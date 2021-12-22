/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Reader;
import entity.User;
import entity.UserRole;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class UserRolesFacade extends AbstractFacade<UserRole>{
    private EntityManager em;
    
    public UserRolesFacade() {
        super(UserRole.class);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em; //To change body of generated methods, choose Tools | Templates.
    }
    
}
