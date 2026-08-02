package com.library.libraryspringjpa.repositories;

import com.library.libraryspringjpa.entities.Author;
import com.library.libraryspringjpa.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
