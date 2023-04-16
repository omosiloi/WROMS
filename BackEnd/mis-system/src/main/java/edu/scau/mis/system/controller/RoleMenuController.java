package edu.scau.mis.system.controller;

import edu.scau.mis.system.domain.RoleMenu;
import edu.scau.mis.system.service.IRoleMenuService;
import edu.scau.mis.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
/**
 * (RoleMenu)表控制层
 *
 * @author CJW
 * @since 2023-03-04 00:48:47
 */
@RestController
@RequestMapping("roleMenu")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 查询数据列表
     *
     * @param roleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(RoleMenu roleMenu) {
        return AjaxResult.success(this.roleMenuService.selectList(roleMenu));
    }

    /**
     * 分页查询数据列表
     *
     * @param roleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, RoleMenu roleMenu) {
        PageHelper.startPage(pageNum,pageSize);
        List<RoleMenu> list = this.roleMenuService.selectList(roleMenu);
        PageInfo<List> pageInfo = new PageInfo(list);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public AjaxResult getById(@PathVariable("id") Long id) {
        return AjaxResult.success(this.roleMenuService.selectById(id));
    }

    /**
     * 新增数据
     *
     * @param roleMenu 实体
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult add(@RequestBody RoleMenu roleMenu) {
        return AjaxResult.success(this.roleMenuService.insert(roleMenu));
    }

    /**
     * 编辑数据
     *
     * @param roleMenu 实体
     * @return 编辑结果
     */
    @PutMapping
    public AjaxResult edit(@RequestBody RoleMenu roleMenu) {
        return AjaxResult.success(this.roleMenuService.update(roleMenu));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteById(@PathVariable Long id) {
        return AjaxResult.success(this.roleMenuService.deleteById(id));
    }
}
