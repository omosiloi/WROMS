package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.User;

import java.util.List;

public interface IUserService {

    User selectUserById(Long userId);

    User selectUserByUserName(String userName);

    List<User> selectUserList(User user);

    int deleteUserById(Long userId);

    int insertUser(User user);

    void insertUserRole(User user);

    void insertUserAuth(Long userId, Long[] roleIds);

    int updateUser(User user);

    boolean checkUserNameUnique(String userName);

    int updateUserAvatar(User user);

    int resetUserPassword(User user);

    String selectUserRoleGroup(String userName);

    String selectUserRoleGroup(User user);

}
