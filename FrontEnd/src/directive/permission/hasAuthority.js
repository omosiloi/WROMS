 /**
 * 操作权限处理
 * 
 */
 
import store from '@/store'

export default {
  inserted(el, binding, vnode) {
    const { value } = binding
    const all_authorities = '*:*:*'
    const authorities = store.getters && store.getters.authorities

    if (value && value instanceof Array && value.length > 0) {
      const authoritiesFlag = value

      const hasAuthorities = authorities.some(permission => {
        return all_authorities === authorities || authoritiesFlag.includes(permission)
      })

      if (!hasAuthorities) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`请设置操作权限标签值`)
    }
  }
}
