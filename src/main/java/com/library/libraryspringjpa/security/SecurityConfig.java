package com.library.libraryspringjpa.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //liga o modo security do spring
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() { //declara o bycript como encoder oficial
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){  //conecta com o userDetailsService e o passwordEncoder e forma o mecanismo de autenticação
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //cada sessão se autentica sozinha, não guarda nada no servidor
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register").permitAll() //todo mundo tem acesso a rota de login
                        .requestMatchers(HttpMethod.GET, "/book/**",
                                "/book",
                                "/author",
                                "/categories",
                                "/author/**",
                                "/categories/**").authenticated()  //qualquer um que esteja logado consegue usar
                        .requestMatchers(HttpMethod.GET , "/loan/**",
                                "/loan",
                                "/users",
                                "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST , "/book/**",
                                "/author/**",
                                "/categories/**",
                                "/loan/**",
                                "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT , "/book/**",
                                "/author/**",
                                "/categories/**",
                                "/loan/**",
                                "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/book/**",
                                "/author/**",
                                "/categories/**",
                                "/loan/**",
                                "/users/**").hasRole("ADMIN") //só o admin tem acesso
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //onde conecta o filtro que fiz acima
        return http.build();
    }
}

