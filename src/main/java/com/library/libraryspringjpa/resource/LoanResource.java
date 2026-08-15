package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.entities.Loan;
import com.library.libraryspringjpa.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/loan")
public class LoanResource {

    @Autowired
    private LoanService service;

    @GetMapping
    public ResponseEntity<List<Loan>> findAll(){
        List<Loan> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Loan> findById(@PathVariable Long id){
        Loan obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Loan> insert(@RequestBody Loan obj) {
        Loan newObj = service.insert(obj);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Loan> update(@PathVariable Long id, @RequestBody Loan obj) {
        Loan newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

}
