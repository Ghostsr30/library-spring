package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.DTO.LoanDTO;
import com.library.libraryspringjpa.DTO.LoanInsertDTO;
import com.library.libraryspringjpa.entities.Loan;
import com.library.libraryspringjpa.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/loan")
public class LoanResource {

    @Autowired
    private LoanService service;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAll(){
        List<Loan> list = service.findAll();
        List<LoanDTO> listDto =  list.stream().map(LoanDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LoanDTO> findById(@PathVariable Long id){
        Loan obj = service.findById(id);
        return ResponseEntity.ok().body(new LoanDTO(obj));
    }

    @PostMapping
    public ResponseEntity<LoanDTO> insert(@RequestBody LoanInsertDTO dto) {
        try {
            Loan newObj = service.fromDto(dto);
            newObj = service.insert(newObj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
            return ResponseEntity.created(uri).body(new LoanDTO(newObj));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LoanDTO> update(@PathVariable Long id, @RequestBody LoanInsertDTO dto) {
        try {
            Loan newObj = service.fromDto(dto);
            newObj = service.update(id, newObj);
            return ResponseEntity.ok().body(new LoanDTO(newObj));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
