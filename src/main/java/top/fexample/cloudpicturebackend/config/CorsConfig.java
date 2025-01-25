package top.fexample.cloudpicturebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求
        registry.addMapping("/**")
                // 允许发送 Cookie
                .allowCredentials(true)
                // 放行哪些域名（必须用 patterns，否则 * 会和 allowCredentials 冲突）
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
        /*
          .allowCredentials(true)  // 允许发送 Cookie
                          .allowedOrigins("https://trusteddomain.com")  // 只允许特定的域名
                          .allowedMethods("GET", "POST")  // 只允许 GET 和 POST 方法
                          .allowedHeaders("Content-Type", "Authorization")  // 只允许常见请求头
                          .exposedHeaders("Authorization", "X-Request-Id");  // 只暴露必要的响应头
         */
    }
}