package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.DTO.LoginRequestDTO;
import com.library.libraryspringjpa.DTO.LoginResponseDTO;
import com.library.libraryspringjpa.DTO.RegisterRequestDTO;
import com.library.libraryspringjpa.entities.Role;
import com.library.libraryspringjpa.entities.RoleName;
import com.library.libraryspringjpa.entities.User;
import com.library.libraryspringjpa.repositories.RoleRepository;
import com.library.libraryspringjpa.repositories.UserRepository;
import com.library.libraryspringjpa.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);

        return new LoginResponseDTO(token);  //rensponsavel por fazer  o login e gerar um token para o usuario
    }

    public void register(RegisterRequestDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); //senha é encodada no banco de dados

        Role defaultRole = RoleRepository.findByName(RoleName.USER);
        user.getRoles().add(defaultRole);

        userRepository.save(user); //responsavel por registrar o usuario e salvar no banco de dados suas informações
    }
}
