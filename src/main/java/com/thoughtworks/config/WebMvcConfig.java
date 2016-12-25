package com.thoughtworks.config;

import com.thoughtworks.web.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login").setViewName("login");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/")
                        .setCachePeriod(36000000);
                registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(36000000);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true)
                        .allowedOrigins("*").allowedMethods("POST", "DELETE");
            }
        };
    }
}
