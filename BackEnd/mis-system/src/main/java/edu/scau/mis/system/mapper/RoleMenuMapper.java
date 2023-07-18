package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuMapper {

    public int checkMenuExistRole(Long menuId);

    public int deleteRoleMenuByRoleId(Long roleId);

    public int deleteRoleMenu(Long[] ids);

    public int batchRoleMenu(List<RoleMenu> roleMenuList);
}
