package ru.dubovitsky.flashcardsspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.dubovitsky.flashcardsspring.model.AppUser;
import ru.dubovitsky.flashcardsspring.model.Role;
import ru.dubovitsky.flashcardsspring.service.AppUserService;

import java.util.ArrayList;

@SpringBootApplication
public class FlashcardsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashcardsSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService) { //Запускается, когда стартует приложение
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			appUserService.saveAppUser(new AppUser(null, "admin", "admin", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "user", "user", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "a", "a", new ArrayList<>()));

			appUserService.addRoleToAppUser("admin", "ROLE_ADMIN");
			appUserService.addRoleToAppUser("user", "ROLE_USER");
			appUserService.addRoleToAppUser("a", "ROLE_SUPER_ADMIN");
			appUserService.addRoleToAppUser("a", "ROLE_ADMIN");
			appUserService.addRoleToAppUser("a", "ROLE_USER");
		};
	}

}
