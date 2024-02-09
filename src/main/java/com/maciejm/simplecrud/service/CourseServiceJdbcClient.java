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
        return jdbcClient.sql("SELECT course_id, title, description, link FROM course")
                         .query(Course.class)
                         .list();
    }

    @Override
    public void create(Course course) {
        jdbcClient.sql("INSERT INTO course(title, description, link) values(?,?,?)")
                .params(List.of(course.getTitle(), course.getDescription(), course.getLink()))
                .update();
    }

    @Override
    public Optional<Course> get(int id) {
        return jdbcClient.sql("SELECT course_id, title, description, link FROM course WHERE id = :id")
                .param("id",id)
                .query(Course.class)
                .optional();
    }

    @Override
    public void update(Course course, int id) {
        jdbcClient.sql("UPDATE course SET title = ?, description = ?, link = ? WHERE course_id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public void delete(int id) {
        jdbcClient.sql("DELETE FROM course WHERE course_id = :id")
                .param("id", id)
                .update();
    }
}
