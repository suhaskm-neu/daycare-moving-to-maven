package com.edu.neu.csye6200;


import com.edu.neu.csye6200.controllers.AdminDashboardController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DaycareApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(DaycareApplication.class).headless(false).run(args);
		context.getBean(AdminDashboardController.class);
	}

}
