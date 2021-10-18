/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Entity.Autor;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author pupil
 */
public class Book implements Serializable {
    private String caption;
    private List<Autor> author;
    private int publication_year;
    
    public Book() {
        
    }

    public String getCaption() {
        return caption;
    }

    public List<Autor> getAuthor() {
        return author;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setAuthor(List<Autor> author) {
        this.author = author;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    @Override
    public String toString() {
        return "Book{" + "caption=" + caption
                + ",\n author=" + Arrays.toString(author.toArray())
                + ",\n publication_year=" + publication_year
                + "\n}";
    }
    
   
}
