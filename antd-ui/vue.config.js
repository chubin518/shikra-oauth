const path = require('path')
const webpack = require('webpack')
const createThemeColorReplacerPlugin = require('./config/plugin.config')
const AssetsPlugin = require('assets-webpack-plugin')
const conf = require('./uploadResource/conf')
const chalk = require('chalk')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const isProd = process.env.NODE_ENV === 'production'
let publicPath = '/'
if (isProd) {
  publicPath = `//file.40017.cn/${conf.DIC_NAME}/${process.env.VUE_APP_Mode_Name}/`
}

// vue.config.js
const vueConfig = {
  publicPath: publicPath,
  outputDir: 'dist',
  indexPath: path.resolve(__dirname, '../public/index.html'),
  configureWebpack: config => {
    const plugins = [new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/)]
    config.externals = {
      vue: 'Vue',
      vuex: 'Vuex',
      'vue-router': 'VueRouter',
      axios: 'axios',
      moment: 'moment'
    }

    if (isProd) {
      // 开启分离js
      config.optimization = {
        runtimeChunk: 'single',
        splitChunks: {
          chunks: 'all',
          maxInitialRequests: Infinity,
          minSize: 20000,
          cacheGroups: {
            vendor: {
              test: /[\\/]node_modules[\\/]/,
              name(module) {
                // get the name. E.g. node_modules/packageName/not/this/part.js
                // or node_modules/packageName
                const packageName = module.context.match(/[\\/]node_modules[\\/](.*?)([\\/]|$)/)[1]
                // npm package names are URL-safe, but some servers don't like @ symbols
                return `npm.${packageName.replace('@', '')}`
              }
            }
          }
        }
      }
      // 取消webpack警告的性能提示
      config.performance = {
        hints: 'warning',
        // 入口起点的最大体积
        maxEntrypointSize: 50000000,
        // 生成文件的最大体积
        maxAssetSize: 30000000,
        // 只给出 js 文件的性能提示
        assetFilter: function (assetFilename) {
          return assetFilename.endsWith('.js')
        }
      }

      plugins.push(
        new AssetsPlugin({
          filename: 'webpack.assets.json',
          path: path.join(__dirname, '../public'),
          processOutput: function (assets) {
            const fileMaps = JSON.stringify(assets)
            return fileMaps
          }
        })
      )
      plugins.push({
        apply: compiler => {
          compiler.hooks.done.tap('assets-webpack-plugin', async compilation => {
            const uploadFile = require('./uploadResource/uploadFile')
            console.info(chalk.cyan(`准备上传脚本至CDN服务器`))
            try {
              const res = uploadFile.uploadResource(path.join('./dist'))
              if (!res || res.code.toString() !== '0') {
                throw res
              }
              console.info(chalk.green(`上传脚本至CDN服务器成功`))
            } catch (error) {
              console.error(chalk.bold.red(error), '上传脚本至CDN服务器异常')
              process.exit(1)
            }
          })
        }
      })
    }

    if (!isProd || process.env.VUE_APP_PREVIEW === 'true') {
      plugins.push(createThemeColorReplacerPlugin())
    }
    config.plugins = [...config.plugins, ...plugins]
  },

  chainWebpack: config => {
    config.resolve.alias.set('@$', resolve('src'))

    const svgRule = config.module.rule('svg')
    svgRule.uses.clear()
    svgRule
      .oneOf('inline')
      .resourceQuery(/inline/)
      .use('vue-svg-icon-loader')
      .loader('vue-svg-icon-loader')
      .end()
      .end()
      .oneOf('external')
      .use('file-loader')
      .loader('file-loader')
      .options({
        name: 'assets/[name].[hash:8].[ext]'
      })
    // 注入cdn
    config.plugin('html').tap(args => {
      args[0].cdn = conf.STATIC_RESOURCE
      return args
    })
    // if prod is on
    // assets require on cdn
    if (isProd) {
      config.plugin('html').tap(args => {
        args[0].cdn = conf.STATIC_RESOURCE
        return args
      })
      // 删除预加载
      config.plugins.delete('preload')
      config.plugins.delete('prefetch')
      // 压缩代码
      config.optimization.minimize(true)
      // 分割代码
      config.optimization.splitChunks({
        chunks: 'all'
      })
    }
  },

  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          // less vars，customize ant design theme

          // 'primary-color': '#F5222D',
          // 'link-color': '#F5222D',
          'border-radius-base': '2px'
        },
        // DO NOT REMOVE THIS LINE
        javascriptEnabled: true
      }
    }
  },

  devServer: {
    // development server port 8000
    port: 8000,
    // If you want to turn on the proxy, please remove the mockjs /src/main.jsL11
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080/',
        ws: false,
        changeOrigin: true
      }
    },
    overlay: {
      warning: false,
      errors: false
    }
  },
  lintOnSave: false,
  // disable source map in production
  productionSourceMap: false,
  // babel-loader no-ignore node_modules/*
  transpileDependencies: []
}

// preview.pro.loacg.com only do not use in your production;
if (process.env.VUE_APP_PREVIEW === 'true') {
  console.log('VUE_APP_PREVIEW', true)
  // add `ThemeColorReplacer` plugin to webpack plugins
  vueConfig.configureWebpack.plugins.push(createThemeColorReplacerPlugin())
}

module.exports = vueConfig
