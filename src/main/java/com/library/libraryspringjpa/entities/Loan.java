package com.library.libraryspringjpa.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_loan")
public class Loan implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateLoan;
    private LocalDate dateReturnForecast;
    private LocalDate dateReturnReal;

    public Loan(){
    }

    public Loan(Long id, LocalDate dateLoan, LocalDate dateReturnForecast, LocalDate dateReturnReal) {
        this.id = id;
        this.dateLoan = dateLoan;
        this.dateReturnForecast = dateReturnForecast;
        this.dateReturnReal = dateReturnReal;
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

    public void setDate_loan(LocalDate dateLoan) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
