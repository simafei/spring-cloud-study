package cn.simafei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    List<User> getUsers() {
        return jdbcTemplate.query("SELECT id, username from t_user",
                new BeanPropertyRowMapper<>(User.class));
    }

    User getUser(Long id) {
        return jdbcTemplate.queryForObject("SELECT id, username from t_user where id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }
}
