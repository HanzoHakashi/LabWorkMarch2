package com.example.LabWorkMarch_2.dao;

import com.example.LabWorkMarch_2.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class UserDao extends BaseDao{
    private final PasswordEncoder passwordEncoder;
    protected UserDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, PasswordEncoder passwordEncoder) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists usr\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    username  varchar not null,\n" +
                "    email  varchar not null,\n" +
                "    password  varchar not null,\n" +
                "    role  varchar default 'quest',\n" +
                "    enabled BOOLEAN\n" +
                ");");
    }

    public void save(User user) {
        String sql = "insert into usr(username,email,password,role,enabled) " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3,this.passwordEncoder.encode(user.getPassword()));
            ps.setString(4,user.getRole());
            ps.setBoolean(5,user.getEnabled() );
            return ps;
        });
    }
}
