package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.Role;

import java.util.List;

public interface IRoleService {

    Role selectRoleById(Long roleId);

    List<Role> selectRoleList(Role role);

    List<Role> selectAllRole();

    int deleteRoleById(Long roleId);

    int insertRole(Role role);

    int updateRole(Role role);

}
