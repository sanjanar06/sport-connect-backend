package com.backend.sportC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.sportC.entity.User;
import com.backend.sportC.repository.TestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public List<User> findUsers() {
        return testRepository.findAll();
    }
}
