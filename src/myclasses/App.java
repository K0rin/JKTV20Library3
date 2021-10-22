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
import File_Keeper.File_Keeper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = new ArrayList<>();
    List<Reader> readers = new ArrayList<>();
    List<History> histories = new ArrayList<>();
    File_Keeper file_keeper = new File_Keeper();
    
    public App() {
        File_Keeper file_keeper = new File_Keeper();
        books = file_keeper.loadBooks();
        readers = file_keeper.loadReaders();
        histories = file_keeper.loadHistories();
    }
    
    public void run() {
        String repeat = "y";
        do{
            System.out.println("Выберите задачу:");
            System.out.println("0: Закончить задачу");
            System.out.println("1: Добавление книги");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            System.out.println("5: Добавление выдачи книги");
            System.out.println("6: Список выданных книг");
            System.out.println("7: Возврат книги");
            int task = scanner.nextInt();
            scanner.nextLine();
            switch (task){
                case 0:
                    repeat = "q";
                    System.out.println("Программа закончена");
                    break;
                case 1:
                    System.out.println("Добавление книги");
                            books.add(addBook());
                            file_keeper.saveBooks(books);
                            break; 
                case 2:
                    System.out.println("Список книг: ");
                    for (int i = 0; i < books.size(); i++) {
                        if(books.get(i) != null){
                            System.out.println(books.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Добавление читателя");
                            readers.add(addReader());
                            file_keeper.saveReaders(readers);
                            break;
                case 4:
                    System.out.println("Список читателей");
                    for (int i = 0; i < readers.size(); i++) {
                        if(readers.get(i) != null){
                            System.out.println(readers.get(i));
                        }
                        
                    }
                    break;
                case 5:
                    System.out.println("Добавление выданной книги");
                            histories.add(addHistory());
                            file_keeper.saveHistories(histories);
                            break;
                case 6:
                    printGivenBook();
                    break;
                case 7:
                    printGivenBook();
                    System.out.print("Выберите возвращаемую книгу: ");
                    int historyNumber = scanner.nextInt(); scanner.nextLine();
                    Calendar c = new GregorianCalendar();
                    histories.get(historyNumber-1).setReturnDate(c.getTime());
                    file_keeper.saveHistories(histories);                   
                    break;
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
            List<Autor> autors = new ArrayList<>();
            for (int i = 0; i < countAutor; i++) {
                System.out.println("Добавление автора "+(i+1));
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
                autors.add(autor);
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
    
    private void printGivenBook(){
        System.out.println("Список выданных книг: ");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null && histories.get(i).getReturnDate() == null){
                System.out.printf("%d. Книгу: %s читает %s %s%n",
                        i+1,
                        histories.get(i).getBook().getCaption(),
                        histories.get(i).getReader().getFirstname(),
                        histories.get(i).getReader().getLastname()
                );
                n++;
                
            }
        }
        if (n<1){
            System.out.println("Выданных книг нет");
        }
    }
    
    private History addHistory(){
        History history = new History();
        /*
            Вывести список книг
            Получить от пользователя номер выбранной книги
            Вывести список читателей
            Получить от пользователя номер читателя
            создать объект history
             b history инициировать поле boook объектом который лежит в массиве books[booknumber-1].
             v history инициировать поле reader объектом который лежит в массиве reader[readerNumber-1]
            Получить текущую дату и положить ее в поле history givenDate
        */
        
        System.out.println("Список книг в библиотеке");
        for (int i = 0; i < books.size(); i++) {
                        if(books.get(i) != null){
                            System.out.printf("%d. %s%n",i+1, books.get(i));
                        }
                    }
        System.out.println("Введите номер книги");
        int bookNumber = scanner.nextInt(); scanner.nextLine();
        history.setBook(books.get(bookNumber-1));
        
        for (int i = 0; i < readers.size(); i++) {
                        if(readers.get(i) != null){
                            System.out.printf("%d. %s%n",i+1, readers.get(i));
                        }
                    }
        System.out.println("Введите номер читателя");
        int readerNumber = scanner.nextInt(); scanner.nextLine();
        history.setReader(readers.get(readerNumber-1));
        Calendar c = new GregorianCalendar(); 
        history.setGivenDate(c.getTime());
        return history;
    }
    
}
