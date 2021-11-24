/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Book;
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
public abstract class AbstractFacade<T> {
    
    
    
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entiClass){   
            this.entityClass = entiClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    public void create(T entity){
        getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }
    
    public void edit(T entity){
        tx.begin();
            .merge(entity);
        tx.commit();
    }
    
    public T find(Long id){
        return em.find(entityClass, id);
    }
    
    public List<T> findAll(){
        try{
            return em.createQuery("SELECT entity FROM "+entityClass.getName()+" entity").getResultList();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
