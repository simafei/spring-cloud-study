package cn.simafei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    @Autowired
    FeignConsumerClient client;

    @Value("${consumer.key}")
    private String consumerKey;

    @RequestMapping("/hi")
    public String hi() {
        return consumerKey;
    }

    @RequestMapping(value = "/user/${id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://user-service/user/" + id, User.class);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return client.getUsers();
    }
}
