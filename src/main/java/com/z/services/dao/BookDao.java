/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao;

import com.z.models.Book;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mariano
 */
public interface BookDao {
   public Serializable save(Book book);
    public void update(Book book);
    public void delete(Book book);
    public Book findByIsbn(String isbn);
    public Book findById(Long id);
    public List<Book> list();

}
