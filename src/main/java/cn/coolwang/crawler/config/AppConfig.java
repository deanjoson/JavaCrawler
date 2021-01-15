package cn.coolwang.crawler.config;

import cn.coolwang.crawler.extend.mybatis.CustomerSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerSqlInjector customerSqlInjector(){
        return new CustomerSqlInjector();
    }
}
