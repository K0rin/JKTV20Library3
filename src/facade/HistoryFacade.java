/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Book;
import Entity.History;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class HistoryFacade extends AbstractFacade<History>{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jktv20libraryv4PU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    public HistoryFacade(Class<History> entiClass) {
        super(entiClass);
    }
    
    public History findHistorybyGivenBook(Book book){
        return (History) getEntityManager().createQuery("SELECT history FROM History history WHERE history.book = :book AND history.returnDate = null")
                .setParameter("book", book).getSingleResult();
        
    }
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
}
