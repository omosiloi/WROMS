package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.Menu;
import edu.scau.mis.system.vo.RouterVo;

import java.util.List;
import java.util.Set;


public interface IMenuService {

    List<Menu> selectMenuList(Menu menu);


    List<Menu> buildTreeMenu(List<Menu> menus);

    List<Menu> selectAllMenuTree();

    List<Menu> selectMenuListByUserId(Long userId);

    Set<String> selectMenuPermsByUserId(Long userId);

    List<Long> selectMenuIdsByRoleId(Long roleId);

    List<RouterVo> getRouters(Long userId);

    List<RouterVo> buildRouters(List<Menu> menuTree);

    Menu selectMenuById(Long menuId);

    boolean checkMenuExistRole(Long menuId);

    boolean checkMenuNameUnique(String menuName);

    int insertMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenuById(Long menuId);


}
