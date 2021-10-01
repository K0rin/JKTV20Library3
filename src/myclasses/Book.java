/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

/**
 *
 * @author pupil
 */
public class Book {
    private String caption;
    private Autor[] author;
    private int publication_year;
    
    public Book() {
        
    }

    public String getCaption() {
        return caption;
    }

    public Autor[] getAuthor() {
        return author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setAuthor(Autor[] author) {
        this.author = author;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }
    
    
}
