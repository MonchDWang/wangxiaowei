package cn.itcast;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {

    @Bean
    public Logger.Level akdshgfajsg(){
        return Logger.Level.FULL;
    }
}
