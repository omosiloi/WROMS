import hasRole from './hasRole'
import hasAuthority from './hasAuthority'

const install = function (Vue) {
  Vue.directive('hasRole', hasRole)
  Vue.directive('hasAuthority', hasAuthority)
}

if (window.Vue) {
  window['hasRole'] = hasRole
  window['hasAuthority'] = hasAuthority
  Vue.use(install); // eslint-disable-line
}

export default install
