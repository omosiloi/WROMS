package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleMapper
 */
@Mapper
public interface RoleMapper {

    /**
     * 根据id查询role
     * @param id
     * @return
     */
    Role selectRoleById(Long id);

    /**
     * 根据userId查找role列表
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 根据userName查询role列表
     * @param userName
     * @return
     */
    List<Role> selectRolesByUserName(String userName);

    /**
     * 根据userId查询roleId列表
     * @param userId
     * @return
     */
    List<Long> selectRoleIdListByUserId(Long userId);

    /**
     * 查询role列表
     * @param role
     * @return
     */
    List<Role> selectRoleList(Role role);

    /**
     * 查询所有role
     * @return
     */
    List<Role> selectAllRole();

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRoleById(Long roleId);


    /**
     * 批量删除角色信息
     * @param roleIds
     */
    int deleteRoleByIds(Long[] roleIds);

    /**
     * 新增角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    Role checkRoleKeyUnique(String roleKey);

}

