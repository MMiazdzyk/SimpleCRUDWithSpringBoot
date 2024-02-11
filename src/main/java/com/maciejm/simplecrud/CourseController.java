package com.maciejm.simplecrud;


import com.maciejm.simplecrud.dao.CourseDAO;
import com.maciejm.simplecrud.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final CourseDAO courseDAO;

    public CourseController(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @GetMapping("/")
    List<Course> findAll() {
        return courseDAO.findAll();
    }

    @GetMapping("/{id}")
    Course getById(@PathVariable int id) {
        return courseDAO.getById(id).get();
    }

    @PostMapping("/")
    Course create(@RequestBody Course course) {
        courseDAO.create(course);
        return course;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(@RequestBody Course course, @PathVariable int id) {
        courseDAO.update(course, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        courseDAO.delete(id);
    }
}