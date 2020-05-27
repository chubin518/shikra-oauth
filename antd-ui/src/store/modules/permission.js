import { asyncRouterMap, constantRouterMap } from '@/config/router.config'

/**
 * 过滤账户是否拥有某一个权限，并将菜单从加载列表移除
 *
 * @param permission
 * @param route
 * @returns {boolean}
 */
function hasPermission(roles, route) {
  return true
  // if (
  //   route.path === '*' ||
  //   (route.component && (route.component.name === 'BasicLayout' || route.component.name === 'RouteView'))
  // ) {
  //   console.log(route.path || '')
  //   return true
  // }
  // var exists = roles.findIndex(item => item.url.toLowerCase() === route.path.toLowerCase()) >= 0
  // return exists
}

function filterAsyncRouter (routerMap, roles) {
  const accessedRouters = routerMap.filter(route => {
    if (hasPermission(roles, route)) {
      if (route.children && route.children.length) {
        const children = filterAsyncRouter(route.children, roles)
        if (children && children.length > 0) {
          route.children = children
        } else {
          return false
        }
      }
      return true
    }
    return false
  })
  return accessedRouters
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes ({ commit }, data) {
      return new Promise(resolve => {
        const { roles } = data
        console.log(roles)
        const accessedRouters = filterAsyncRouter(asyncRouterMap, roles)
        console.log(accessedRouters)
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
