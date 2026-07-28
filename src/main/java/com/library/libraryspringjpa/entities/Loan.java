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
    private LocalDate date_loan;
    private LocalDate date_returnForecast;
    private LocalDate date_returnReal;

    public Loan(){
    }

    public Loan(Long id, LocalDate date_loan, LocalDate date_returnForecast, LocalDate date_returnReal) {
        this.id = id;
        this.date_loan = date_loan;
        this.date_returnForecast = date_returnForecast;
        this.date_returnReal = date_returnReal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_loan() {
        return date_loan;
    }

    public void setDate_loan(LocalDate date_loan) {
        this.date_loan = date_loan;
    }

    public LocalDate getDate_returnForecast() {
        return date_returnForecast;
    }

    public void setDate_returnForecast(LocalDate date_returnForecast) {
        this.date_returnForecast = date_returnForecast;
    }

    public LocalDate getDate_returnReal() {
        return date_returnReal;
    }

    public void setDate_returnReal(LocalDate date_returnReal) {
        this.date_returnReal = date_returnReal;
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
