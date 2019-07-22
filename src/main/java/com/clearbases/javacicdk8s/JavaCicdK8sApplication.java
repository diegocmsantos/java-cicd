package com.clearbases.javacicdk8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JavaCicdK8sApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JavaCicdK8sApplication.class, args);
	}

}
