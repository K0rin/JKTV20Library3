/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pupil
 */
public class Autor implements Serializable {
    private String name;
    private String lastname;
    private int year;
    private int birthday;
    private int month;
    public Autor() {
    
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getYear() {
        return year;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getMonth() {
        return month;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Autor{" + "name=" + name
                + ", lastname=" + lastname
                + ", year=" + year
                + ", birthday=" + birthday
                + ", month=" + month
                + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.lastname);
        hash = 43 * hash + this.year;
        hash = 43 * hash + this.birthday;
        hash = 43 * hash + this.month;
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
        final Autor other = (Autor) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.birthday != other.birthday) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        return true;
    }
    
}
