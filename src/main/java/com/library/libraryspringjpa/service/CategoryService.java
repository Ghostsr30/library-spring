package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.DTO.CategoryDTO;
import com.library.libraryspringjpa.entities.Category;
import com.library.libraryspringjpa.repositories.CategoryRepository;
import com.library.libraryspringjpa.service.exceptions.DatabaseException;
import com.library.libraryspringjpa.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category fromDTO(CategoryDTO dto){
        Category cat = new Category();
        cat.setName(dto.getName());
        return cat;
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

    public Category insert(Category obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    public Category update(Long id, Category obj) {
        try {
            Category entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
                throw new ResourceNotFoundException(id);
            }
    }

    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }
}
