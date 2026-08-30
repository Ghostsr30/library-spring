package com.library.libraryspringjpa.DTO;

import com.library.libraryspringjpa.entities.Book;

public class BookDTO {

    private Long id;
    private String title;
    private Integer yearPublication;

    public BookDTO(){
        super();
    }

    public BookDTO(Long id, String title, Integer yearPublication) {
        this.id = id;
        this.title = title;
        this.yearPublication = yearPublication;
    }

    public BookDTO(Book book){
        id = book.getId();
        title = book.getTitle();
        yearPublication = book.getYearPublication();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(Integer yearPublication) {
        this.yearPublication = yearPublication;
    }
}
