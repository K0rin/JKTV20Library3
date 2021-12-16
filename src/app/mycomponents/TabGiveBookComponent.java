/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Autor;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.BookFacade;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;

/**
 *
 * @author pupil
 */
public class TabGiveBookComponent extends JPanel{   

    private InfoComponent infoComponent;
    private CaptionComponent captionComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastNameComponent;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private ListBooksComponent listBooksComponent;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    private ComboBoxModel comboBoxModel;
    private Reader reader;
    private History history;
    
//    public TabGiveBookComponent(int widthPanel, ComboBoxModel comboBoxModel) {
//        this.comboBoxModel = comboBoxModel;
//        initComponent(widthPanel);
//    }

    public TabGiveBookComponent(int widthPanel, ComboBoxModel comboBoxModel, ListBooksComponent listBooksComponent) {
        this.comboBoxModel = comboBoxModel;
        this.listBooksComponent = listBooksComponent;
        initComponents(widthPanel);
    }

    private void initComponents(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabAddReader = new JTabbedPane();       
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Выбор книги", widthPanel, 31);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);        
        listBooksComponent = new ListBooksComponent("Книги", widthPanel, 120, 300);
        this.add(listBooksComponent);
        listBooksComponent.getJList().setModel(getListBookModel());
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        comboBoxReadersComponent = new ComboBoxReadersComponent("Читателю", widthPanel, 31, 300);
        comboBoxReadersComponent.getComboBox().setModel(addComboBoxModelReader());
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
        comboBoxReadersComponent.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                reader=(Reader) ie.getItem();
            }
        });
        
        
        this.add(comboBoxReadersComponent);       
        this.add(Box.createRigidArea(new Dimension(0,10)));        

        this.add(Box.createRigidArea(new Dimension(0,10)));
        
        buttonComponent = new ButtonComponent("выдать книги читателя", widthPanel, 35, widthPanel/3+5, 200);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButton());
    }
    
    private ActionListener clickToButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                BookFacade bookFacade = new BookFacade(Book.class);
                List<Book> books = listBooksComponent.getJList().getSelectedValuesList();
                if(books.isEmpty()){
                    infoComponent.getInfo().setText("вы не выбрали автора");
                    return;
                }
                for (int i = 0; i < books.size(); i++) {
                    history.setBook(bookFacade.find((long)book));
                }
                
                
                
                try {
                   readerFacade.edit(reader);
                   infoComponent.getInfo().setText("Читатель изменен");
                   comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
                   nameComponent.getEditor().setText("");
                   lastNameComponent.getEditor().setText("");
                   phoneComponent.getEditor().setText("");
                } catch (Exception e) {
                   infoComponent.getInfo().setText("Error");
                }
            }
        };
        
    }
    
    public ComboBoxModel<Reader> addComboBoxModelReader(){
        infoComponent.getInfo().setText("");
        ReaderFacade readerFacade = new ReaderFacade(Reader.class);
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxModel = defaultComboBoxModel;
        comboBoxReadersComponent.getComboBox().setModel(defaultComboBoxModel);
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
        return comboBoxModel;
    }
    
    public ListModel<Book> getListBookModel() {
        BookFacade bookFacade = new BookFacade(Book.class);
        List<Book> books = bookFacade.findAll();
        DefaultListModel<Book> defaultListModel = new DefaultListModel<>();
        for (Book book : books) {
            defaultListModel.addElement(book);
        }
        return defaultListModel;
    }
}


