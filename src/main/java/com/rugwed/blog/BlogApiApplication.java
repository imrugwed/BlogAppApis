package com.rugwed.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rugwed.blog.config.AppConstants;
import com.rugwed.blog.entity.Role;
import com.rugwed.blog.repository.RolesRepo;

import java.util.*;

@SpringBootApplication
@EnableWebMvc
//(exclude = {DataSourceAutoConfiguration.class })
public class BlogApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolesRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(this.passwordEncoder.encode("123456"));

		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role2 = new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName("NORMAL_USER");

			List<Role> roles = List.of(role, role2);
			List<Role> saveAll = this.repo.saveAll(roles);

			saveAll.forEach(r -> {
				System.out.println(r.getName());
			});
		} catch (Exception e) {

		}
	}

}
