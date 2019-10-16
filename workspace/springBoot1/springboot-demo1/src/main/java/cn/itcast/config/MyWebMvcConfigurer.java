package cn.itcast.config;

import cn.itcast.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

//    来自于WebMvcConfigurer中
   public void addInterceptors(InterceptorRegistry registry){
       registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/**");
   }

}
