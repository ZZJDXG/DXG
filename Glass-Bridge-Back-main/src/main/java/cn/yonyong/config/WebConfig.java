package cn.yonyong.config;

import cn.yonyong.config.inteceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gugu
 */
//@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/users/login");

        // ✅ 你已经删掉 Info 模块，所以不再拦截 /Info/**
        // registry.addInterceptor(loginInterceptor()).addPathPatterns("/Info/**");

        // ✅ 如果你后续需要拦截“登录后才能访问”的接口/页面，把路径写到这里，例如：
        // registry.addInterceptor(loginInterceptor()).addPathPatterns("/department/**");
        // registry.addInterceptor(loginInterceptor()).addPathPatterns("/admin/**");

        // ✅ 如果你要放行登录/静态资源，可以用 exclude：
        // registry.addInterceptor(loginInterceptor())
        // .addPathPatterns("/**")
        // .excludePathPatterns("/login", "/tologin", "/toregister", "/resetPassword",
        // "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif",
        // "/bootstrap-3.3.7-dist/**", "/jquery/**", "/images/**");

    }

    // ✅ 不要继承 WebMvcConfigurationSupport，否则容易影响 SpringBoot 默认静态资源/模板解析
    // ✅ 你之前的静态资源映射先保留为注释（不删），需要时再启用
    /*
     * @Override
     * public void addResourceHandlers(ResourceHandlerRegistry registry) {
     * // 静态资源映射
     * registry.addResourceHandler("/").addResourceLocations("/**");
     * registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
     * ;
     * }
     */

//    @Bean
//    public LoginInterceptor loginInterceptor() {
//        return new LoginInterceptor();
//    }
}
