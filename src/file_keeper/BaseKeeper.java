/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_keeper;

import Entity.Book;
import Entity.History;
import Entity.Reader;
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
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    @Override
    public void saveBooks(List<Book> books) {
        
        tx.begin();
            for (int i = 0; i < books.size(); i++) {
                if(books.get(i).getId() == null){
                    em.persist(books.get(i));
                }         
            }
        tx.commit();
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try {
            books = em.createQuery("SELECT book FROM Book WHERE 1")
                .getResultList();
        }catch (Exception e){
            books = new ArrayList<>();
        }
        
        return books;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reader> loadReaders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveHistories(List<History> histories) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<History> loadHistories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
