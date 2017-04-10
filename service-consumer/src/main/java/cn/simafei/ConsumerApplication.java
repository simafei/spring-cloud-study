package cn.simafei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ConsumerApplication {

    @Autowired
    UserClient client;

    @RequestMapping("/")
    public int add() {
        return client.add(1, 2);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @FeignClient(name = "user-service")
    interface UserClient {
        @RequestMapping(value = "/add")
        int add(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b);
    }
}
