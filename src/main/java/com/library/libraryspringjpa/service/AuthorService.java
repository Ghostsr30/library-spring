package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.entities.Author;
import com.library.libraryspringjpa.repositories.AuthorRepository;
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
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public List<Author>findAll(){
        return repository.findAll();
    }

    public Author findById(Long id){
        Optional<Author> obj = repository.findById(id);
        return obj.get();
    }

    public Author insert(Author obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Author update(Long id, Author obj) {
        try {
            Author entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Author entity, Author obj) {
        entity.setName(obj.getName());
        entity.setNationality(obj.getNationality());
    }

}
