package edu.scau.mis.system.controller;

import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.system.domain.Menu;
import edu.scau.mis.system.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("参数查询菜单列表")
    @GetMapping("/list")
    public AjaxResult list(Menu menu){
        List<Menu> list = menuService.selectMenuList(menu);
        return AjaxResult.success(list);
    }

    @ApiOperation("获取所有目录+菜单")
    @GetMapping("/tree")
    public AjaxResult tree(){
        List<Menu> list = menuService.selectAllMenuTree();
        return AjaxResult.success(list);
    }

    @ApiOperation("通过menuId查询menu")
    @GetMapping("/{menuId}")
    public AjaxResult getById(@PathVariable("menuId") Long menuId) {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    @ApiOperation("添加菜单")
    @PostMapping
    public AjaxResult add(@RequestBody Menu menu) {
        int count = menuService.insertMenu(menu);
        return count > 0 ? AjaxResult.success("添加菜单成功") : AjaxResult.error("菜单已经存在");

    }

    @ApiOperation("修改菜单")
    @PutMapping
    public AjaxResult edit(@RequestBody Menu menu) {
        int count = menuService.updateMenu(menu);
        return count > 0 ? AjaxResult.success("修改菜单成功") : AjaxResult.error("菜单已经存在");
    }

    @ApiOperation("根据menuId删除菜单")
    @DeleteMapping("/delete/{menuId}")
    public AjaxResult deleteById(@PathVariable Long menuId) {
        int count = menuService.deleteMenuById(menuId);
        return count > 0 ? AjaxResult.success("删除菜单成功") : AjaxResult.error("删除菜单失败");
    }

}
