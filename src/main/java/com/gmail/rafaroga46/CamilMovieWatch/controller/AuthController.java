package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.LoginRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.request.UserRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.UserResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.User;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.UserMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camilmoviewatch/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);

        User user = (User) authenticate.getPrincipal();

    }
}
