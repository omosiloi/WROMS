package edu.scau.mis.web.controller.system;

import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.core.utils.ServletUtils;
import edu.scau.mis.system.domain.User;
import edu.scau.mis.system.security.domain.LoginUser;
import edu.scau.mis.system.security.service.TokenService;
import edu.scau.mis.system.service.IMenuService;
import edu.scau.mis.system.service.IUserAuthService;
import edu.scau.mis.system.service.IUserService;
import edu.scau.mis.system.vo.LoginUserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class UserAuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserAuthService userAuthService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public AjaxResult login(@RequestBody LoginUserVo loginUserVo) {
        Map<String, Object> data = new HashMap<>();
        String token = userAuthService.login(loginUserVo.getUsername(), loginUserVo.getPassword());
        data.put("token", token);
        return AjaxResult.success("登录成功", data);
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        User user = loginUser.getUser();
        Map<String, Object> data = new HashMap<>();
        data.put("name", user.getUserName());
        data.put("avatar", user.getAvatar());
        data.put("roles", userService.selectUserRoleGroup(user));
        data.put("authorities", menuService.selectMenuPermsByUserId(user.getUserId()));
        return AjaxResult.success(data);
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public AjaxResult logout() {
        return AjaxResult.success("退出成功");
    }

    @ApiOperation("获取动态路由/SideBar菜单")
    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        User user = loginUser.getUser();
        return AjaxResult.success("获取路由成功", menuService.getRouters(user.getUserId()));
    }

    @GetMapping("/test")
    public AjaxResult getA() {
        return AjaxResult.success("ok");
    }
}
