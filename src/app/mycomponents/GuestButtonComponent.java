/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mycomponents;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pupil
 */
public class GuestButtonComponent extends JPanel{
    
    private JLabel caption;
    private JButton button;
    
    /**
     * компонент кнопки 
     *@param text текст в кнопке
     *@param widthWindow ширина паели в котрой ниходиться кнопка
     * @param heightPanel высота паели в котрой ниходиться кнопка
     * @param left отступ слева от кнопки
     * @param buttonWidth ширина кнопки
     */
    
    public GuestButtonComponent(String text, String text2, int widthWindow, int heightPanel,int left, int between, int buttonWidth) {
        initComponents(text, text2, widthWindow, heightPanel, left, between, buttonWidth);
    }

    private void initComponents(String text, String text2, int widthWindow, int heightPanel,int left, int between, int buttonWidth) {
        this.setPreferredSize(new Dimension(widthWindow, heightPanel));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        button = new JButton(text);
        button.setPreferredSize(new Dimension(buttonWidth, 27));
        button.setMaximumSize(button.getPreferredSize());
        button.setMinimumSize(button.getPreferredSize());
        this.add(button);
        button = new JButton(text2);
        button.setPreferredSize(new Dimension(buttonWidth, 27));
        button.setMaximumSize(button.getPreferredSize());
        button.setMinimumSize(button.getPreferredSize());
        this.add(button);
    }

    public JButton getButton() {
        return button;
    }
}
