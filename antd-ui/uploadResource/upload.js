const path = require('path')
const chalk = require('chalk')
const argv = require('yargs')
  .alias('env', 'environment')
  .alias('oc', 'onlyconfig')
  .demand(['env', 'oc'])
  .default({
    env: 'stage',
    oc: false
  })
  .describe({
    env: 'upload environment path',
    oc: '是否只更新配置'
  }).argv
process.env.VUE_APP_Mode_Name = argv.environment
console.log(argv)

const action = async () => {
  // 脚本build完成可以做静态文件上传等操作
  const uploadFile = require('./uploadFile')
  console.log(chalk.cyan(`开始准备脚本上传！！！！！！！！`))
  try {
    const res = uploadFile.uploadResource(path.join('./dist'))
    console.log(res)
    if (!res || res.code.toString() !== '0') {
      throw res
    }
    console.log(chalk.cyan(`脚本上传完成！！！！！！！！`))
  } catch (error) {
    console.error(error)
    console.log(chalk.red(`脚本上传出错！！！！！！！！`))
    process.exit(1)
  }
}

action()
