package com.maciejm.simplecrud.controller;

import com.maciejm.simplecrud.dao.CourseDAO;
import com.maciejm.simplecrud.model.Course;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseDAO courseServiceJdbcTemplate;

    public CourseController(CourseDAO courseServiceJdbcTemplate) {
        this.courseServiceJdbcTemplate = courseServiceJdbcTemplate;
    }

    @GetMapping("/")
    List<Course> findAll() {
        return courseServiceJdbcTemplate.list();
    }



}
