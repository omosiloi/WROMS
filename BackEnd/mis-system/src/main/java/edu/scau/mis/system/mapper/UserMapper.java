package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserById(Long userId);

    User selectUserByUserName(String userName);

    List<User> selectUserList(User user);

    int deleteUserById(Long userId);

    int insertUser(User user);

    int updateUser(User user);

    int checkUserNameUnique(String userName);

    int updateUserAvatar(User user);

    int resetUserPassword(User user);
}
