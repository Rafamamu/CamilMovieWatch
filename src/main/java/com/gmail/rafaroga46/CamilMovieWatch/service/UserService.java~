package com.gmail.rafaroga46.CamilMovieWatch.service;

import com.gmail.rafaroga46.CamilMovieWatch.entity.User;
import com.gmail.rafaroga46.CamilMovieWatch.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return  userRepository.save(user);
    }
}
