package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 根据ID查询user
     * @param userId
     * @return
     */
    User selectUserById(Long userId);

    /**
     * 根据userName查询user
     * @param userName
     * @return
     */
    User selectUserByUserName(String userName);

    /**
     * 查询user集合
     * @param user
     * @return
     */
    List<User> selectUserList(User user);

    /**
     *
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUserById(Long userId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 检查用户名是否唯一
     * @param userName
     * @return
     */
    int checkUserNameUnique(String userName);

    /**
     * 更新用户头像
     * @param user
     * @return
     */
    int updateUserAvatar(User user);

    /**
     * 重置密码
     * @param user
     * @return
     */
    int resetUserPassword(User user);
}
