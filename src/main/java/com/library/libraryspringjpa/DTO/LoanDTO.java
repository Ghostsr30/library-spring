package com.library.libraryspringjpa.DTO;


import com.library.libraryspringjpa.entities.Loan;

import java.time.LocalDate;

public class LoanDTO {

    private Long id;
    private LocalDate dateLoan;
    private LocalDate dateReturnForecast;
    private LocalDate dateReturnReal;

    public LoanDTO(){
        super();
    }

    public LoanDTO(Long id, LocalDate dateLoan, LocalDate dateReturnForecast, LocalDate dateReturnReal) {
        this.id = id;
        this.dateLoan = dateLoan;
        this.dateReturnForecast = dateReturnForecast;
        this.dateReturnReal = dateReturnReal;
    }

    public LoanDTO(Loan loan){
        id = loan.getId();
        dateLoan = loan.getDateLoan();
        dateReturnForecast = loan.getDateReturnForecast();
        dateReturnReal = loan.getDateReturnReal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(LocalDate dateLoan) {
        this.dateLoan = dateLoan;
    }

    public LocalDate getDateReturnForecast() {
        return dateReturnForecast;
    }

    public void setDateReturnForecast(LocalDate dateReturnForecast) {
        this.dateReturnForecast = dateReturnForecast;
    }

    public LocalDate getDateReturnReal() {
        return dateReturnReal;
    }

    public void setDateReturnReal(LocalDate dateReturnReal) {
        this.dateReturnReal = dateReturnReal;
    }
}
