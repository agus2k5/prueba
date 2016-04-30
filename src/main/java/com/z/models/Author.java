/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Mariano
 */
@Entity
@Table(name = "author")
public class Author implements java.io.Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "authorbooks", //tabla intermedia
            joinColumns = { 
			@JoinColumn(name = "author_id")}, //columna para hacer el join entre author y authorbooks 
			inverseJoinColumns = { @JoinColumn(name = "book_id")})//columna de authorbooks que permite la relación con book
    List<Book> libros = new ArrayList<Book>();
    
    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public List<Book> getLibros() {
        return libros;
    }

    public void setLibros(List<Book> libros) {
        this.libros = libros;
    }
    
    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }
    public void quitarLibro(Book b){
        for (Book libro : libros) {
            if(libro.getId()==b.getId()){
                this.getLibros().remove(libro);
                break;
            }
        }
    }
    @Override
    public final String toString() {
        String result = "Author [";
        if (this.id != null) {
            result = result + "dbid=" + Long.toString(id) + " ";
        }
        if (this.name != null) {
            result = result + "name=" + name + " ";
        }
        result = result + "]";
        return result;
    }
}