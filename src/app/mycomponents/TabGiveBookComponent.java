/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.ComboBoxReadersComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListBooksComponent;
import entity.Autor;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
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
    private ButtonComponent buttonComponent;
    private ListBooksComponent listBooksComponent;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    private ComboBoxModel comboBoxModel;
    private Reader reader;
    
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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("?????????? ??????????", widthPanel, 31);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);        
        listBooksComponent = new ListBooksComponent("??????????", GuiApp.WIDTH_WINDOWS-100, 120, 300);
        this.add(listBooksComponent);
        JCheckBox checkBoxAllBooks = new JCheckBox("???????????????? ?????? ??????????");
        this.add(checkBoxAllBooks);
        checkBoxAllBooks.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
               if(ie.getStateChange() == ItemEvent.SELECTED){
                   listBooksComponent.getJList().setModel(listBooksComponent.getListModel(true));
                   buttonComponent.getButton().setEnabled(false);
                   listBooksComponent.getJList().setEnabled(false);
               }else{
                   listBooksComponent.getJList().setModel(listBooksComponent.getListModel(false));
                   buttonComponent.getButton().setEnabled(true);
                   listBooksComponent.getJList().setEnabled(true);
               }
            }
        });
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        comboBoxReadersComponent = new ComboBoxReadersComponent("????????????????", widthPanel, 31, 300);
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
        
        buttonComponent = new ButtonComponent("???????????? ?????????? ????????????????", widthPanel, 35, widthPanel/3+5, 200);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButton());
    }
    
    private ActionListener clickToButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                HistoryFacade historyFacade = new HistoryFacade();
                BookFacade bookFacade = new BookFacade();
                List<Book> books = listBooksComponent.getJList().getSelectedValuesList();
                if(books.isEmpty()){
                    infoComponent.getInfo().setText("???? ???? ?????????????? ??????????");
                    return;
                }
                if(comboBoxReadersComponent.getComboBox().getSelectedIndex() == -1){
                    infoComponent.getInfo().setText("???? ???? ?????????????? ??????????????????");
                    return;
                }                
           
                try {
                    for (int i = 0; i < books.size(); i++) {
                        History history = new History();
                        history.setBook(books.get(i));
                        history.setReader(reader);
                        Calendar c = new GregorianCalendar();
                        history.setGivenDate(c.getTime());
                        history.getBook().setCount(history.getBook().getCount()-1);
                        bookFacade.edit(history.getBook());
                        historyFacade.create(history);
                        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
                        listBooksComponent.getJList().setModel(listBooksComponent.getListModel(false));
                        listBooksComponent.getJList().clearSelection();
                    }
                   infoComponent.getInfo().setText("?????????? ????????????");
                   comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
                } catch (Exception e) {
                   infoComponent.getInfo().setText("Error");
                }
            }
        };
        
    }
    
    public ComboBoxModel<Reader> addComboBoxModelReader(){
        infoComponent.getInfo().setText("");
        ReaderFacade readerFacade = new ReaderFacade();
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
        BookFacade bookFacade = new BookFacade();
        List<Book> books = bookFacade.findEnabledBook();
        DefaultListModel<Book> defaultListModel = new DefaultListModel<>();
        for (Book book : books) {
            defaultListModel.addElement(book);
        }
        return defaultListModel;
    }
}


