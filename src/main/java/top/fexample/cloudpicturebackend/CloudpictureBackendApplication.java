package top.fexample.cloudpicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("top.fexample.cloudpicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class CloudpictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudpictureBackendApplication.class, args);
    }

}
