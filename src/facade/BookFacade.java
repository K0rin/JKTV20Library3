/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class BookFacade extends AbstractFacade<Book>{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jktv20libraryv4PU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    public BookFacade(Class<Book> entiClass) {
        super(entiClass);
    }
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
}
