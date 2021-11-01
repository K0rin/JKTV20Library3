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
                    addBook();
                    break; 
                case 2:
                    printListBooks();
                    break;
                case 3:
                    addReader();
                    break;
                case 4:
                    printListReaders();
                    break;
                case 5:
                    System.out.println("Добавление выданной книги");
                    addHistory();
                    break;
                case 6:
                    printGivenBook();
                    break;
                case 7:
                    returnBook();                  
                    break;
            }
        }while("y".equals(repeat));         
    }
    
    private void addBook(){
            System.out.println("Добавление книги");
            Book book = new Book();
            System.out.print("Введите название книги: ");
            book.setCaption(scanner.nextLine());
            System.out.print("Введите год издания: ");
            book.setPublication_year(scanner.nextInt()); scanner.nextLine();
            System.out.print("Введите количество экземпляров: ");
            book.setQuantity(scanner.nextInt()); scanner.nextLine();
            book.setCount(book.getQuantity());
            System.out.print("Сколько авторов у книги: ");
            int countAutor = scanner.nextInt(); scanner.nextLine();  
            List<Autor> autors = new ArrayList<>();
            for (int i = 0; i < countAutor; i++) {
                System.out.println("Добавление автора "+(i+1));
                Autor autor = new Autor();
                System.out.print("Имя автора: ");
                autor.setName(scanner.nextLine());
                System.out.print("Фамилия автора: ");
                autor.setLastname(scanner.nextLine());
                System.out.print("год рождения автора: ");
                autor.setYear(scanner.nextInt()); scanner.nextLine();
                System.out.print("день рождения автора: ");
                autor.setBirthday(scanner.nextInt()); scanner.nextLine();
                System.out.print("Месяц рождения автора: ");
                autor.setMonth(scanner.nextInt()); scanner.nextLine();
                autors.add(autor);
            }
            book.setAuthor(autors);
            books.add(book);
            file_keeper.saveBooks(books);
    }
    
    private void addReader(){
        System.out.println("Добавление читателя");
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        
        readers.add(reader);
        file_keeper.saveReaders(readers);
    }
    
    private void printGivenBook(){
        System.out.println("Список выданных книг: ");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null 
                    && histories.get(i).getReturnDate() == null
                    && histories.get(i).getBook().getCount() 
                        < histories.get(i).getBook().getQuantity()
                    ){
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
    
    private void addHistory(){
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
        printListBooks();
        System.out.println("Введите номер книги");
        int bookNumber = scanner.nextInt(); scanner.nextLine();
        history.setBook(books.get(bookNumber-1));
        System.out.println("Список читателей");
        printListReaders();
        System.out.println("Введите номер читателя");
        int readerNumber = scanner.nextInt(); scanner.nextLine();
        history.setReader(readers.get(readerNumber-1));
        Calendar c = new GregorianCalendar(); 
        history.setGivenDate(c.getTime());
        books.get(bookNumber-1).setCount(history.getBook().getCount() -1);
        file_keeper.saveBooks(books);
        histories.add(history);
        file_keeper.saveHistories(histories);
    }

    private void printListBooks() {
        System.out.println("Список книг: ");
        books = file_keeper.loadBooks();
        for(int i = 0; i < books.size(); i++){
            StringBuilder cbAuthors = new StringBuilder();
            for (int j = 0; j < books.get(i).getAuthor().size(); j++) {
               cbAuthors.append(books.get(i).getAuthor().get(j).getName())
                        .append(" ")
                        .append(books.get(i).getAuthor().get(j).getLastname())
                        .append(". ");
            }
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.printf("%d. %s. %s В наличии экземпляров: %d%n"
                        ,i+1
                        ,books.get(i).getCaption()
                        ,cbAuthors.toString()
                        ,books.get(i).getCount());
            }
        }
    }

    private void printListReaders() {
        System.out.println("Список читателей");
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i) != null){
                    System.out.printf("%d. %s%n",i+1, readers.get(i));
                }
            }
            
    }

    private void returnBook() {
        System.out.println("Вернуть книгу: ");
        printGivenBook();
        System.out.print("Выберите возвращаемую книгу: ");
        int historyNumber = scanner.nextInt(); scanner.nextLine();
        Calendar c = new GregorianCalendar();
        histories.get(historyNumber-1).setReturnDate(c.getTime());
        
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getCaption().equals(histories.get(historyNumber-1).getBook().getCaption())){
                books.get(i).setCount(books.get(i).getCount()+1);
            }
        }
                
        file_keeper.saveBooks(books);
        file_keeper.saveHistories(histories); 
    }
    
}
