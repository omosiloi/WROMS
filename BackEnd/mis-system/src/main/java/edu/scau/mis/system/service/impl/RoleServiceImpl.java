package edu.scau.mis.system.service.impl;

import edu.scau.mis.system.domain.Role;
import edu.scau.mis.system.mapper.RoleMapper;
import edu.scau.mis.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 根据id查询role
     *
     * @param roleId
     * @return
     */
    @Override
    public Role selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 查询role列表
     *
     * @param role
     * @return
     */
    @Override
    public List<Role> selectRoleList(Role role) {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 查询所有role
     * @return
     */
    @Override
    public List<Role> selectAllRole(){
        return roleMapper.selectAllRole();
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }
}
