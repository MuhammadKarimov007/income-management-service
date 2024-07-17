package com.uzum.academy.incomeManagementService;

import com.uzum.academy.incomeManagementService.dao.UserDAO;
import com.uzum.academy.incomeManagementService.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserDAO userDAO) {
		return runner -> find(userDAO);
	}

	private void find(UserDAO userDAO) {
		User user = userDAO.findById(1);
		System.out.println(user);
	}

	private void createUser(UserDAO userDAO) {
		User user = new User(
				"Muhammad",
				"Karimov",
				"karimov.shermukhammad.tech@gmail.com",
				"18/06/2004",
				"male",
				5000.12
		);

		userDAO.save(user);
	}

}
