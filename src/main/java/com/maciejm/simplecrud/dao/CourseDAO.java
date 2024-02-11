package com.maciejm.simplecrud.dao;

import com.maciejm.simplecrud.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class CourseDAO implements DAO<Course> {

    private static final Logger logger = LoggerFactory.getLogger(CourseDAO.class);

    RowMapper<Course> rowMapper = (resultSet, rowNumber) -> {
        var course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setTitle(resultSet.getString("title"));
        course.setDescription(resultSet.getString("description"));
        course.setLink(resultSet.getString("link"));
        return course;
    };
    private final JdbcTemplate jdbcTemplate;

    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> findAll() {
        var sql = "SELECT course_id, title, description, link FROM course";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Course course) {
        var sql = "INSERT INTO course(title, description, link) values(?,?,?)";
        jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink());
    }

    @Override
    public Optional<Course> getById(int id) {
        var sql =  "SELECT course_id, title, description, link FROM course WHERE course_id = ?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            logger.info(String.format("Course with id %d was not found", id));
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void update(Course course, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
