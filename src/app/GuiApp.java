/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.mycomponents.ButtonComponent;
import app.mycomponents.CaptionComponent;
import app.mycomponents.EditorComponent;
import app.mycomponents.GuestButtonComponent;
import app.mycomponents.GuestComponent;
import app.mycomponents.InfoComponent;
import app.mycomponents.ListAuthorsComponent;
import app.mycomponents.TabAddReaderComponents;
import app.mycomponents.TabBookComponent;
import app.mycomponents.TabGiveBookComponent;
import app.mycomponents.TabLibrarerComponent;
import app.mycomponents.TabReaderComponent;
import entity.Autor;
import entity.Book;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRole;
import facade.BookFacade;
import facade.ReaderFacade;
import facade.RoleFacade;
import facade.UserFacade;
import facade.UserRolesFacade;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author pupil
 */
public class GuiApp extends JFrame{
    public static final int WIDTH_WINDOWS = 600;
    public static final int HEIGHT_WINDOWS = 450;
    private GuestComponent guestComponent;
    GuestButtonComponent guestButtonComponent;
    private TabAddReaderComponents addReaderComponents;
    private UserFacade userFacade = new UserFacade();
    private ReaderFacade readerFacade = new ReaderFacade();
    private RoleFacade roleFacade = new RoleFacade();
    private UserRolesFacade userRolesFacade = new UserRolesFacade();
    private GuiApp guiApp = this;
    
    public GuiApp() {
        List<User> users = userFacade.findAll();
        if(users.isEmpty()){
            User user = new User();
            user.setLogin("admin");
            user.setPassword("12345");
            Reader reader = new Reader();
            reader.setFirstname("admin");
            reader.setLastname("admin");
            reader.setPhone("123456");
            readerFacade.create(reader);
            user.setReader(reader);
            userFacade.create(user);
            Role role = new Role();
            role.setRoleName("ADMINISTRATOR");
            roleFacade.create(role);
            UserRole userRoles = new UserRole();
            userRoles.setUserId(user);
            userRoles.setRoleId(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            roleFacade.create(role);
            role.setRoleName("MANAGER");
            userRoles = new UserRole();
            userRoles.setUserId(user);            
            userRoles.setRoleId(role);
            userRolesFacade.create(userRoles);
            role = new Role();
            role.setRoleName("READER");
            roleFacade.create(role);
            userRoles = new UserRole();
            userRoles.setUserId(user);
            userRoles.setRoleId(role);            
            userRoles.setRoleId(role);
            userRolesFacade.create(userRoles);
            
            
        }
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    } 
    
    private void initComponents() {
        this.setTitle("JKTV20 Library");
        this.setPreferredSize(new Dimension(WIDTH_WINDOWS, HEIGHT_WINDOWS));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        guestComponent = new GuestComponent();
        guestButtonComponent = new GuestButtonComponent("Войти", "Зарегистрироваться", GuiApp.WIDTH_WINDOWS, 50,100,10,200);
        this.add(guestButtonComponent);
        guestButtonComponent.getButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JDialog dialogLogin = new JDialog(guiApp, "Введите логин и пароль", Dialog.ModalityType.DOCUMENT_MODAL);
                dialogLogin.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOWS, 300));
                dialogLogin.setMaximumSize(dialogLogin.getPreferredSize());
                dialogLogin.setMinimumSize(dialogLogin.getPreferredSize());
                dialogLogin.setLocationRelativeTo(null);
                dialogLogin.getContentPane().setLayout(new BoxLayout(dialogLogin.getContentPane(), BoxLayout.Y_AXIS));
                EditorComponent loginComponent = new EditorComponent("login", GuiApp.WIDTH_WINDOWS, 27, 200);
                EditorComponent passwordComponent = new EditorComponent("password", GuiApp.WIDTH_WINDOWS, 27, 200);
                ButtonComponent enterComponent = new ButtonComponent("Войти", GuiApp.WIDTH_WINDOWS, 27, 200, 100);
                
                dialogLogin.getContentPane().add(loginComponent);
                dialogLogin.getContentPane().add(passwordComponent);
                dialogLogin.getContentPane().add(enterComponent);
                dialogLogin.pack();
                dialogLogin.setVisible(true);
                
            }
        });
        guestButtonComponent.getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                guiApp.getContentPane().remove(guestComponent);
                addReaderComponents = new TabAddReaderComponents(GuiApp.WIDTH_WINDOWS);
                guiApp.getContentPane().add(addReaderComponents);
                guiApp.repaint();
                guiApp.revalidate();
            }
        });
        this.add(guestComponent);
        
//        JTabbedPane jTabbedPane = new JTabbedPane();
//        jTabbedPane.setPreferredSize(new Dimension(WIDTH_WINDOWS, HEIGHT_WINDOWS));
//        jTabbedPane.setMinimumSize(this.getPreferredSize());
//        jTabbedPane.setMaximumSize(this.getPreferredSize());        
//        TabBookComponent tabBookComponent = new TabBookComponent(this.getWidth());
//        TabReaderComponent tabReaderComponent = new TabReaderComponent(this.getWidth());
//        TabLibrarerComponent tabLibrarerComponent = new TabLibrarerComponent(this.getWidth());
//        jTabbedPane.addTab("Книга", tabBookComponent);
//        this.getContentPane().add(jTabbedPane);
//        jTabbedPane.addTab("Читатель", tabReaderComponent);
//        this.getContentPane().add(jTabbedPane);
//        jTabbedPane.addTab("Библиотекарь", tabLibrarerComponent);
//        this.getContentPane().add(jTabbedPane);
        
    }
    
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

   
}
