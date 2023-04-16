package edu.scau.mis.system.service.impl;

import edu.scau.mis.system.domain.Role;
import edu.scau.mis.system.domain.User;
import edu.scau.mis.system.domain.UserRole;
import edu.scau.mis.system.mapper.RoleMapper;
import edu.scau.mis.system.mapper.UserMapper;
import edu.scau.mis.system.mapper.UserRoleMapper;
import edu.scau.mis.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private RoleMapper roleMapper;
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

    /**
     * 根据ID查询user
     *
     * @param userId
     * @return
     */
    @Override
    public User selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 根据userName查询user
     *
     * @param userName
     * @return
     */
    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 查询user集合
     *
     * @param user
     * @return
     */
    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteUserById(Long userId) {
        userRoleMapper.deleteUserRoleByUserId(userId); //删除关联的角色
        return userMapper.deleteUserById(userId);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        int count = 0;
        if (checkUserNameUnique(user.getUserName())) {
            count = userMapper.insertUser(user);
        }
        return count;
    }

    /**
     * 新增用户-角色信息
     * 前端需传递roles数组
     *
     * @param user
     */
    @Override
    public void insertUserRole(User user) {
        Long[] roles = user.getRoleIds();
        if (roles.length > 0) {
            // 新增用户与角色管理
            List<UserRole> list = new ArrayList<UserRole>();
            for (Long roleId : roles) {
                UserRole ur = new UserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleMapper.batchUserRole(list);
            }
        }
    }


    /**
     * 用户角色授权
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void insertUserAuth(Long userId, Long[] roleIds) {
        userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(userId, roleIds);
    }

    /**
     * 新增用户角色信息
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    public void insertUserRole(Long userId, Long[] roleIds) {
        if (roleIds.length > 0) {
            // 新增用户与角色管理
            List<UserRole> list = new ArrayList<UserRole>();
            for (Long roleId : roleIds) {
                UserRole ur = new UserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        int count = 0;
        if (checkUserNameUnique(user.getUserName())) {
            count = userMapper.updateUser(user);
        }
        return count;
    }

    /**
     * 检查用户名是否唯一
     *
     * @param userName
     * @return
     */
    @Override
    public boolean checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        return count > 0 ? true : false;
    }

    /**
     * 更新用户头像
     *
     * @param user
     * @return
     */
    @Override
    public int updateUserAvatar(User user) {
        return userMapper.updateUserAvatar(user);
    }

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @Override
    public int resetUserPassword(User user) {
        return userMapper.resetUserPassword(user);
    }

    /**
     * 查询用户的角色组
     * 前端需求ROLE_admin,ROLE_user
     *
     * @param userName
     * @return
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<Role> list = roleMapper.selectRolesByUserName(userName);
        if (list.size() > 0) {
            return list.stream().map(Role::getRoleKey).collect(Collectors.joining(","));
        } else {
            return null;
        }
    }

    /**
     * 查询已登录用户角色组
     *
     * @param user
     * @return
     */
    @Override
    public String selectUserRoleGroup(User user) {
        List<Role> list = user.getRoles();
        if (list.size() > 0) {
            return list.stream().map(Role::getRoleKey).collect(Collectors.joining(","));
        } else {
            return "";
        }
    }
}

