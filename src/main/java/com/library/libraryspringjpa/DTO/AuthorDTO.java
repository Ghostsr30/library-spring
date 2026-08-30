package com.library.libraryspringjpa.DTO;

import com.library.libraryspringjpa.entities.Author;

public class AuthorDTO {

    private Long id;
    private String name;
    private String nationality;

    public AuthorDTO(){
        super();
    }

    public AuthorDTO(Long id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public AuthorDTO(Author author){
        id = author.getId();
        name = author.getName();
        nationality = author.getNationality();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
