package edu.scau.mis.system.security.service;

import edu.scau.mis.system.domain.Role;
import edu.scau.mis.system.domain.User;
import edu.scau.mis.system.security.domain.LoginUser;
import edu.scau.mis.system.service.IMenuService;
import edu.scau.mis.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    /**
     * 根据用户名从数据库加载用户和权限生成登录用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
//        //将密码取出转化加密比较
//        String a = new BCryptPasswordEncoder().encode(user.getPassword());
//        user.setPassword(a);
        // 粗粒度权限
        // Set<GrantedAuthority> authorities  = getAuthoritiesOfRoles(user);
        // 细粒度权限
        Set<GrantedAuthority> authorities = getAuthoritiesOfPerms(user);
        return new LoginUser(user, authorities);
    }

    /**
     * 根据角色roles生成GrantedAuthorities
     * sample: admin,cashier,manager
     *
     * @param user
     * @return
     */
    private Set<GrantedAuthority> getAuthoritiesOfRoles(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority authority;
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            authority = new SimpleGrantedAuthority(role.getRoleKey());
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 根据权限码perms生成GrantedAuthorities
     * 细粒度权限
     * sample: pos:product:list, system:user:list
     *
     * @param user
     * @return
     */
    private Set<GrantedAuthority> getAuthoritiesOfPerms(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority authority;
        Set<String> perms = menuService.selectMenuPermsByUserId(user.getUserId());
        for (String perm : perms) {
            authority = new SimpleGrantedAuthority(perm);
            authorities.add(authority);
        }
        return authorities;
    }
}
