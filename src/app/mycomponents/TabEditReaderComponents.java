/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Reader;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class TabEditReaderComponents extends JPanel{   

    private InfoComponent infoComponent;
    private CaptionComponent captionComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastNameComponent;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    private ComboBoxModel comboBoxModel;
    private Reader reader;
    
    public TabEditReaderComponents(int widthPanel, ComboBoxModel comboBoxModel) {
        this.comboBoxModel = comboBoxModel;
        initComponent(widthPanel);
    }

    private void initComponent(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabAddReader = new JTabbedPane();
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Изменить данные читателя", widthPanel, 31);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        nameComponent = new EditorComponent("Имя", widthPanel, 31, 300);
        lastNameComponent = new EditorComponent("Фамилия", widthPanel, 31, 100);
        phoneComponent = new EditorComponent("Телефон", widthPanel, 31, 300);
        comboBoxReadersComponent = new ComboBoxReadersComponent("Читатели", widthPanel, 31, 300);
        comboBoxReadersComponent.getComboBox().setModel(addComboBoxModel());
        comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);
        comboBoxReadersComponent.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                reader=(Reader) ie.getItem();
                nameComponent.getEditor().setText(reader.getFirstname());
                lastNameComponent.getEditor().setText(reader.getLastname());
                phoneComponent.getEditor().setText(reader.getPhone());
            }
        });
        this.add(comboBoxReadersComponent);       
        this.add(Box.createRigidArea(new Dimension(0,10)));        
        this.add(nameComponent);        
        this.add(lastNameComponent);        
        this.add(phoneComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        buttonComponent = new ButtonComponent("изменить данные читателя", widthPanel, 35, widthPanel/3+5, 200);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButton());
    }
    
    private ActionListener clickToButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ReaderFacade readerFacade = new ReaderFacade();               
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите имя читателя");
                    return;
                }
                reader.setFirstname(nameComponent.getEditor().getText());
                
                if(lastNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите фамилию читателя");
                    return;
                }
                reader.setLastname(lastNameComponent.getEditor().getText());
                
                if(phoneComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setText("Введите телефон читателя");
                    return;
                }
                reader.setPhone(phoneComponent.getEditor().getText());
                
                
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
    
    public ComboBoxModel<Reader> addComboBoxModel(){
        nameComponent.getEditor().setText("");
        lastNameComponent.getEditor().setText("");
        phoneComponent.getEditor().setText("");
        infoComponent.getInfo().setText("");
        ReaderFacade readerFacade = new ReaderFacade();
        List<Reader> readers = readerFacade.findAll();
        DefaultComboBoxModel<Reader> defaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Reader reader : readers) {
            defaultComboBoxModel.addElement(reader);
        }
        comboBoxModel = defaultComboBoxModel;
        comboBoxReadersComponent.getComboBox().setModel(comboBoxModel);
        return comboBoxModel;
    }
}


