/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao;

import com.z.models.Author;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mariano
 */
//nada nuevo
public interface AuthorDao {
    public Serializable save(Author author);
    public void update(Author author);
    public void delete(Author author);
    public Author findByName(String name);
    public Author findById(Long id);
    public List<Author> list();
}