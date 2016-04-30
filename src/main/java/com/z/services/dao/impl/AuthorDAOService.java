/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.services.dao.impl;

import com.z.models.Author;
import com.z.services.dao.AuthorDao;
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
//Service indica a spring que combierta la clase en Bean, para que otras clases puedan acceder a la instancia
@Service
public class AuthorDAOService  implements AuthorDao{
    //inyección de SessionFactory (creado desde mvc-dispatche-servlet.xml)
    @Autowired
    private SessionFactory sessionFactory;
    
    
    @Override
    /*Transactional indica al gestor que se realizará una nueva transacción a la BD,
    se le puede indicar atributos como si es de solo lectura,
    el gestor por si solo se encarga de abrir la session y cerrarla, por eso al usar getCurrentSession() no devuelve Null,
    si no se usará el gestor usar getCurrentSession daria null*/
    @Transactional(readOnly = false)
    public Serializable save(Author author) {return sessionFactory.getCurrentSession().save(author);}
    
    
    @Override
    @Transactional(readOnly = false)
    public void update(Author author) {sessionFactory.getCurrentSession().update(author);}
    
    
    @Override
    @Transactional(readOnly = false)
    public void delete(Author author) {sessionFactory.getCurrentSession().delete(author);}
    
    
    @Override
    @Transactional(readOnly = true)
    public Author findByName(String name) {
        String hqlQuery = "from Author WHERE name=:name";
        Query query = sessionFactory.getCurrentSession().createQuery(hqlQuery);
        query.setParameter("name", name);
        return (Author)query.uniqueResult();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {return (Author)sessionFactory.getCurrentSession().get(Author.class, id);}
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Author> list() {
        return sessionFactory.getCurrentSession().createQuery("from Author").list();
    }
    
}
