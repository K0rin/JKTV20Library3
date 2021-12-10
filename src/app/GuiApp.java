/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.EditorComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListAuthorsComponent;
import app.mycomponents.TabBookComponent;
import app.mycomponents.TabReaderComponent;
import entity.Autor;
import entity.Book;
import facade.BookFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class GuiApp extends JFrame{
    

    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    } 
    
    private void initComponents() {
        this.setTitle("JKTV20 Library");
        this.setPreferredSize(new Dimension(600, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());               
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.setPreferredSize(new Dimension(600, 450));
        jTabbedPane.setMinimumSize(this.getPreferredSize());
        jTabbedPane.setMaximumSize(this.getPreferredSize());        
        TabBookComponent tabBookComponent = new TabBookComponent(this.getWidth());
        TabReaderComponent tabReaderComponent = new TabReaderComponent(this.getWidth());
        jTabbedPane.addTab("Книга", tabBookComponent);
        this.getContentPane().add(jTabbedPane);
        jTabbedPane.addTab("Читатель", tabReaderComponent);
        this.getContentPane().add(jTabbedPane);
        
    }
    
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

   
}
