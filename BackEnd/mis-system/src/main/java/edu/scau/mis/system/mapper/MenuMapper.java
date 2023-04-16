package edu.scau.mis.system.mapper;

import edu.scau.mis.system.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    /**
     * 参数查询菜单列表
     *
     * @param menu 菜单对象
     * @return 菜单列表
     */
    List<Menu> selectMenuList(Menu menu);

    /**
     * 查询所有目录+菜单
     * @return 树形菜单
     */
    List<Menu> selectAllMenuTree();


    /**
     * 根据userId查询菜单列表
     *
     * @param userId
     * @return 菜单列表
     */
    List<Menu> selectMenuListByUserId(Long userId);


    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<Menu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据所有菜单权限码
     *
     * @return 权限码集合
     */
    List<String> selectMenuPerms();

    /**
     * 根据userId查询权限码
     *
     * @param userId 用户ID
     * @return 权限码集合
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据roleID查询menuIds
     *
     * @param roleId 角色ID
     * @return 选中的menuIds
     */
    List<Long> selectMenuIdsByRoleId(Long roleId);

    /**
     * 根据menuId查询menu
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    Menu selectMenuById(Long menuId);

    /**
     * 根据menuId判断是否存在子菜单
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int hasChildByMenuId(Long menuId);

    /**
     * 校验菜单名称是否唯一
     * @param menuName 菜单名称
     * @return 结果
     */
    Menu checkMenuNameUnique(String menuName);

    /**
     * 新增菜单
     *
     * @param menu
     * @return 结果
     */
    int insertMenu(Menu menu);

    /**
     * 修改菜单
     *
     * @param menu
     * @return 结果
     */
    int updateMenu(Menu menu);

    /**
     * 根据menuId删除菜单
     *
     * @param menuId
     * @return 结果
     */
    int deleteMenuById(Long menuId);
}
