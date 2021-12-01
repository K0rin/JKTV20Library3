/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Autor;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author pupil
 */
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String caption;
    @OneToOne(cascade = CascadeType.ALL)
    private List<Autor> author;
    private int publication_year;
    private int quantity;
    private int count;
    
    public Book() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        return "Book{" 
                + "caption=" + caption 
                + ", author=" + Arrays.toString(author.toArray()) 
                + ", publication_year=" + publication_year 
                + ", quantity=" + quantity + 
                ", count=" + count + 
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.caption);
        hash = 67 * hash + Objects.hashCode(this.author);
        hash = 67 * hash + this.publication_year;
        hash = 67 * hash + this.quantity;
        hash = 67 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.publication_year != other.publication_year) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.caption, other.caption)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }


       
}
