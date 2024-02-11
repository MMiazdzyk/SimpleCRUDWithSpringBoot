package com.maciejm.simplecrud.dao;

import com.maciejm.simplecrud.model.Course;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("client")
public class CourseDaoJDBCClient implements CourseDao {

    private final JdbcClient jdbcClient;

    public CourseDaoJDBCClient(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Course> findAll() {
        return jdbcClient.sql("SELECT course_id, title, description, link FROM course")
                         .query(Course.class)
                         .list();
    }

    @Override
    public void create(Course course) {

    }

    @Override
    public Optional<Course> getById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Course course, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
