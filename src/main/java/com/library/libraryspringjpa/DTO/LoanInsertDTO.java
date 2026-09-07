package com.library.libraryspringjpa.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class LoanInsertDTO {

    @PastOrPresent(message = "Field loan date it not valid!")
    private LocalDate loanDate;

    @FutureOrPresent(message = "Date it not valid!")
    private LocalDate dateReturnForecast;

    @FutureOrPresent(message = "Date it not valid!")
    private LocalDate dateReturnReal;

    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id it's not valid, need be format UUID ")
    private Long userId;

    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Id it's not valid, need be format UUID ")
    private Long bookId;

    public LoanInsertDTO(){
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
