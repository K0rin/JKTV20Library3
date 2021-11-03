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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
    private Date LocalDate;
    
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
            int year;
            do{
                System.out.print("Введите год издания: ");
                String strYear = scanner.nextLine();
                try{                   
                    year = Integer.parseInt(strYear);
                    book.setPublication_year(year);
                }catch (Exception e) {
                    year = 0;
                }
            }while(year == 0);
            int quantity;
            do{
                System.out.print("Введите количество экземпляров: ");
                String strQuantity = scanner.nextLine();
                try{
                    quantity = Integer.parseInt(strQuantity);
                    book.setQuantity(quantity);
                }catch (Exception e){
                    quantity = 0;
                }
            }while(quantity == 0);
            book.setCount(book.getQuantity());
            int countAutor;
            do{
                System.out.print("Сколько авторов у книги: ");
                String strCountAuthors = scanner.nextLine();
                try{
                    countAutor = Integer.parseInt(strCountAuthors); 
                }catch (Exception e){
                    countAutor = 0;
                }
            }while(countAutor == 0);  
            List<Autor> autors = new ArrayList<>();
            for (int i = 0; i < countAutor; i++) {
                System.out.println("Добавление автора "+(i+1));
                Autor autor = new Autor();
                System.out.print("Имя автора: ");
                autor.setName(scanner.nextLine());
                System.out.print("Фамилия автора: ");
                autor.setLastname(scanner.nextLine());
                int yearAuthor;
                do{
                    System.out.print("год рождения автора: ");
                    String strAuthorYear = scanner.nextLine();
                try{
                    yearAuthor = Integer.parseInt(strAuthorYear);
                    autor.setYear(yearAuthor);
                }catch (Exception e){
                    yearAuthor = 0;
                }
                }while(yearAuthor == 0);
                int dayAuthor;
                do{
                System.out.print("день рождения автора: ");
                    String strAuthorDay = scanner.nextLine();
                    try{
                        dayAuthor = Integer.parseInt(strAuthorDay);
                        autor.setBirthday(dayAuthor);
                    }catch (Exception e){
                        dayAuthor = 0;
                    }
                }while(dayAuthor == 0);
                int monthAuthor;
                do{
                    System.out.print("Месяц рождения автора: ");
                    String strAuthorMonth = scanner.nextLine();
                try{
                    monthAuthor = Integer.parseInt(strAuthorMonth);
                    autor.setMonth(monthAuthor);
                }catch (Exception e){
                    monthAuthor = 0;
                }
                }while(monthAuthor == 0);
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
    
    private Set<Integer> printGivenBook(){
        System.out.println("Список выданных книг: ");
        Set<Integer> setNumberGivenBooks = new HashSet<>();
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
                setNumberGivenBooks.add(i+1);
                
            }
        }
        if (setNumberGivenBooks.isEmpty()){
            System.out.println("Выданных книг нет");
        }
        return setNumberGivenBooks;
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
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        int bookNumber;
        do{
            System.out.print("Введите номер книги");
            String strBookNumber = scanner.nextLine();
            try{
                bookNumber = Integer.parseInt(strBookNumber);
            }catch (Exception e){
                bookNumber = 0;
            }
        }while(!setNumbersBooks.contains(bookNumber));
        history.setBook(books.get(bookNumber-1));
        System.out.println();
        System.out.println("Список читателей");
        Set<Integer> setNumbersReaders = printListReaders();
        int readerNumber;
        do{
            System.out.println("Введите номер читателя");
            String strreaderNumber = scanner.nextLine();
            try {
                readerNumber = Integer.parseInt(strreaderNumber);
            }catch (Exception e) {
                readerNumber = 0;
            }
        }while(!setNumbersReaders.contains(readerNumber));
        history.setReader(readers.get(readerNumber-1));
        Calendar c = new GregorianCalendar(); 
        history.setGivenDate(c.getTime());
        books.get(bookNumber-1).setCount(history.getBook().getCount() -1);
        file_keeper.saveBooks(books);
        histories.add(history);
        file_keeper.saveHistories(histories);
    }

    private Set<Integer> printListBooks() {
        System.out.println("Список книг: ");
        books = file_keeper.loadBooks();
        Set<Integer> setNumbersBooks = new HashSet<>();
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
                setNumbersBooks.add(i+1);
            }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s Нет в наличии. Вернется в: %s%n"
                        ,i+1
                        ,books.get(i).getCaption()
                        ,cbAuthors.toString()
                        ,getReturnDate(books.get(i)));
            }
        }
        return setNumbersBooks;
    }
    
    private String getReturnDate(Book book){
        for (int i = 0; i < histories.size(); i++) {
            if(book.getCaption().equals(histories.get(i).getBook().getCaption())){
                LocalDate localGivenDate = histories.get(i).getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localGivenDate = localGivenDate.plusDays(14);
                return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        }
        
        return "";
    }

    private Set<Integer> printListReaders() {
        Set<Integer> setNumbersReaders = new HashSet<>();
        System.out.println("Список читателей");
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i) != null){
                    System.out.printf("%d. %s%n",i+1, readers.get(i));
                }
            }
            return setNumbersReaders;
    }

    private void returnBook() {
        System.out.println("Вернуть книгу: ");
        Set<Integer> numbersGivenBooks = printGivenBook();
        if(numbersGivenBooks.isEmpty()){
            return;
        }
        
        int historyNumber;
        do{
            System.out.print("Выберите возвращаемую книгу: ");
            String strSistoryNumber = scanner.nextLine();
            try {
                 historyNumber = Integer.parseInt(strSistoryNumber);
            }catch (Exception e){
                historyNumber = 0;
            }
        }while(!numbersGivenBooks.contains(historyNumber));
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
