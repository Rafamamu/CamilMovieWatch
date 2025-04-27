package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.config.TokenService;
import com.gmail.rafaroga46.CamilMovieWatch.controller.request.LoginRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.request.UserRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.LoginResponse;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.UserResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.User;
import com.gmail.rafaroga46.CamilMovieWatch.exception.UserNameOrPasswordInvalidException;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.UserMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final TokenService tokenService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Operation(summary = "Registro de usuários",
            description = "Método responsável por registrar um novo usuário.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201",description = "Usuário Cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid  @RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));

    }


    @Operation(summary = "Login do usuário",
            description = "Método responsável por gerenciar o login do usuário",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204",description = "Cliente logado com sucesso.",
            content = @Content())
    @ApiResponse(responseCode = "403",description = "Usuário ou senha inválido.",content = @Content())
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        try{
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        }catch (BadCredentialsException e) {
            throw  new UserNameOrPasswordInvalidException("Usuário ou senha inválido.");
        }


    }
}
