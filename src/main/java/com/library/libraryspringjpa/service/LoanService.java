package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.entities.Loan;
import com.library.libraryspringjpa.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    public List<Loan> findAll(){
        return repository.findAll();
    }

    public Loan findById(Long id){
        Optional<Loan> obj = repository.findById(id);
        return obj.get();
    }

    public Loan insert(Loan obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Loan update(Long id, Loan obj) {
        Loan entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Loan entity, Loan obj) {
        entity.setDate_loan(obj.getDateLoan());
        entity.setDateReturnForecast(obj.getDateReturnForecast());
        entity.setDateReturnReal(obj.getDateReturnReal());
    }
}
