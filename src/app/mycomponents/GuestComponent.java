/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import app.GuiApp;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author pupil
 */
public class GuestComponent extends JPanel{
    private ListBooksComponent listBooksComponent;
    private GuestButtonComponent guestButtonComponent;

    public GuestComponent() {
        initComponents();
        this.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOWS, GuiApp.HEIGHT_WINDOWS));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
    }

    private void initComponents() {
        listBooksComponent = new ListBooksComponent(true, "Книги", GuiApp.WIDTH_WINDOWS, GuiApp.HEIGHT_WINDOWS, 400);
        this.add(listBooksComponent);
        guestButtonComponent = new GuestButtonComponent("Войти", "Зарегистрироваться", GuiApp.WIDTH_WINDOWS, HEIGHT, WIDTH, HEIGHT, WIDTH)
        
    }
    
    
}
