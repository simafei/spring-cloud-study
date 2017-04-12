package cn.simafei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author fengpj
 * @version 1.0
 * @since 2017/4/12
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getUser() {
        return jdbcTemplate.queryForObject("SELECT username from jpress_user limit 1", String.class);
    }
}
