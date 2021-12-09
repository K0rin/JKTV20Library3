/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Autor;
import facade.AutorFacade;
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
public class ListAuthorsComponent extends JPanel{
    
    private JLabel caption;
    private JList<Autor> list;

    public ListAuthorsComponent(String text, int widthWindow, int heightPanel, int listWidth) {
        initComponents(text, widthWindow, heightPanel, listWidth);
    }
    
    private void initComponents(String text, int widthWindow, int heightPanel, int listWidth) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthWindow/3, heightPanel));
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setMaximumSize(caption.getPreferredSize());
//        caption.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        caption.setHorizontalAlignment(JLabel.RIGHT);
        caption.setAlignmentY(JLabel.TOP_ALIGNMENT);
        caption.setFont(new Font("Tahoma",0,12));
        this.add(caption);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        list = new JList<>();
        list.setModel(getListModel());
        list.setCellRenderer(createListAutorsRenderer());
        list.setSelectionMode (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HEIGHT);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(listWidth, 120));
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    private ListModel<Autor> getListModel() {
        AutorFacade authorFacade = new AutorFacade(Autor.class);
        List<Autor> authors = authorFacade.findAll();
        DefaultListModel<Autor> defaultListModel = new DefaultListModel<>();
        for (Autor autor : authors) {
            defaultListModel.addElement(autor);
        }
        return defaultListModel;
    }

    private ListCellRenderer<? super Autor> createListAutorsRenderer() {
        return new DefaultListCellRenderer(){
            private final Color background = new Color(0, 100, 255, 15);
            private final Color defaultBackground = (Color) UIManager.get("List.background");
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, 
                    boolean cellHasFocus){
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(component instanceof JLabel){
                    JLabel label = (JLabel) component;
                    Autor autor = (Autor) value;
                    label.setText(String.format("%d. %s %s%n", autor.getId(), autor.getName(), autor.getLastname()));
                    if(!isSelected){
                        label.setBackground(index % 2 == 0 ? background : defaultBackground);
                    }
                }
                return component;
            }
        };
    }
    
    public JList<Autor> getJList(){
        return list;
    }
}
