package com.sam.pose.config;

import com.sam.pose.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         InterceptorRegistration loginReg = registry.addInterceptor(new LoginInterceptor());
        loginReg.addPathPatterns("/**");
        loginReg.excludePathPatterns("/login","/loginUser", "/error", "/reg","configuration","/front/**","/addUser");
        super.addInterceptors(registry);
    }

}
