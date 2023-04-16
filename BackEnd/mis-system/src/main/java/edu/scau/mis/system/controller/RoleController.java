package edu.scau.mis.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.system.domain.Role;
import edu.scau.mis.system.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiOperation("分页查询角色")
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, Role role) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleService.selectRoleList(role);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @ApiOperation("查询所有角色")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        return AjaxResult.success(roleService.selectAllRole());
    }

    @ApiOperation("根据ID查询角色")
    @GetMapping("/{roleId}")
    public AjaxResult getById(@PathVariable("roleId") Long roleId) {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    @ApiOperation("新增角色")
    @PostMapping
    public AjaxResult add(@RequestBody Role role) {
        return AjaxResult.success(roleService.insertRole(role));
    }

    @ApiOperation("编辑角色")
    @PutMapping
    public AjaxResult edit(@RequestBody Role role) {
        return AjaxResult.success(roleService.updateRole(role));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/delete/{roleId}")
    public AjaxResult deleteById(@PathVariable Long roleId) {
        return AjaxResult.success(roleService.deleteRoleById(roleId));
    }
}
