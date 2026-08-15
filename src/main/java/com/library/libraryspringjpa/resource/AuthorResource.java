package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.entities.Author;
import com.library.libraryspringjpa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorResource {

    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<List<Author>> findAll(){
        List<Author>list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        Author obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Author> insert(@RequestBody Author obj) {
        Author newObj = service.insert(obj);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author obj) {
        Author newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

}
