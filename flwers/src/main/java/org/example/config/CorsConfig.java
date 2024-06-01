package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

//    这是一个用于配置跨域资源共享（CORS）的类。在Web开发中，当一个网页通过JavaScript请求不属于该网页所在域的资源时，就会发起跨域请求。为了安全起见，浏览器会阻止跨域请求，但通过CORS可以在服务器端进行配置，以允许跨域请求。
//
//    这个类中的CorsConfig类定义了一个buildConfig方法，用于构建CORS配置信息。在这个方法中，设置了允许任何域名、任何头部和任何HTTP方法的请求。
//
//    接着，在corsFilter方法中，创建了一个CorsFilter实例，并将之前构建的CORS配置应用到所有路径上。这样就可以确保服务器允许来自任何域名的跨域请求。
//
//    总的来说，这个类的作用是设置服务器端的CORS配置，以便允许跨域请求。
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1容许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2容许任何头
        corsConfiguration.addAllowedMethod("*"); // 3容许任何方法（post、get等）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}