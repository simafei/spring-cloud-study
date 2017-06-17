package cn.simafei;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengpj
 *
 * @version 1.0
 * @since 2017/5/13
 */
@Controller
public class MonitorController {

    @RequestMapping("test")
    @ResponseBody
    public Map<String, String> test() {
        return new HashMap<String, String>() {{
           put("code", "200");
           put("message", "Hello, World");
        }};
    }
}
