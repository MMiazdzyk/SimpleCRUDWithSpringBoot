package com.maciejm.simplecrud;

import com.maciejm.simplecrud.dao.DAO;
import com.maciejm.simplecrud.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SimplecrudApplication {

	private static DAO<Course> dao ;

	public SimplecrudApplication(DAO dao ){
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimplecrudApplication.class, args);

		List<Course> courses = dao.findAll();
		System.out.println("\n All courses -------------------------------------------- \n");
		courses.forEach(System.out::println);
	}

}
