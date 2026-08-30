package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.DTO.BookInsertDTO;
import com.library.libraryspringjpa.entities.Author;
import com.library.libraryspringjpa.entities.Book;
import com.library.libraryspringjpa.entities.Category;
import com.library.libraryspringjpa.repositories.AuthorRepository;
import com.library.libraryspringjpa.repositories.BookRepository;
import com.library.libraryspringjpa.repositories.CategoryRepository;
import com.library.libraryspringjpa.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Book fromDTO(BookInsertDTO dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYearPublication(dto.getYearPublication());

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + dto.getAuthorId()));
        book.setAuthor(author);

        for (Long categoryId : dto.getCategoryIds()){
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
            book.getCategories().add(category);
        }
        return book;
    }

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
        entity.setAuthor(obj.getAuthor());
        entity.getCategories().clear();
        entity.getCategories().addAll(obj.getCategories());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
