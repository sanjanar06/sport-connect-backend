package com.backend.sportC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.sportC.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
