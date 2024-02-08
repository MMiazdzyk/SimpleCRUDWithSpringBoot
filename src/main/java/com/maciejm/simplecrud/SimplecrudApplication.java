package com.maciejm.simplecrud;

import com.maciejm.simplecrud.model.Course;
import com.maciejm.simplecrud.model.dao.DAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SimplecrudApplication {

	private static DAO<Course> dao;

	public SimplecrudApplication(DAO<Course> dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimplecrudApplication.class, args);

		System.out.println("\n Create new course -------------------------------------------- \n");
		var newCourse = new Course("new Course", "Course added from CourseJdbcDAO create method", "www.localhost.com");
		dao.create(newCourse);

		System.out.println("\n All courses -------------------------------------------- \n");
		List<Course> courses = dao.list();
		courses.forEach(System.out::println);
	}

}
