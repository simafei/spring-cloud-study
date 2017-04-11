package cn.simafei;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/11
 */
@FeignClient(name = "user-service")
public interface FeignConsumerClient {
    @RequestMapping(value = "/add")
    int add(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b);
}
