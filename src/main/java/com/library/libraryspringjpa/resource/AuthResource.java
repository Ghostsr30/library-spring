package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.DTO.LoginRequestDTO;
import com.library.libraryspringjpa.DTO.LoginResponseDTO;
import com.library.libraryspringjpa.DTO.RegisterRequestDTO;
import com.library.libraryspringjpa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO)); //pede a requisição do login
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO requestDTO){
        authService.register(requestDTO);
        return ResponseEntity.ok().build(); //pede a requisição do register
    }


}
