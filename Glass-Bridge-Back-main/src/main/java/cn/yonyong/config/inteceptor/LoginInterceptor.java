package cn.yonyong.config.inteceptor;

import cn.yonyong.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author gugu
 */
@Slf4j
//@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //首先进入的方法
        System.out.println("preHandle");
        //return false表示拦截，不向下执行
        //return true表示放行
        String jwt = request.getHeader("Authorization");
        if (jwt == null || !JwtUtils.verify(jwt)) {
            response.getWriter().write("{\"code\":401,\"message\":\"未登录，请先登录\"}");
            return false;
        }
        //
        Map<String, Object> data = JwtUtils.verifyAndGetInfo(jwt);
        if (data != null) {
            log.info("用户 {} 验证通过，放行", data.get("tel"));
        }
        return true;

    }
    //返回modelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");
    }
    //执行Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}

