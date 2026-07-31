package com.library.libraryspringjpa.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_book")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer yearPublication;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    public Book(){
    }

    @OneToMany(mappedBy = "book")
    private Set<Loan> loans = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_book_categories", joinColumns = @JoinColumn(name = "id_book"), inverseJoinColumns = @JoinColumn(name = "id_categories"))
    private Set<Category> categories = new HashSet<>();

    public Book(Long id, String title, Integer yearPublication) {
        this.id = id;
        this.title = title;
        this.yearPublication = yearPublication;
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

    public Author getAuthor(Author author){
        return author;
    }

    public Set<Category> getCategories(){
        return categories;
    }

    public Set<Loan> getLoans(){
        return loans;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
