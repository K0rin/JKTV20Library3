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
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    Book[] books = new Book[10];
    Reader[] readers = new Reader[10];
    History[] histories = new History[10];
    
    
    
    public App() {
    }
    public void run() {
        String repeat = "y";
        do{
            System.out.println("Выберите задачу:");
            System.out.println("0: Закончить задачу");
            System.out.println("1: Продолжить задачу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = "q";
                    System.out.println("Программа закончена");
                    break;
                case 1:
                    System.out.println("Добавление книги");
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] == null){
                            books[i] = addBook();
                            break;
                            }
                    }
                    break; 
                case 2:
                    System.out.println("Список книг: ");
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] != null){
                            System.out.println(books[i].toString());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Добавление читателя");
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] == null){
                            readers[i] = addReader();
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Список читателей");
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] != null){
                            System.out.println(readers[i].toString());
                        }
                        
                    }
            }
        }while("y".equals(repeat));         
    }
    
    private Book addBook(){
            Book book = new Book();
            System.out.println("Введите название книги");
            book.setCaption(scanner.nextLine());
            System.out.println("Год издания");
            book.setPublication_year(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Сколько авторов у книги");
            int countAutor = scanner.nextInt();
            scanner.nextLine();  
            Autor[] autors = new Autor[countAutor];
            for (int i = 0; i < autors.length; i++) {
                System.out.println("Добавление автора"+(i+1));
                Autor autor = new Autor();
                System.out.println("Имя автора");
                autor.setName(scanner.nextLine());
                System.out.println("Фамилия автора");
                autor.setLastname(scanner.nextLine());
                System.out.println("год рождения автора");
                autor.setYear(scanner.nextInt());
                System.out.println("день рождения автора");
                autor.setBirthday(scanner.nextInt());
                System.out.println("Месяц рождения автора");
                autor.setMonth(scanner.nextInt());
                autors[i]=autor;
            }
            book.setAuthor(autors);
            return book; 
    }
    
    private Reader addReader(){
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        
        return reader;
    }
    
}
