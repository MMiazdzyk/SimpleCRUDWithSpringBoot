package com.maciejm.simplecrud.service;

import com.maciejm.simplecrud.dao.CourseDAO;
import com.maciejm.simplecrud.model.Course;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseServiceJdbcClient implements CourseDAO {

    private final JdbcClient jdbcClient;

    public CourseServiceJdbcClient(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Course> list() {
        return null;
    }

    @Override
    public void create(Course course) {

    }

    @Override
    public Optional<Course> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Course course, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
