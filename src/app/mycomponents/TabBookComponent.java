/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Autor;
import entity.Book;
import facade.BookFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author pupil
 */
public class TabBookComponent extends JPanel{
    
    private InfoComponent infoComponent1;
    private CaptionComponent captionComponent;
    private EditorComponent bookNameComponent;
    private EditorComponent publicationYearComponent;
    private EditorComponent quantityComponent;
    private ButtonComponent buttonComponent;
    private ListAuthorsComponent listAuthors;
    
    public TabBookComponent(int widthWindow) {
        initComponent(widthWindow);
    }

    private void initComponent(int widthWindow) {
        this.setPreferredSize(new Dimension(widthWindow, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Добавление новой книги", widthWindow, 31);
        this.add(captionComponent);
        infoComponent1 = new InfoComponent("", widthWindow, 31);
        this.add(infoComponent1);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponent = new EditorComponent("Название книги", widthWindow, 31, 300);
        this.add(bookNameComponent);
        listAuthors = new ListAuthorsComponent("Авторы", widthWindow, 120, 300);
        this.add(listAuthors);
        publicationYearComponent = new EditorComponent("Год издания", widthWindow, 31, 100);
        this.add(publicationYearComponent);
        quantityComponent = new EditorComponent("Количество экземпляров", widthWindow, 31, 50);
        this.add(quantityComponent);
        buttonComponent = new ButtonComponent("Добавить книгу", widthWindow, 31, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButton());
    }
    
    private ActionListener clickToButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                /**
                 * Создать объект book
                 * Инициировать поля книги используя элементы компонентов
                 * Добавить книгу в базу данных
                 * Сообщить пользователю о результате
                 * если true очистить редакторы компонентов
                */
                Book book = new Book();
                if(bookNameComponent.getEditor().getText().isEmpty()){
                    infoComponent1.getInfo().setText("Введите название книги");
                    return;
                }
                book.setCaption(bookNameComponent.getEditor().getText());
                try {
                   book.setPublication_year(Integer.parseInt(publicationYearComponent.getEditor().getText())); 
                } catch (Exception e) {
                    infoComponent1.getInfo().setText("введите цифровое значение в год публикации");
                    return;
                }
                try {
                   book.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                   book.setCount(book.getQuantity());
                } catch (Exception e) {
                    infoComponent1.getInfo().setText("введите цифровое значение в количество экземпляров");
                    return;
                }
                List<Autor> bookAuthors = listAuthors.getJList().getSelectedValuesList();
                if(bookAuthors.isEmpty()){
                    infoComponent1.getInfo().setText("вы не выбрали автора");
                    return;
                }
                book.setAuthor(bookAuthors);
                BookFacade bookFacade = new BookFacade();
                try {
                   bookFacade.create(book);
                   infoComponent1.getInfo().setText("ОК");
                   listAuthors.getJList().clearSelection();
                   quantityComponent.getEditor().setText("");
                   publicationYearComponent.getEditor().setText("");
                   bookNameComponent.getEditor().setText("");
                } catch (Exception e) {
                   infoComponent1.getInfo().setText("Error");
                }
            }
        };
        
    }
        
}
