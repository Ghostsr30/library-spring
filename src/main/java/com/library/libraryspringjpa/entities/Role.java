package com.library.libraryspringjpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    //role basicamente é o que seta a permissão para o usuario, distuinguindo o que ele pode ou não fazer

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return "ROLE_" + name.name();
    }

    public Role(){
    }

    public Role(Integer id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<User> getUsers(){
        return users;
    }
}
