/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRole;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
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
public class TabAddReaderComponents extends JPanel{

    public TabAddReaderComponents(int widthPanel) {
        initComponent(widthPanel);
    }
    
    
    
    private InfoComponent infoComponent1;
    private CaptionComponent captionComponent;
    private EditorComponent nameComponent;
    private EditorComponent lastName;
    private EditorComponent phoneComponent;
    private EditorComponent loginComponent;
    private EditorComponent passwordComponent;
    private ButtonComponent buttonComponent;
    
   

    private void initComponent(int widthPanel) {
        this.setPreferredSize(new Dimension(widthPanel, 450));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        JTabbedPane tabAddReader = new JTabbedPane();
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        captionComponent = new CaptionComponent("Регистрация нового читателя", widthPanel, 31);
        this.add(captionComponent);
        infoComponent1 = new InfoComponent("", widthPanel, 31);
        this.add(infoComponent1);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditorComponent("Имя", widthPanel, 31, 300);
        this.add(nameComponent);
        lastName = new EditorComponent("Фамилия", widthPanel, 31, 100);
        this.add(lastName);
        phoneComponent = new EditorComponent("Телефон", widthPanel, 31, 300);
        this.add(phoneComponent);
        loginComponent = new EditorComponent("Логин", widthPanel, 31, 300);
        this.add(loginComponent);
        passwordComponent = new EditorComponent("Пароль", widthPanel, 31, 300);
        this.add(passwordComponent);
        buttonComponent = new ButtonComponent("Добавить читателя", widthPanel, 31, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(clickToButton());
    }
    
    private ActionListener clickToButton(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                User user = new User();
                Reader reader = new Reader();
                UserRole userRole = new UserRole();
                Role role = new Role();
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
                if(loginComponent.getEditor().getText().isEmpty()){
                    infoComponent1.getInfo().setText("Введите login читателя");
                    return;
                }
                user.setLogin(loginComponent.getEditor().getText());
                if(passwordComponent.getEditor().getText().isEmpty()){
                    infoComponent1.getInfo().setText("Введите password читателя");
                    return;
                }
                user.setPassword(passwordComponent.getEditor().getText());
                user.setReader(reader);
                RoleFacade roleFacade = new RoleFacade();
                
                userRole.setUserId(user);
                userRole.setRoleId(roleFacade.find((long)3));
                
                ReaderFacade readerFacade = new ReaderFacade();
                UserFacade userFacade = new UserFacade();
                UserRolesFacade userRolesFacade = new UserRolesFacade();
                try {
                   readerFacade.create(reader);                   
                   userFacade.create(user);
                   userRolesFacade.create(userRole);
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


