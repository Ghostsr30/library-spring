package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.DTO.AuthorDTO;
import com.library.libraryspringjpa.entities.Author;
import com.library.libraryspringjpa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorResource {

    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<Author>list = service.findAll();
        List<AuthorDTO> listDto = list.stream().map(AuthorDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id){
        Author obj = service.findById(id);
        return ResponseEntity.ok().body(new AuthorDTO(obj));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> insert(@RequestBody AuthorDTO dto) {
        Author newObj = service.fromDTO(dto);
        newObj = service.insert(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new AuthorDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        Author obj = service.fromDTO(dto);
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(new AuthorDTO(obj));
    }

}
