package com.library.libraryspringjpa.repositories;

import com.library.libraryspringjpa.entities.Category;
import com.library.libraryspringjpa.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
