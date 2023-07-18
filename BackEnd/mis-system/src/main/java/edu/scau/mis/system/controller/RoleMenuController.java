package edu.scau.mis.system.controller;

import edu.scau.mis.system.domain.RoleMenu;
import edu.scau.mis.system.service.IRoleMenuService;
import edu.scau.mis.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("roleMenu")
public class RoleMenuController {

    @Autowired
    private IRoleMenuService roleMenuService;

    @GetMapping("/list")
    public AjaxResult list(RoleMenu roleMenu) {
        return AjaxResult.success(this.roleMenuService.selectList(roleMenu));
    }

    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, RoleMenu roleMenu) {
        PageHelper.startPage(pageNum,pageSize);
        List<RoleMenu> list = this.roleMenuService.selectList(roleMenu);
        PageInfo<List> pageInfo = new PageInfo(list);
        return AjaxResult.success(pageInfo);
    }

    @GetMapping("{id}")
    public AjaxResult getById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.roleMenuService.selectById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody RoleMenu roleMenu) {
        return AjaxResult.success(this.roleMenuService.insert(roleMenu));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody RoleMenu roleMenu) {
        return AjaxResult.success(this.roleMenuService.update(roleMenu));
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable Long id) {
        return AjaxResult.success(this.roleMenuService.deleteById(id));
    }
}
