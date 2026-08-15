package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.entities.Book;
import com.library.libraryspringjpa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> findAll(){
        return repository.findAll();
    }

    public Book findById(Long id){
        Optional<Book> obj = repository.findById(id);
        return obj.get();
    }

    public Book insert(Book obj) {
        return repository.save(obj);
    }

    public Book update(Long id, Book obj) {
        Book entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Book entity, Book obj) {
        entity.setTitle(obj.getTitle());
        entity.setYearPublication(obj.getYearPublication());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
