package cn.simafei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/10
 */
@RestController
@RibbonClient(name = "user-service")
@RefreshScope
public class RestConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${value}")
    private String value;

    @RequestMapping("/hi")
    public String hi() {
        int greeting = this.restTemplate.getForObject("http://user-service/add?a=1&b=6", int.class);
        return String.format("result = %d", greeting);
    }

    @RequestMapping("/user/key")
    public String getKey() {
        String key = this.restTemplate.getForObject("http://user-service/user/key", String.class);
        return String.format("%s=%s", key, value);
    }
}
