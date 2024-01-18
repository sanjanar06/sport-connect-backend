package com.backend.sportC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.sportC.entity.Role;
import com.backend.sportC.entity.User;
import com.backend.sportC.repository.RoleRepository;
import com.backend.sportC.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SportCApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

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

		User user = new User(1, "admin", "admin@safe", passwordEncoder.encode("admin123"),
				roleRepository.findByType("ADMIN").orElseThrow(),
				null);
		userRepository.save(user);
	}

}
