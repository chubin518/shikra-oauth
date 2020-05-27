// with polyfills
import 'core-js/stable'
import 'regenerator-runtime/runtime'

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/'
import { VueAxios } from './utils/request'

// mock
// WARNING: `mockjs` NOT SUPPORT `IE` PLEASE DO NOT USE IN `production` ENV.
// import './mock'

import bootstrap from './core/bootstrap'
import './core/lazy_use'
import './permission' // permission control
import './utils/filter' // global filter
import './components/global.less'
import { Dialog } from '@/components'

Vue.config.productionTip = false

// mount axios Vue.$http and this.$http
Vue.use(VueAxios)
Vue.use(Dialog)
// 系统错误捕获
const errorHandler = (error, vm) => {
  console.error('抛出全局异常')
  console.error(vm)
  console.error(error)
}
Vue.config.errorHandler = errorHandler
Vue.prototype.$throw = error => errorHandler(error, this)
window.addEventListener('unhandledrejection', function(e) {
  console.log('捕获到异常：', e)
  return true
})
new Vue({
  router,
  store,
  created: bootstrap,
  render: h => h(App)
}).$mount('#app')
