package com.library.libraryspringjpa.service;

import com.library.libraryspringjpa.DTO.LoanInsertDTO;
import com.library.libraryspringjpa.entities.Book;
import com.library.libraryspringjpa.entities.Loan;
import com.library.libraryspringjpa.entities.User;
import com.library.libraryspringjpa.repositories.BookRepository;
import com.library.libraryspringjpa.repositories.LoanRepository;
import com.library.libraryspringjpa.repositories.UserRepository;
import com.library.libraryspringjpa.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Loan fromDto(LoanInsertDTO dto){
        Loan loan = new Loan();
        loan.setDateLoan(dto.getLoanDate());
        loan.setDateReturnForecast(dto.getDateReturnForecast());
        loan.setDateReturnReal(dto.getDateReturnReal());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found id:" + dto.getUserId()));
        loan.setUser(user);

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found id:" + dto.getBookId()));
        loan.setBook(book);

        return loan;
    }

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
        entity.setDateLoan(obj.getDateLoan());
        entity.setDateReturnForecast(obj.getDateReturnForecast());
        entity.setDateReturnReal(obj.getDateReturnReal());
    }
}
