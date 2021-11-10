/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Autor;
import Entity.Book;
import Entity.History;
import Entity.Reader;
import java.util.List;

/**
 *
 * @author pupil
 */
public interface Keeping {
    
    public void saveAuthors(List<Autor> autors);
    public List<Autor> loadAuthors();
    public void saveBooks(List<Book> books);
    public List<Book> loadBooks();
    public void saveReaders(List<Reader> readers);
    public List<Reader> loadReaders();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
}
