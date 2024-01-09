package com.backend.sportC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.sportC.entity.User;

public interface TestRepository extends JpaRepository<User, Integer> {
}
