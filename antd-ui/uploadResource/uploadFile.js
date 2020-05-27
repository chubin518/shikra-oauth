// http://leonidmanager2.17usoft.com/main/tool/resource

const fs = require('fs')
const path = require('path')
const FormData = require('form-data')
const axios = require('axios')
const conf = require('./conf')
const chalk = require('chalk')

/**
 * 获取文件上传header
 * @param {*} form
 */
function getUploadHeaders (form) {
  return new Promise((resolve, reject) => {
    form.getLength((err, length) => {
      if (err) {
        reject(err)
      }
      const header = Object.assign(
        {
          'Content-Length': length,
          'user-token': conf.USER_TOKEN,
          'asset-key': conf.ASSET_KEY
        },
        form.getHeaders()
      )
      resolve(header)
    })
  })
}

/**
 * 遍历获取目录下的文件
 * @param {*} dirPath
 */
function getUploadFiles (dirPath) {
  let fileResult = []
  const lstFile = fs.readdirSync(dirPath)
  if (!lstFile || lstFile.length <= 0) {
    return fileResult
  }
  lstFile.forEach(fileName => {
    const filePath = path.join(dirPath, fileName) // 获取当前文件绝对路径
    // 根据文件路径获取文件信息，返回一个fs.Stats对象
    const stats = fs.statSync(filePath)
    if (stats.isFile()) {
      fileResult.push({
        dirPath: dirPath,
        name: fileName,
        filePath: filePath
      })
    }
    if (stats.isDirectory()) {
      const subList = getUploadFiles(filePath)
      fileResult = fileResult.concat(subList)
    }
  })
  return fileResult
}

/**
 * 上传文件至狮子座
 * @param {*} filePath
 * @param {*} key
 */
function fileUpload (filePath, key) {
  return new Promise((resolve, reject) => {
    const file = path.parse(filePath)
    const data = fs.createReadStream(filePath)
    const form = new FormData()
    form.append('bucket_name', conf.BUCKET_NAME)
    form.append('key', key)
    form.append('file', data, file.name + file.ext)
    getUploadHeaders(form)
      .then(result => {
        return axios.post(conf.UPLOAD_URL, form, {
          headers: result
        })
      })
      .then(result => {
        resolve(result.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

module.exports = {
  /**
   * 根据打包的静态文件目录上传文件至狮子座
   * @param {*} dirPath
   */
  uploadResource (dirPath) {
    const result = {
      code: 0,
      msg: [],
      result: [],
      type: []
    }
    const lstFile = getUploadFiles(dirPath)

    lstFile.forEach(async file => {
      let fileDir = file.dirPath.replace(dirPath, '').replace(/\\/g, '/')
      if (fileDir && fileDir[0] === '/') {
        fileDir = fileDir.substring(1)
      }

      const fileKey = `/${conf.DIC_NAME}/${process.env.VUE_APP_Mode_Name}/${fileDir ? `${fileDir}/` : ''}`

      const res = await fileUpload(file.filePath, fileKey).catch(err => {
        console.error(chalk.bold.red(err), file.filePath, fileKey)
      })
      if (res && res.code.toString() === '0') {
        result.msg.push(res.msg)
        result.result.push(res.result)
        result.type.push(res.type)
      } else {
        if (res) {
          result.code = 100
          result.msg.push(res.msg)
          result.result.push(res.result)
          result.type.push(res.type)
          console.log(chalk.yellow(`${file.filePath}上传至${fileKey}失败：${res.msg}`))
        }
      }
    })
    return result
  }
}
