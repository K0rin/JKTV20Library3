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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author pupil
 */
public class TabReaderComponent extends JPanel{
    
    private InfoComponent infoComponent1;
    private CaptionComponent captionComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastNameComponent;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxModel comboBoxModel;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    
    
    public TabReaderComponent(int widthWindow) {
        setComboBoxModel();
        initComponent(widthWindow);
    }

    private void initComponent(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabReader = new JTabbedPane();
        tabReader.setPreferredSize(new Dimension(widthPanel-17,450));
        tabReader.setMinimumSize(tabReader.getPreferredSize());
        tabReader.setMaximumSize(tabReader.getPreferredSize());
        tabReader.setAlignmentX(CENTER_ALIGNMENT);
        TabAddReaderComponents tabAddReaderComponents = new TabAddReaderComponents(widthPanel);
        tabReader.addTab("Регистрация читателя", tabAddReaderComponents);
        TabEditReaderComponents tabEditReaderComponents = new TabEditReaderComponents(widthPanel, comboBoxModel);
        tabReader.addTab("Изменить читателя", tabEditReaderComponents);
        this.add(tabReader);
        tabReader.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                tabEditReaderComponents.addComboBoxModel();
            }
        });
    }
    
    public void setComboBoxModel(){
        ReaderFacade readerFacade = new ReaderFacade(Reader.class);
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxModel = defaultComboBoxModel;
    }
        
}
