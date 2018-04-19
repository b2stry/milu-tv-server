package com.shallowan.milu.live.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybatis配置类
 *
 * @author ShallowAn
 */
@Configuration
public class MyBatisConfiguration {
    /**
     * 注册MyBatis分页插件PageHelper
     *
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        //System.out.println("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
