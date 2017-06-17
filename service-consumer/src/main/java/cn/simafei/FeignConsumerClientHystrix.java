package cn.simafei;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/5/16
 */
@Component
public class FeignConsumerClientHystrix implements FeignConsumerClient {

    @Override
    public User getUser(@PathVariable("id") Long id) {
        return new User();
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<User>();
    }
}

