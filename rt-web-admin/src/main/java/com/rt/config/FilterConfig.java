package com.rt.config;

import com.rt.common.web.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @ClassName: FilterConfig
 * @Description: Filter配置
 * @author: <a href="edeis@163.com">edeis</a>
 * @version: V1.0.0
 * @date: 2017-12-20
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        XssFilter xssFilter = new XssFilter();
//        xssFilter.setUrlExclusion(Arrays.asList("/notice/update","/notice/add"));
        xssFilter.setUrlExclusion(Arrays.asList("/admin/parkinglot/healRecover/add","/admin/parkinglot/healRecover/update"));
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(xssFilter);
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        return registration;
    }
}
