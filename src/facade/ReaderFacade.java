/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Reader;

/**
 *
 * @author pupil
 */
public class ReaderFacade extends AbstractFacade<Reader>{
    
    public ReaderFacade(Class<Reader> entiClass) {
        super(entiClass);
    }
    
}
