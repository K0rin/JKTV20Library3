/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author pupil
 */
public class Autor {
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
    
    
}
