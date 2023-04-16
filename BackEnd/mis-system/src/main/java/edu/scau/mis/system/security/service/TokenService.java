package edu.scau.mis.system.security.service;

import edu.scau.mis.system.security.domain.LoginUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenService {
    /* 内存存储loginUser用于测试, 后期使用Redis存储登录用户对象loginUser */
    private LoginUser loginUser;

    /**
     * 生成token并redis存储loginUser
     * @param loginUser
     * @return 令牌
     */
    public String generateToken(LoginUser loginUser){
        // TODO 生成JWT token
        String token = loginUser.getUsername() + "_token"; //测试token
        // 后期将loginUser存入Redis
        this.loginUser = loginUser;
        return token;
    }

    /**
     * 解析request中token获取loginUser
     * @return loginUser
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        // 后期从Redis中查询loginUser对象
        LoginUser loginUser = this.loginUser;
        return loginUser;
    }
}
