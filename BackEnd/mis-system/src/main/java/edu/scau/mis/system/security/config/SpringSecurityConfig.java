package edu.scau.mis.system.security.config;

import edu.scau.mis.system.security.filter.MisAuthenticationTokenFilter;
import edu.scau.mis.system.security.handler.AuthenticationEntryPointImpl;
import edu.scau.mis.system.security.handler.LogoutSuccessHandlerImpl;
import edu.scau.mis.system.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * SpringSecurity5.7.x配置
 */
@Configuration
@EnableWebSecurity // 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别的权限认证
public class SpringSecurityConfig {
    /* 不受控静态资源RUL */
    private static final String[] SECURITY_IGNORE_RESOURCE_URL = {
            // "/*.html", "/**/*.html" // 本项目前后端分离，无静态资源
    };
    /* 不受控API_PATH */
    private static final String[] SECURITY_IGNORE_API_PATH = {
            //  通过这条代码可以绕过访问控制，直接访问所有地址
            "/**"
    };
    /* 用户服务 */
    @Autowired
    private final UserDetailsServiceImpl userDetailsService;
    /* 认证失败处理器 */
    private AuthenticationEntryPointImpl unauthorizedHandler;
    /* 退出成功处理器 */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    /* token认证过滤器 */
    @Autowired
    private MisAuthenticationTokenFilter misAuthenticationTokenFilter;

    /** 构造器注入 */
    @Autowired
    public SpringSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    /** 配置密码加密 */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /** 用户认证管理器 */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* 解决跨域 */
        http.cors();
        /* 禁用跨站请求伪造csrf */
        http.csrf().disable();
        /* 认证失败处理 */
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        /* 后期配置JWT token后禁用session */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        /* 配置认证授权 */
        http.authorizeRequests(authorize -> authorize
                .antMatchers(HttpMethod.GET, SECURITY_IGNORE_RESOURCE_URL).permitAll() // 允许GET请求静态资源
                .antMatchers(SECURITY_IGNORE_API_PATH).permitAll() // 允许白名单API
                .anyRequest().authenticated() // 其他请求都需要身份认证
        );

        /* 配置退出 */
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler); // 配置退出路径

        /* 配置token验证过滤器 */
        http.addFilterBefore(misAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        /*认证用户时用户信息加载配置，注入springAuthUserService */
        http.userDetailsService(userDetailsService);
        return http.build();
    }
    /** 配置跨源访问(CORS) */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
}
