package com.maciejm.simplecrud.model.dao;

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
public class CourseJdbcDAO implements DAO<Course> {

    private static final Logger logger = LoggerFactory.getLogger(CourseJdbcDAO.class);
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Course> rowMapper = (resultSet, rowNumber) -> {
        var course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setTitle(resultSet.getString("title"));
        course.setDescription(resultSet.getString("description"));
        course.setLink(resultSet.getString("link"));
        return course;
    };

    public CourseJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> list() {
        var sql = "SELECT course_id, title, description, link FROM course";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Course course) {
        var sql = "INSERT INTO course(title, description, link) values(?,?,?)";
        int insert = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getLink());
        if (insert == 1) {
            logger.info("New course have been added: " + course.getTitle());
        }

    }

    @Override
    public Optional<Course> get(int id) {
        var sql = "SELECT * FROM course WHERE course_id = ?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException ex) {
            logger.info(String.format( "Course with id %d was not found", id));
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
