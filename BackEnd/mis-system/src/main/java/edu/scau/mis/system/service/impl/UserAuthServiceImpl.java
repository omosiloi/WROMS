package edu.scau.mis.system.service.impl;

import edu.scau.mis.system.security.domain.LoginUser;
import edu.scau.mis.system.security.service.TokenService;
import edu.scau.mis.system.service.IUserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements IUserAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    @Override
    public String login(String username, String password) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // TODO 生成JWT token
        tokenService.generateToken(loginUser);
        String token = "Admin_token"; //测试token
        return token;
    }
}
