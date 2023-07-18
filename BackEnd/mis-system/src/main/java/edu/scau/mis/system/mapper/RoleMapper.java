package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role selectRoleById(Long id);

    List<Role> selectRolesByUserId(Long userId);

    List<Role> selectRolesByUserName(String userName);

    List<Long> selectRoleIdListByUserId(Long userId);

    List<Role> selectRoleList(Role role);

    List<Role> selectAllRole();

    int deleteRoleById(Long roleId);

    int deleteRoleByIds(Long[] roleIds);

    int insertRole(Role role);

    int updateRole(Role role);

    Role checkRoleKeyUnique(String roleKey);

}

