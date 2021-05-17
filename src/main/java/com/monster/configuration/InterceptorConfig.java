package com.monster.configuration;



import com.monster.common.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 将拦截器配置进spring
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行路径
        List<String> patterns = new ArrayList();
        patterns.add("/admin/login");
        patterns.add("/owner/login");
        patterns.add("/owner/register");
        patterns.add("/community/list");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").
                excludePathPatterns(patterns);
    }
}

