package edu.scau.mis.system.service;

import org.springframework.security.core.AuthenticationException;

public interface IUserAuthService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return token
     * @throws AuthenticationException
     */
    String login(String username, String password) throws AuthenticationException;
}
