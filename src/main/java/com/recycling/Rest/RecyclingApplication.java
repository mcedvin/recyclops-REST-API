package com.recycling.Rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories(basePackages = "com.recycling.DB.repository")
@EntityScan(basePackages = {"com.recycling.production"})
//@EnableJpaRepositories(basePackages = "com.recycling.Rest.repository")

//@ComponentScan(basePackages = {"com.recycling.Rest.Controller"})
@EnableScheduling
//TODO: hämta datum, ifall datum är över current date/time, uppdatera datumet som hämtats och öka med två/en dagar
@SpringBootApplication


public class RecyclingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecyclingApplication.class, args);
	}

}
