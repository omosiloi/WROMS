package edu.scau.mis.system.domain;

import java.io.Serializable;

public class UserRole implements Serializable {
    private static final long serialVersionUID = -74141915316871321L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 角色ID
    */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
