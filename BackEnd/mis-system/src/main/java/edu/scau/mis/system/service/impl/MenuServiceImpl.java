package edu.scau.mis.system.service.impl;

import cn.hutool.core.util.StrUtil;
import edu.scau.mis.system.domain.Menu;
import edu.scau.mis.system.domain.Role;
import edu.scau.mis.system.mapper.MenuMapper;
import edu.scau.mis.system.mapper.RoleMapper;
import edu.scau.mis.system.mapper.RoleMenuMapper;
import edu.scau.mis.system.service.IMenuService;
import edu.scau.mis.system.vo.MetaVo;
import edu.scau.mis.system.vo.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;



    @Autowired
    private RoleMenuMapper roleMenuMapper;
    /**
     * 参数查询菜单列表
     *
     * @param menu 菜单对象
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenuList(Menu menu){
        List<Menu> menuList = menuMapper.selectMenuList(menu);
        List<Menu> treeMenuList = this.buildTreeMenu(menuList);
        return treeMenuList;

    }

    /**
     * 构建树形菜单
     * @param menus
     * @return
     */
    public List<Menu> buildTreeMenu(List<Menu> menus) {
        List<Menu> finalMenus = new ArrayList<>();
        for (Menu menu : menus) {
            for (Menu e : menus) {
                if (menu.getMenuId() == e.getParentId()) {
                    menu.getChildren().add(e);
                }
            }
            if (menu.getParentId() == 0L) {  // 提取出父节点
                finalMenus.add(menu);
            }
        }
        return finalMenus;
    }

    /**
     * 查询所有目录+菜单
     * @return 树形菜单
     */
    @Override
    public List<Menu> selectAllMenuTree(){
        List<Menu> menuList = menuMapper.selectAllMenuTree();
        List<Menu> treeMenuList = this.buildTreeMenu(menuList);
        return treeMenuList;
    }


    /**
     * 根据userId查询菜单列表
     *
     * @param userId 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<Menu> selectMenuListByUserId(Long userId){
        List<Menu> menuList = null;
        // 管理员显示所有菜单信息
        if (userId == 1L) {
            menuList = selectMenuList(new Menu());
        } else {
            menuList = buildTreeMenu(menuMapper.selectMenuListByUserId(userId));
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限信息
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId){
        List<String> perms;
        // 管理员获得所有权限吗
        if(userId == 1L) {
            perms = menuMapper.selectMenuPerms();
        } else {
            perms = menuMapper.selectMenuPermsByUserId(userId);
        }
        Set<String> permsSet = new HashSet<>(); // 使用set去重
        for (String perm : perms){
            if (StrUtil.isNotEmpty(perm)){
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据roleID查询menuIds
     *
     * @param roleId 角色ID
     * @return 选中的menuIds
     */
    @Override
    public List<Long> selectMenuIdsByRoleId(Long roleId) {
        Role role = roleMapper.selectRoleById(roleId);
        return menuMapper.selectMenuIdsByRoleId(roleId);
    }

    /**
     * 得到动态路由
     * @return
     */
    @Override
    public List<RouterVo> getRouters(Long userId) {
        List<Menu> treeMenuListOfLoginUser = selectMenuListByUserId(userId);
        List<RouterVo> routerVos = this.buildRouters(treeMenuListOfLoginUser);
        return routerVos;
    }

    /**
     * 构建路由
     * @param menuTree
     * @return routers
     */
    public List<RouterVo> buildRouters(List<Menu> menuTree) {
        List<RouterVo> routerVos = new ArrayList<>();
        menuTree.forEach(menu -> {
            RouterVo routerVo = new RouterVo();
            routerVo.setPath(this.getRouterPath(menu));
            routerVo.setHidden("1".equals(menu.getVisible()));
            routerVo.setComponent(this.getRouterComponent(menu));
            String path = menu.getPath();
            // system -> System
            String name = path.substring(0, 1).toUpperCase() + path.substring(1);
            routerVo.setName(name);
            MetaVo meta = new MetaVo();
            meta.setTitle(menu.getMenuName());
            meta.setIcon(menu.getIcon());
            routerVo.setMeta(meta);
            if (menu.getChildren().size() > 0 && "M".equals(menu.getMenuType())) {
                routerVo.setRedirect("noRedirect");
                // 子节点调用当前方法进行再次转换
                routerVo.setChildren(buildRouters(menu.getChildren()));
            }
            routerVos.add(routerVo);
        });
        return routerVos;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(Menu menu)
    {
        String routerPath = menu.getPath();
        // M 代表目录
        if (0 == menu.getParentId().intValue() && "M".equals(menu.getMenuType())) { // 目录
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    private String getRouterComponent(Menu menu)
    {
        String component = "Layout";
        // C 代表菜单
        if (StrUtil.isNotEmpty(menu.getComponent()) && "C".equals(menu.getMenuType())) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 根据menuId查询menu
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public Menu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 根据menuId判断是否存在子菜单
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    private boolean hasChildByMenuId(Long menuId) {
        int result = menuMapper.hasChildByMenuId(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 根据menuId判断是否存有角色
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        int result = roleMenuMapper.checkMenuExistRole(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 判断menuName是否唯一
     *
     * @param menuName
     * @return 结果
     */
    @Override
    public boolean checkMenuNameUnique(String menuName) {
        Menu info = menuMapper.checkMenuNameUnique(menuName);
        return info == null ? true : false;
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu) {
        int row = 0;
        if(checkMenuNameUnique(menu.getMenuName())){
            row = menuMapper.insertMenu(menu);
        }
        return  row;
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 根据menuId删除菜单
     *
     * @param menuId
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId) {
        return menuMapper.deleteMenuById(menuId);
    }
}
