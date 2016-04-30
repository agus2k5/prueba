/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "year")
    private Integer year;

    @Column(name = "title")
    private String title;
    @JsonIgnore //ignorar la lista de autores para el json, sino entra en buble infinito autor->libro->autor->libro....
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "libros",cascade = CascadeType.PERSIST)
    private List<Author> autores = new ArrayList<Author>();
   
    public Book() {}

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
    
    public List<Author> getAutores() {
        return autores;
    }
    
    public void setAutores(List<Author> autores) {
        this.autores = autores;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void quitarAuthor(Author b){
        for (Author autor : autores) {
            if(autor.getId()==b.getId()){
                getAutores().remove(autor);
                break;
            }
        }
    }
    
    @Override
    public String toString(){
        return getId()+"\t"+getIsbn()+"\t"+getTitle()+"\t"+getYear();
    }
}
