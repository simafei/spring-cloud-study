package cn.simafei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/10
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class UserConsumer {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumer.class, args);
    }
}
