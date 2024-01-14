package com.backend.sportC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.sportC.entity.Role;
import com.backend.sportC.repository.RoleRepository;

@SpringBootApplication
public class SportCApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SportCApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(1, "USER"));
		roles.add(new Role(2, "ADMIN"));
		roles.add(new Role(3, "FACILITY_OWNER"));

		roleRepository.saveAll(roles);
	}

}
