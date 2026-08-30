package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.DTO.BookDTO;
import com.library.libraryspringjpa.DTO.BookInsertDTO;
import com.library.libraryspringjpa.entities.Book;
import com.library.libraryspringjpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookResource {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(){
        List<Book> list = service.findAll();
        List<BookDTO> listDto = list.stream().map(BookDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        Book obj = service.findById(id);
        return ResponseEntity.ok().body(new BookDTO(obj));
    }

    @PostMapping
    public ResponseEntity<BookDTO> insert(@RequestBody BookInsertDTO dto){
        try{
            Book obj = service.fromDTO(dto);
            obj = service.insert(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).body(new BookDTO(obj));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookInsertDTO dto){
        try{
            Book obj = service.fromDTO(dto);
            obj = service.update(id, obj);
            return ResponseEntity.ok().body(new BookDTO(obj));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
