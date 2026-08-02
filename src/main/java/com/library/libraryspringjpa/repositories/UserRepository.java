package com.library.libraryspringjpa.repositories;

import com.library.libraryspringjpa.entities.Loan;
import com.library.libraryspringjpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
