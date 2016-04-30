/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao.impl;

import com.z.models.Book;
import com.z.services.dao.BookDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mariano
 */
@Service
public class BookDAOService implements BookDao{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional(readOnly = false)
    public Serializable save(Book book) {
        return sessionFactory.getCurrentSession().save(book);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findByIsbn(String isbn) {
        String hqlQuery = "from Book WHERE isbn=:isbn";
        Query query = sessionFactory.getCurrentSession().createQuery(hqlQuery);
        query.setParameter("isbn", isbn);
        return (Book)query.uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return (Book)sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> list() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }
    
}
