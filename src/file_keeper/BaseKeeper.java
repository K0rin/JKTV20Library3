/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_keeper;

import entity.Autor;
import entity.Book;
import entity.History;
import entity.Reader;
import Interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class BaseKeeper implements Keeping{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jktv20libraryv4PU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    @Override
    public void saveAuthors(List<Autor> autors) {
        
        tx.begin();
            for (int i = 0; i < autors.size(); i++) {
                if(autors.get(i).getId() == null){
                    
                    em.persist(autors.get(i));
                }
            }
        tx.commit();
        
    }

    @Override
    public List<Autor> loadAuthors() {
        List<Autor> autors = null;
        try {
            autors = em.createQuery("SELECT autor FROM Autor autor")
                .getResultList();
        }catch (Exception e){
            autors = new ArrayList<>();
        }        
        return autors;
    }
       
    @Override
    public void saveBooks(List<Book> books) {
        
        tx.begin();
            for (int i = 0; i < books.size(); i++) {
                if(books.get(i).getId() == null){
                    for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
                        Autor author = books.get(i).getAuthor().get(j);
                        em.persist(author);
                    }
                    em.persist(books.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = null;
        try {
            books = em.createQuery("SELECT book FROM Book book")
                .getResultList();
        }catch (Exception e){
            books = new ArrayList<>();
        }        
        return books;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        
        tx.begin();
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i).getId() == null){
                    
                    em.persist(readers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Reader> loadReaders() {
        
        List<Reader> readers = null;
        try {
            readers = em.createQuery("SELECT reader FROM Reader reader")
                .getResultList();
        }catch (Exception e){
            readers = new ArrayList<>();
        }        
        return readers;
    }

    @Override
    public void saveHistories(List<History> histories) {
        
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    
                    em.persist(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        
        List<History> histories = null;
        try {
            histories = em.createQuery("SELECT history FROM History history")
                .getResultList();
        }catch (Exception e){
            histories = new ArrayList<>();
        }        
        return histories;
    }

    
    
}
