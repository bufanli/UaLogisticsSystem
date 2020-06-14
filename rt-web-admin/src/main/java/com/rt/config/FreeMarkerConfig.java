package com.rt.config;

import com.rt.common.shiro.freemarker.ShiroTags;
import com.rt.common.utils.FreeMarkerViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreeMarkerConfig {
    private static final Logger logger = LoggerFactory.getLogger(FreeMarkerConfig.class);
    @Autowired
    private freemarker.template.Configuration configuration;
    @PostConstruct
    public void setSharedVariable() {
        try {
// 设置标签类型([]、<>),[]这种标记解析要快些 默认是自动检测语法
//  自动 AUTO_DETECT_TAG_SYNTAX
//  尖括号 ANGLE_BRACKET_TAG_SYNTAX
//  方括号 SQUARE_BRACKET_TAG_SYNTAX
            configuration.setTagSyntax(freemarker.template.Configuration.SQUARE_BRACKET_TAG_SYNTAX);
            configuration.setSharedVariable("shiro", new ShiroTags());
            configuration.setNumberFormat("#");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
//        System.out.println("MvcConfig.freeMarkerViewResolver()");
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setRequestContextAttribute("request");  //在这里配置
        logger.debug("自定义FreeMarkerViewResolver加载完成");
        return resolver;
    }
}
