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
import Interfaces.Keeping;
import facade.AutorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import file_keeper.BaseKeeper;
import file_keeper.File_Keeper;
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
    //List<Book> books = new ArrayList<>();
    //List<Reader> readers = new ArrayList<>();
    //List<History> histories = new ArrayList<>();
    //List<Autor> authors = new ArrayList<>();
    private BookFacade bookFacade = new BookFacade(Book.class);
    private ReaderFacade readerFacade = new ReaderFacade(Reader.class);
    private AutorFacade authorFacade = new AutorFacade(Autor.class);
    private HistoryFacade historyFacade = new HistoryFacade(History.class);
    Keeping keeper;
    //Keeping keeper = new File_Keeper();
    private Date LocalDate;
    
    public App() {
//        File_Keeper file_keeper = new File_Keeper();
          //books = keeper.loadBooks();
          //authors = keeper.loadAuthors();
          //readers = keeper.loadReaders();
          //histories = keeper.loadHistories();  
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
            System.out.println("5: Выдать книгу");
            System.out.println("6: Список выданных книг");
            System.out.println("7: Возврат книги");
            System.out.println("8: Добавить автора");
            System.out.println("9: Список авторов");
            System.out.println("10: Добавить количество экземпляров книг");
            System.out.println("11: Изменить азвание книги");
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
                case 8:
                    addAuthor();                  
                    break;
                case 9:
                    printListAuthors();                  
                    break;
                case 10:
                    changeBookCountity();  
                    keeper.saveBooks(books);
                    break;
                case 11:
                    changeBookName();  
                    keeper.saveBooks(books);
                    break;
            }
        }while("y".equals(repeat));         
    }
    
    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\","
                + "продолжения введите люьой другой символ ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
        return false;
    }
    
    private int getNumber(){
        do{
            try{
                String strNumber = scanner.nextLine();
                return Integer.parseInt(strNumber);
            }catch (Exception e){
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }
    
    private int insertNumber(Set<Integer> setNumbers){
        do{
            int historyNumber = getNumber();
            if(setNumbers.contains(historyNumber)){
                return historyNumber;
            }
            System.out.println("Попробуй еще раз: ");
        }while(true);
    }
    
    private void addBook(){
            System.out.println("Добавление книги");
            if(quit()) return; 
            Set<Integer> setNumbersAuthors = printListAuthors();
            if (setNumbersAuthors.isEmpty()) {
                System.out.println("Добавьте автора");
                return;
            }
            System.out.println("Если есть автор в списке нажмите 1.");           
            if(getNumber() != 1){
                System.out.println("Добавьте автора");
                return;
            }
            System.out.print("Сколько авторов у книги: ");
            Book book = new Book();
            List<Autor> authorsBook = new ArrayList<>();
            int countAutor = getNumber();
            for (int i = 0; i < countAutor; i++) {
                System.out.println("Введите номер автора "+(i+1)+": ");
                int numberAuthor = insertNumber(setNumbersAuthors);
                authorsBook.add(authorFacade.find((long)numberAuthor));
            }
            book.setAuthor(authorsBook);                       
            System.out.print("Введите название книги: ");
            book.setCaption(scanner.nextLine());
            System.out.print("Введите год издания: ");
            book.setPublication_year(getNumber());
            System.out.print("Введите количество экземпляров книги: ");
            book.setQuantity(getNumber());
            book.setCount(book.getQuantity());            
            bookFacade.create(book);
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
        readerFacade.create(reader);
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
        if(quit()) return;
        History history = new History();
        
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);
        history.setBook(bookFacade.find((long)bookNumber));
        System.out.println();
        Set<Integer> setNumbersReaders = printListReaders();
        if(setNumbersReaders.isEmpty()){
            return;
        }
        int readerNumber = insertNumber(setNumbersReaders);        
        history.setReader(readerFacade.find((long)readerNumber));
        Calendar c = new GregorianCalendar(); 
        history.setGivenDate(c.getTime());
        Book book = bookFacade.find((long)bookNumber);
        book.setCount(history.getBook().getCount() -1);
        bookFacade.edit(book);
        historyFacade.create(history);
    }

    private Set<Integer> printListBooks() {
        System.out.println("Список книг: ");
        List<Book> books = bookFacade.findAll();
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
                System.out.printf("%d. %s. %s %d В наличии экземпляров: %d%n"
                        ,i+1
                        ,books.get(i).getCaption()
                        ,cbAuthors.toString()
                        ,books.get(i).getPublication_year()
                        ,books.get(i).getCount());
                setNumbersBooks.add(i+1);
            }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s %d Нет в наличии. Вернется в: %s%n"
                        ,i+1
                        ,books.get(i).getCaption()
                        ,cbAuthors.toString()
                        ,books.get(i).getPublication_year()
                        ,getReturnDate(books.get(i)));
            }
        }
        return setNumbersBooks;
    }
    
    private String getReturnDate(Book book){
        History history = historyFacade.findHistorybyGivenBook(book);
        LocalDate localGivenDate = history.getGivenDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localGivenDate = localGivenDate.plusDays(14);
  
        return "";
    }

    private Set<Integer> printListReaders() {
        Set<Integer> setNumbersReaders = new HashSet<>();
        System.out.println("Список читателей");
        List<Reader> readers = readerFacade.findAll();
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i) != null){
                    System.out.printf("%d. %s %s Phone %s%n",readers.get(i).getId(), readers.get(i).getFirstname(), readers.get(i).getLastname(), readers.get(i).getPhone());
                    setNumbersReaders.add(i+1);
                }
            }
            if(setNumbersReaders.isEmpty()){
                System.out.println("Читателей нет");
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
        History history = historyFacade.find((long)historyNumber);
        history.setReturnDate(c.getTime());
        Book book = history.getBook();
        book.setCount(book.getCount()+1);
        bookFacade.edit(book);
        historyFacade.edit(history);
    }

    private void addAuthor() {
        System.out.println("Добавление автора ");
                Autor autor = new Autor();
                System.out.print("Имя автора: ");
                autor.setName(scanner.nextLine());
                System.out.print("Фамилия автора: ");
                autor.setLastname(scanner.nextLine());
                System.out.println("Год рождения автора: ");
                autor.setYear(getNumber());
                System.out.println("День рождения автора: ");
                autor.setBirthday(getNumber());
                System.out.println("Месяц рождения автора: ");
                autor.setMonth(getNumber());
                authorFacade.create(autor);
    }

    private Set<Integer> printListAuthors() {
        Set<Integer> setNumbersAuthors = new HashSet<>();
        System.out.println("Список читателей");
        List<Autor> authors = authorFacade.findAll();
            for (int i = 0; i < authors.size(); i++) {
                if(authors.get(i) != null){
                    System.out.printf("%d. %s %s %n",authors.get(i).getId(), authors.get(i).getName(), authors.get(i).getLastname());
                    setNumbersAuthors.add(authors.get(i).getId().intValue());
                }
            }
            return setNumbersAuthors;
    }

    private void changeBookCountity() {
        if(quit()) return;
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long)bookNumber);
        System.out.print("Сколько экземпляров добавить: ");
        int count = getNumber();
        book.setQuantity(book.getQuantity()+count);
        book.setCount(book.getCount()+count);
        System.out.println("ok");
    }

    private void changeBookName() {
        if(quit()) return;
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.print("Введите номер книги из списка: ");
        int bookNumber = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long)bookNumber);
        System.out.print("Введите новое название книги: ");
        book.setCaption(scanner.nextLine());
        System.out.println("ok"); 
    }
    
    
    
}
