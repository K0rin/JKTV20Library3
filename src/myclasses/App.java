/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import Entity.Reader;
import Entity.Book;
import Entity.Autor;
import Entity.History;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author pupil
 */
public class App {
    
    public App() {
        
    }
    public void run() {
        System.out.println("Hello");
        Book book1 = new Book();
        book1.setCaption("Voina i Mir");
        Autor autor1 = new Autor();
        autor1.setName("Lev");
        autor1.setLastname("Tolstoy");
        autor1.setYear(1828);
        autor1.setBirthday(9);
        autor1.setMonth(9);
        Autor[] autors = new Autor[1];
        autors[0]=autor1;
        book1.setAuthor(autors);
        book1.setPublication_year(2005);
        Book book2 = new Book();
        book2.setCaption("Otsi i deti");
        Autor autor2 = new Autor();
        autor2.setName("Ivan");
        autor2.setLastname("Turgenev");
        autor2.setYear(1818);
        autor2.setBirthday(9);
        autor2.setMonth(11);
        Autor[] autors2 = new Autor[1];
        autors2[0] = autor2;
        book2.setAuthor(autors2);
        book2.setPublication_year(1862);
        Reader reader1 = new Reader();
        reader1.setFirstname("Ivan");
        reader1.setLastname("Ivanov");
        reader1.setPhone("4564545345");
        
        History history1 = new History();
        history1.setBook(book1);
        history1.setReader(reader1);
        Calendar c = new GregorianCalendar();
        
        history1.setGivenDate(c.getTime());
        
        System.out.println("History = "+history1.toString());
        System.out.println(" ------------------------------ ");
        history1.setReturnDate(c.getTime());
        System.out.println("history 1 = "+history1.toString());
    }
    
}
