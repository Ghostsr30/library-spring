package com.library.libraryspringjpa.resource;

import com.library.libraryspringjpa.entities.Loan;
import com.library.libraryspringjpa.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
