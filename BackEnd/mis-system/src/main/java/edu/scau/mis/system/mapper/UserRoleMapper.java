package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    public int deleteUserRoleByUserId(Long userId);

    public int deleteUserRole(Long[] ids);

    public int countUserRoleByRoleId(Long roleId);

    public int batchUserRole(List<UserRole> userRoleList);

    public int deleteUserRoleInfo(UserRole userRole);

    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);

}
