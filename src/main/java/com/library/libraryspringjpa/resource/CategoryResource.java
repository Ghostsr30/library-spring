package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.DTO.CategoryDTO;
import com.library.libraryspringjpa.entities.Category;
import com.library.libraryspringjpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<Category> list = service.findAll();
        List<CategoryDTO> listDto = list.stream().map(CategoryDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(new CategoryDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
        Category newObj = service.fromDTO(dto);
        newObj = service.insert(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoryDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        Category newObj = service.fromDTO(dto);
        newObj = service.update(id, newObj);
        return ResponseEntity.ok().body(new CategoryDTO(newObj));
    }
}
