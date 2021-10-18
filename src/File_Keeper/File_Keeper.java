/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File_Keeper;

import Entity.Book;
import Entity.History;
import Entity.Reader;
import Interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class File_Keeper implements Keeping{

    @Override
    public void saveBooks(List<Book> books) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("books");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода данных", ex);
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> loadBooks = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("books");
            ois = new ObjectInputStream(fis);
            loadBooks = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Нет файла books", ex);
        } catch (IOException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(File_Keeper.class.getName()).log(Level.SEVERE, "класс не найден", ex);
        }
        
        return loadBooks;
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        
    }

    @Override
    public List<Reader> loadReaders() {
        List<Reader> loadReaders = new ArrayList<>();
        return loadReaders;
    }

    @Override
    public void saveHistories(List<History> histories) {
        
    }

    @Override
    public List<History> loadHistories() {
        List<History> loadHistories = new ArrayList<>();
        return loadHistories;
    }
    
    
}
