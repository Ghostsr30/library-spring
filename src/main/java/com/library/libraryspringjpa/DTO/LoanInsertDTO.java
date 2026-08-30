package com.library.libraryspringjpa.DTO;

import java.time.LocalDate;

public class LoanInsertDTO {

    private LocalDate loanDate;
    private LocalDate dateReturnForecast;
    private LocalDate dateReturnReal;
    private Long userId;
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
