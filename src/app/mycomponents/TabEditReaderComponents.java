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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class TabEditReaderComponents extends JPanel{

    public TabEditReaderComponents(int widthPanel) {
        initComponent(widthPanel);
    }
    
    
    
    private InfoComponent infoComponent1;
    private CaptionComponent captionComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastName;
    private EditorComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxReadersComponent ComboBoxReadersComponent;
    
   

    private void initComponent(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabAddReader = new JTabbedPane();
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Изменить читателя", widthPanel, 31);
        this.add(captionComponent);
        ComboBoxReadersComponent = new ComboBoxReadersComponent("Читатели", widthPanel, 31, 300);
        this.add(ComboBoxReadersComponent);
        infoComponent1 = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent1);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditorComponent("Имя", widthPanel, 31, 300);
        this.add(nameComponent);
        lastName = new EditorComponent("Фамилия", widthPanel, 31, 100);
        this.add(lastName);
        phoneComponent = new EditorComponent("Телефон", widthPanel, 31, 300);
        this.add(phoneComponent);
        buttonComponent = new ButtonComponent("Добавить читателя", widthPanel, 31, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButton());
    }
    
    private ActionListener clickToButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Reader reader = new Reader();
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent1.getInfo().setText("Введите имя читателя");
                    return;
                }
                reader.setFirstname(nameComponent.getEditor().getText());
                
                if(lastName.getEditor().getText().isEmpty()){
                    infoComponent1.getInfo().setText("Введите фамилию читателя");
                    return;
                }
                reader.setLastname(lastName.getEditor().getText());
                
                if(phoneComponent.getEditor().getText().isEmpty()){
                    infoComponent1.getInfo().setText("Введите телефон читателя");
                    return;
                }
                reader.setPhone(phoneComponent.getEditor().getText());
                ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                try {
                   readerFacade.create(reader);
                   infoComponent1.getInfo().setText("ОК");
                   nameComponent.getEditor().setText("");
                   lastName.getEditor().setText("");
                   phoneComponent.getEditor().setText("");
                } catch (Exception e) {
                   infoComponent1.getInfo().setText("Error");
                }
            }
        };
        
    }
}


