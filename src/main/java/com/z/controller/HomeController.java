/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z.controller;

import com.z.models.Author;
import com.z.models.Book;
import com.z.models.Mensaje;
import com.z.services.dao.AuthorDao;
import com.z.services.dao.BookDao;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mariano
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    /*inyección de dependencia, en tiempo de ejecución buscará un Bean de la clase AuthorDao(en caso de encontrar varios dara error), 
     AuthorDAOService posee la anotación Service, lo cual indica que es un Bean, 
     y como implementa la interfaz AuthorDao, significa que AuthorDAOService puede ser generalizado a AuthorDao, casteo implicito*/

    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "index";
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "insertAuthor", method = RequestMethod.GET)
    public String insertAuthor() {
        return "fragments/insertAuthor";
    }

    @RequestMapping(value = "listAuthor", method = RequestMethod.GET)
    public ModelAndView listAuthor() {
        ModelAndView view = new ModelAndView("fragments/listAuthor");
        view.addObject("autores", autores());
        return view;
    }

    /**
     * *****************************AUTORES************************************
     */
    //@ResponseBody=Json (para el mapeo automatico es necesaria la lib Jackson "pom.xml")
    @RequestMapping(value = "autores", method = RequestMethod.GET)
    public @ResponseBody List<Author> autores(){return authorDao.list();}

    //@RequestParam solicitará nombre como parametro del POST en forma obligatoria
    @RequestMapping(value = "autores/insert", method = RequestMethod.POST)
    public @ResponseBody Mensaje test_insert(@RequestParam("nombre") String nombre) {
        System.out.println("Nombre: "+nombre);
        Mensaje mensaje = new Mensaje();
        mensaje.titulo = "Insert autor: " + nombre;
        try {
            Author a = new Author(nombre);
            authorDao.save(a);
            mensaje.mensaje = a.toString();
        } catch (ConstraintViolationException e) {
            mensaje.mensaje = e.getCause().getMessage();
        }
        return mensaje;
    }

    @RequestMapping(value = "autores/delete/{id}", method = RequestMethod.GET)
    public ModelAndView test_delete(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("autores");
        Author a = authorDao.findById(id);
        if (a != null) {
            try {
                authorDao.delete(a);
                view.addObject("mensaje", "Delete: " + a.toString());
            } catch (DataIntegrityViolationException e) {
                view.addObject("error", "Posee FK");
            }
        } else {
            view.addObject("error", "No hay Autor con id: " + id);
        }
        view.addObject("autores", authorDao.list());
        return view;
    }

    @RequestMapping(value = "autores/deleteByNombre/{nombre}", method = RequestMethod.GET)
    public ModelAndView test_delete(@PathVariable("nombre") String nombre) {
        ModelAndView view = new ModelAndView("autores");
        Author a = authorDao.findByName(nombre);
        if (a != null) {
            try {
                authorDao.delete(a);
                view.addObject("mensaje", "Delete: " + a.toString());
            } catch (DataIntegrityViolationException e) {
                view.addObject("error", "Posee FK");
            }
        } else {
            view.addObject("error", "No hay Autor con el nombre: " + nombre);
        }
        view.addObject("autores", authorDao.list());
        return view;
    }

    @RequestMapping(value = "autores/update/{id}/{nombre}", method = RequestMethod.GET)
    public ModelAndView test_update(@PathVariable("id") long id, @PathVariable("nombre") String nombre) {
        ModelAndView view = new ModelAndView("autores");
        Author a = authorDao.findById(id);
        String mensaje = "Update: " + a.toString();
        if (a != null) {
            a.setName(nombre);
            authorDao.update(a);
            mensaje += "\n\t To: " + a.toString();
            view.addObject("mensaje", mensaje);
        } else {
            view.addObject("error", "No hay Autor con id: " + id);
        }
        view.addObject("autores", authorDao.list());
        return view;
    }

    /**
     * *****************************LIBROS************************************
     */
    @RequestMapping(value = "libros", method = RequestMethod.GET)
    public @ResponseBody List<Book> libros() {return  bookDao.list();}

    @RequestMapping(value = "libros/insert/{isbn}/{title}", method = RequestMethod.GET)
    public ModelAndView book_insert(@PathVariable("isbn") String isbn, @PathVariable("title") String title) {
        ModelAndView view = new ModelAndView("libros");
        try {
            Book b = new Book(isbn, title);
            bookDao.save(b);
            view.addObject("mensaje", "Insert: " + b.toString());
        } catch (ConstraintViolationException e) {
            view.addObject("error", e.getCause().getMessage());
        }
        view.addObject("libros", bookDao.list());
        return view;
    }

    @RequestMapping(value = "libros/delete/{id}", method = RequestMethod.GET)
    public ModelAndView libros_delete(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("libros");
        Book b = bookDao.findById(id);
        if (b != null) {
            try {
                bookDao.delete(b);
                view.addObject("mensaje", "Delete: " + b.toString());
            } catch (DataIntegrityViolationException e) {
                view.addObject("error", "Posee FK");
            }
        } else {
            view.addObject("error", "No hay Libro con id: " + id);
        }
        view.addObject("libros", bookDao.list());
        return view;
    }

    @RequestMapping(value = "libros/deleteByIsbn/{isbn}", method = RequestMethod.GET)
    public ModelAndView libros_delete(@PathVariable("isbn") String isbn) {
        ModelAndView view = new ModelAndView("libros");
        Book b = bookDao.findByIsbn(isbn);
        if (b != null) {
            try {
                bookDao.delete(b);
                view.addObject("mensaje", "Delete: " + b.toString());
            } catch (DataIntegrityViolationException e) {
                view.addObject("error", "Posee FK");
            }
        } else {
            view.addObject("error", "No hay Libro con isbn: " + isbn);
        }
        view.addObject("libros", bookDao.list());
        return view;
    }

    @RequestMapping(value = "libros/update/{id}/{isbn}/{title}", method = RequestMethod.GET)
    public ModelAndView libros_update(@PathVariable("id") long id, @PathVariable("isbn") String isbn, @PathVariable("title") String title) {
        ModelAndView view = new ModelAndView("libros");
        Book b = bookDao.findById(id);
        String mensaje = "Update: " + b.toString();
        if (b != null) {
            b.setIsbn(isbn);
            b.setTitle(title);
            bookDao.update(b);
            mensaje += "\n\t To: " + b.toString();
            view.addObject("mensaje", mensaje);
        } else {
            view.addObject("error", "No hay Libro con id: " + id);
        }
        view.addObject("libros", bookDao.list());
        return view;
    }

    @RequestMapping(value = "libros/addAuthor/{id_libro}/{id_author}", method = RequestMethod.GET)
    public ModelAndView addAuthor(@PathVariable("id_libro") long id_libro, @PathVariable("id_author") long id_author) {
        ModelAndView view = new ModelAndView("libros");
        Book b = bookDao.findById(id_libro);
        Author a = authorDao.findById(id_author);
        if (b != null && a != null) {
            b.getAutores().add(a);
            a.getLibros().add(b);
            bookDao.update(b);
            authorDao.update(a);
            view.addObject("mensaje", "Añadir: " + a.toString() + " a: " + b.toString());
        } else {
            view.addObject("error", "No hay Libro con id: " + id_libro);
        }
        view.addObject("libros", bookDao.list());
        return view;
    }

    @RequestMapping(value = "libros/deleteAuthor/{id_libro}/{id_author}", method = RequestMethod.GET)
    public ModelAndView delAuthor(@PathVariable("id_libro") long id_libro, @PathVariable("id_author") long id_author) {
        ModelAndView view = new ModelAndView("libros");
        Book b = bookDao.findById(id_libro);
        Author a = authorDao.findById(id_author);
        if (b != null && a != null) {
            b.quitarAuthor(a);
            a.quitarLibro(b);
            bookDao.update(b);
            authorDao.update(a);
            view.addObject("mensaje", "Eliminar: " + a.toString() + " a: " + b.toString());
        } else {
            view.addObject("error", "No hay Libro con id: " + id_libro);
        }
        view.addObject("libros", bookDao.list());
        return view;
    }
}
