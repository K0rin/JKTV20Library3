/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Autor;

/**
 *
 * @author pupil
 */
public class AutorFacade extends AbstractFacade<Autor>{
    
    public AutorFacade(Class<Autor> entiClass) {
        super(entiClass);
    }
    
}
