package edu.scau.mis.system.security.handler;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import edu.scau.mis.core.constants.HttpStatus;
import edu.scau.mis.core.domain.AjaxResult;
import edu.scau.mis.core.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 * 认证失败处理类 返回未授权
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String result = JSONUtil.toJsonStr(AjaxResult.error(code,"用户认证失败"));
        ServletUtils.renderString(response,result);
    }
}
