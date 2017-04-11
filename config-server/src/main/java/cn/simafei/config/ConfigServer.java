package cn.simafei.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by IntelliJ IDEA.
 * @see <a href="https://spring.io/guides/gs/centralized-configuration/">centralized-configuration</a>
 * @author fengpj
 * @version 1.0
 * @since 2017/4/11
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }
}
