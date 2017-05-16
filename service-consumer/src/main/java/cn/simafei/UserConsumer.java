package cn.simafei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/10
 */
@SpringBootApplication
@RestController
@EnableFeignClients
@EnableEurekaClient
public class UserConsumer {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumer.class, args);
    }
}
