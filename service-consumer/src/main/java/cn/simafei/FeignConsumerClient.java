package cn.simafei;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/11
 */
@FeignClient(name = "user-service", fallback = FeignConsumerClientHystrix.class)
@Component
public interface FeignConsumerClient {
    @RequestMapping(value = "/user/{id}")
    User getUser(@PathVariable("id") Long id);

    @RequestMapping(value = "/users")
    List<User> getUsers();
}