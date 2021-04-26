import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;



public class AboutWindow extends JDialog implements MouseListener {
    
     private static JLabel labelCopyRight;
     private static JLabel labelAbout;
     private static JLabel labelAbout2;
     private static JLabel labelAbout3;
     private JButton btnBackToMain;
     private final Font FontForLabel2 = new Font("Arial", Font.PLAIN, 14);
     private final Font FontForLabel = new Font("Arial Black", Font.PLAIN, 16);
    
    public AboutWindow() {
        
        labelAbout = new JLabel(" This is classic Tic Tac Toe game that you");
        labelAbout.setBounds(30, 100, 250, 50);
        labelAbout.setFont(FontForLabel2);
        this.add(labelAbout);
        labelAbout2 = new JLabel(" can play with your friends or against AI");
        labelAbout2.setBounds(30, 120, 250, 50);
        labelAbout2.setFont(FontForLabel2);
        this.add(labelAbout2);
        labelAbout3 = new JLabel("                  TicTacToe v1.0");
        labelAbout3.setBounds(30, 140, 250, 50);
        labelAbout3.setFont(FontForLabel2);
        this.add(labelAbout3);
        labelCopyRight = new JLabel("  Copyright Ⓒ Group5, All Right Reserved 2021.");
        labelCopyRight.setBounds(30, 330, 300, 30);
        labelCopyRight.setFont(FontForLabel2);
        this.add(labelCopyRight);
        
        
        this.setBounds(400, 200, 330, 400);
        this.setTitle("About This App");
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setVisible(true);
        
        btnBackToMain = new JButton("Back To Main Menu");
        btnBackToMain.setBounds(20, 250, 250, 50);
        btnBackToMain.setFont(FontForLabel);
        btnBackToMain.addActionListener(event -> {
        	this.setVisible(false);
        	new MainWindow();
        });
        add(btnBackToMain);
        }

    AboutWindow(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
