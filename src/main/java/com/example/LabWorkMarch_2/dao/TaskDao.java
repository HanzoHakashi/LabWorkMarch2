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
                "    idOfAuthor  bigserial not null,\n" +
                "    foreign key (idOfAuthor) references usr (id),\n" +
                "    status  varchar default 'NEW'\n" +
                ");");
    }

    public List<Task> getTasksOfUser(Long id){
        String sql = "select id ,header,status,toDate " +
                "from tasks where idOfAuthor = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Task.class),id);
    }

    public Optional<Task> checkDescByID(Long id){
        String sql = "select id ,description " +
                "from tasks " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class), id)
        ));
    }

    public void addTask(Task task){
        String sql = "insert into tasks(header,description,toDate,idOfAuthor)"+
                "values(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,task.getHeader());
            ps.setString(2,task.getDescription());
            ps.setDate(3, Date.valueOf(task.getToDate()));
            ps.setLong(4, task.getIdOfAuthor());
            return ps;
        });
    }

    public void changeStatus(String status,Long idOfAuthor,Long id){
        String sql = "update tasks set status = ? where idOfAuthor = ? " +
                "and id = ?";
        jdbcTemplate.update(sql,status,idOfAuthor,id);
    }

    public void deleteAll() {
        String sql = "delete from tasks";
        jdbcTemplate.update(sql);
    }

}
