package edu.scau.mis.system.security.domain;

import edu.scau.mis.system.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class LoginUser implements UserDetails {
    private User user; //绑定用户
    private long expireTime;   // 过期时间
    private Set<GrantedAuthority> authorities; //权限

    public LoginUser(){}
    public LoginUser(User user, Set<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    /**
     * 权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 用户名
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "user=" + user +
                ", expireTime=" + expireTime +
                ", authorities=" + authorities +
                '}';
    }
}
