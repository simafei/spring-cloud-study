package cn.simafei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/10
 */
@RestController
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${key}")
    private String key;

    @RequestMapping("/user/key")
    public String getKey() {
        return key;
    }

    @RequestMapping("/user/get")
    public String getUser() {
        return userService.getUser();
    }

    @RequestMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
}
