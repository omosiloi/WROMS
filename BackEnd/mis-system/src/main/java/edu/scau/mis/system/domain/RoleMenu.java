package edu.scau.mis.system.domain;

import java.io.Serializable;

public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -44920354405216057L;
    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 菜单ID
    */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
