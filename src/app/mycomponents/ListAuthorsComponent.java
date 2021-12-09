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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pupil
 */
public class ListAuthorsComponent extends JPanel{
    
    private JLabel caption;
    private JList list;

    public ListAuthorsComponent(String text, int widthWindow, int heightPanel, int listWidth) {
        initComponents(text, widthWindow, heightPanel, listWidth);
    }
    
    private void initComponents(String text, int widthWindow, int heightPanel, int editorWidth) {
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
        caption.setVerticalAlignment(JLabel.TOP);
        caption.setFont(new Font("Tahoma",0,12));
        this.add(caption);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        list = new JList();
        
    }
}
