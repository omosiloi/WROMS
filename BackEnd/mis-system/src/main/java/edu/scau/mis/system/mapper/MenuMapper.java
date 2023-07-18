package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> selectMenuList(Menu menu);

    List<Menu> selectAllMenuTree();

    List<Menu> selectMenuListByUserId(Long userId);

    List<Menu> selectMenuTreeByUserId(Long userId);

    List<String> selectMenuPerms();

    List<String> selectMenuPermsByUserId(Long userId);

    List<Long> selectMenuIdsByRoleId(Long roleId);

    Menu selectMenuById(Long menuId);

    int hasChildByMenuId(Long menuId);

    Menu checkMenuNameUnique(String menuName);

    int insertMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenuById(Long menuId);
}
