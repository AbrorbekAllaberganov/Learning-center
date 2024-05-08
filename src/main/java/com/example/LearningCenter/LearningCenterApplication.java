package com.example.LearningCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class LearningCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningCenterApplication.class, args);
		Date date=new Date(2024, Calendar.MARCH,20);
		System.out.println(new Date().getTime());
		System.out.println(date.getTime());
	}

}
