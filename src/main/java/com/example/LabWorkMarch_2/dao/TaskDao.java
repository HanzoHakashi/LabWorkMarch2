package com.example.LabWorkMarch_2.dao;

import com.example.LabWorkMarch_2.entity.Task;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDao extends BaseDao{
    protected TaskDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists tasks\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    header  varchar not null,\n" +
                "    description  varchar not null,\n" +
                "    toDate DATE not null,\n" +
                "    emailOfAuthor  varchar not null,\n" +
                "    foreign key (emailOfAuthor) references usr (email),\n" +
                "    status  varchar default 'NEW'\n" +
                ");");
    }

    public List<Task> getTasksOfUser(String email){
        String sql = "select id," +
                "header,status,toDate" +
                "from tasks where emailOfAuthor = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Task.class),email);
    }

    public Optional<Task> checkDescByID(Long id){
        String sql = "select description " +
                "from tasks " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class), id)
        ));
    }

    public void addTask(Task task){
        String sql = "insert into tasks(header,description,toDate,email)"+
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,task.getHeader());
            ps.setString(2,task.getDescription());
            ps.setDate(3, Date.valueOf(task.getToDate()));
            ps.setString(4, task.getEmailOfAuthor());
            return ps;
        });
    }

    public void changeStatus(String status,String emailOfAuthor){
        String sql = "update tasks set status = ? where emailOfAuthor = ?";
        jdbcTemplate.update(sql,status,emailOfAuthor);
    }

}
