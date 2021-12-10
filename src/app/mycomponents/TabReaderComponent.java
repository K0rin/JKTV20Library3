/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Autor;
import entity.Book;
import entity.Reader;
import facade.BookFacade;
import facade.ReaderFacade;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class TabReaderComponent extends JPanel{
    
    private InfoComponent infoComponent1;
    private CaptionComponent captionComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastName;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    
    public TabReaderComponent(int widthWindow) {
        initComponent(widthWindow);
    }

    private void initComponent(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabReader = new JTabbedPane();
        TabAddReaderComponents tabAddReaderComponents = new TabAddReaderComponents(widthPanel);
        tabReader.addTab("Добавить читателя", tabAddReaderComponents);
        TabEditReaderComponents tabEditReaderComponents = new TabEditReaderComponents(widthPanel);
        tabReader.addTab("Изменить читателя", tabEditReaderComponents);
        this.add(tabReader);
    }
        
}
