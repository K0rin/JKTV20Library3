/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import entity.History;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;
    
    public HistoryFacade() {
        super(History.class);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public History findHistorybyGivenBook(Book book){
        return (History) getEntityManager().createQuery(
                "SELECT history FROM History history WHERE history.book = :book AND history.returnDate = null")
                .setParameter("book", book).getSingleResult();
        
    }
    
    public List<History> findHistoryWithGivenBooks() {
        return getEntityManager().createQuery("SELECT h FROM History h WHERE h.returnDate = null").getResultList();
    }
    
    
    
}
