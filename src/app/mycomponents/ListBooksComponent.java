/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import entity.Autor;
import entity.Book;
import facade.AutorFacade;
import facade.BookFacade;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author pupil
 */
public class ListBooksComponent extends JPanel{
    
    private JLabel caption;
    private JList<Book> list;

    public ListBooksComponent(String text, int widthWindow, int heightPanel, int listWidth) {
        initComponents(false, text, widthWindow, heightPanel, listWidth);
    }

    public ListBooksComponent(boolean guest, String text, int widthWindow, int heightPanel, int listWidth) {
        this.initComponents(guest, text, widthWindow, heightPanel, listWidth);
    } 
    
    private void initComponents(boolean guest, String text, int widthWindow, int heightPanel, int listWidth) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));
            this.setMinimumSize(this.getPreferredSize());
            this.setMaximumSize(this.getPreferredSize());
        if(guest){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            caption = new JLabel(text);
            caption.setPreferredSize(new Dimension(widthWindow, 27));
            caption.setMinimumSize(caption.getPreferredSize());
            caption.setMaximumSize(caption.getPreferredSize());
    //        caption.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            caption.setHorizontalAlignment(JLabel.CENTER);       
            caption.setAlignmentY(TOP_ALIGNMENT);
            caption.setFont(new Font("Tahoma",0,12));
            this.add(caption);
            this.add(Box.createRigidArea(new Dimension(25, 0)));
            list = new JList<>();
            list.setModel(getListModel());
            list.setCellRenderer(createListBooksRenderer());
            list.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HEIGHT);
//            this.add(list);
            
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOWS-20, heightPanel));
            scrollPane.setMaximumSize(scrollPane.getPreferredSize());
            scrollPane.setMinimumSize(scrollPane.getPreferredSize());
            scrollPane.setAlignmentX(LEFT_ALIGNMENT);
            scrollPane.setAlignmentY(TOP_ALIGNMENT);
            this.add(scrollPane);
        }else{
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            caption = new JLabel(text);
            caption.setPreferredSize(new Dimension(widthWindow/3, 27));
            caption.setMinimumSize(caption.getPreferredSize());
            caption.setMaximumSize(caption.getPreferredSize());
    //        caption.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            caption.setHorizontalAlignment(JLabel.RIGHT);       
            caption.setAlignmentY(TOP_ALIGNMENT);
            caption.setFont(new Font("Tahoma",0,12));
            this.add(caption);
            
            this.add(Box.createRigidArea(new Dimension(5, 0)));
            list = new JList<>();
            list.setModel(getListModel());
            list.setCellRenderer(createListBooksRenderer());
            list.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HEIGHT);
        } 
//            JScrollPane scrollPane = new JScrollPane(list);
//            scrollPane.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOWS-20, 120));
//            scrollPane.setMaximumSize(scrollPane.getPreferredSize());
//            scrollPane.setMinimumSize(scrollPane.getPreferredSize());
//            scrollPane.setAlignmentX(LEFT_ALIGNMENT);
//            scrollPane.setAlignmentY(TOP_ALIGNMENT);
//            this.add(scrollPane);
             
    }

    public ListModel<Book> getListModel() {        
        return getListModel(false);
    }
    
    public ListModel<Book> getListModel(boolean allBooks) {
        BookFacade bookFacade = new BookFacade();
        List<Book> books=null;
        if(allBooks){
            books = bookFacade.findAll();
        }else{
            books = bookFacade.findEnabledBook();
        }
        DefaultListModel<Book> defaultListModel = new DefaultListModel<>();
        for (Book book : books){
            defaultListModel.addElement(book);
        }
        return defaultListModel;
    }

    private ListCellRenderer<? super Book> createListBooksRenderer() {
        return new DefaultListCellRenderer(){
            private final Color background = new Color(0, 100, 255, 15);
            private final Color defaultBackground = (Color) UIManager.get("List.background");
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, 
                    boolean cellHasFocus){
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(component instanceof JLabel){
                    JLabel label = (JLabel) component;
                    Book book = (Book) value;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < book.getAuthor().size(); i++) {
                        Autor autor = book.getAuthor().get(i);
                        sb.append(autor.getName()).append(" ").append(autor.getLastname()).append(". ");
                    }
                    if(book.getCount() > 0){
                        label.setText(String.format("%d. %s %s%n", book.getId(), book.getCaption(), sb.toString()));
                    }  else{
                        label.setText(String.format("%d. %s %s нет в наличии %n", book.getId(), book.getCaption(), sb.toString()));
                        label.setForeground(Color.RED);
                    }                  
                    if(!isSelected){
                        label.setBackground(index % 2 == 0 ? background : defaultBackground);
                    }
                }
                return component;
            }
        };
    }
    
    public JList<Book> getJList(){
        return list;
    }
}
