package edu.scau.mis.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.system.domain.User;
import edu.scau.mis.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public AjaxResult page(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, User user) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userService.selectUserList(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @ApiOperation("通过ID查询user")
    @GetMapping("/{userId}")
    public AjaxResult getById(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.selectUserById(userId));
    }

    @ApiOperation("添加用户")
    @PostMapping
    public AjaxResult add(@RequestBody User user) {
        int count = userService.insertUser(user);
        return count > 0 ? AjaxResult.success("添加用户成功") : AjaxResult.error("用户已经存在");

    }

    @ApiOperation("修改用户")
    @PutMapping
    public AjaxResult edit(@RequestBody User user) {
        int count = userService.updateUser(user);
        return count > 0 ? AjaxResult.success("添加用户成功") : AjaxResult.error("用户已经存在");
    }

    @ApiOperation("根据ID删除用户")
    @DeleteMapping("/delete/{userId}")
    public AjaxResult deleteById(@PathVariable Long userId) {
        int count = userService.deleteUserById(userId);
        return count > 0 ? AjaxResult.success("删除用户成功") : AjaxResult.error("删除用户失败");
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("/authRole")
    public AjaxResult insertAuthRole(@RequestBody User user) {
        userService.insertUserAuth(user.getUserId(),user.getRoleIds());
        return AjaxResult.success("分配角色成功");
    }

}
