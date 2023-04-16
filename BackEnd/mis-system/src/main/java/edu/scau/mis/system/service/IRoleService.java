package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author CJW
 * @since 2023-03-04 00:36:27
 */
public interface IRoleService {

    Role selectRoleById(Long roleId);

    List<Role> selectRoleList(Role role);

    List<Role> selectAllRole();

    int deleteRoleById(Long roleId);

    int insertRole(Role role);

    int updateRole(Role role);

}
