package com.library.libraryspringjpa.repositories;

import com.library.libraryspringjpa.entities.Role;
import com.library.libraryspringjpa.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    static Role findByName(RoleName name) {
        return null;
    }
}
