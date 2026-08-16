package com.library.libraryspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_user")
public class User implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    public User(){
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Loan> loans = new HashSet<>();

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() { //retorna a senha do usuario
        return password;
    }

    @Override
    public String getUsername() { //retorna o email do usuario
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { //verifica se a conta do usuario não expirou
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //verifica se a conta do usuario não está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //verifica se as credenciais do usuario não expiraram
        return true;
    }

    @Override
    public boolean isEnabled() { //verifica se a conta do usuario está habilitada
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Loan> getLoans(){
        return loans;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
