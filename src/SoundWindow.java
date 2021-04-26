import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;



public class SoundWindow extends JDialog implements MouseListener {
    
    private static JLabel labelInfo;
    private JButton btnBackToMain;
    private final Font FontForLabel = new Font("Arial Black", Font.PLAIN, 16);
    private final Font FontForLabel2 = new Font("Arial Black", Font.PLAIN, 14);
    
    public SoundWindow() {
        
        labelInfo = new JLabel("            Coming Soon :) ");
        labelInfo.setBounds(30, 100, 250, 50);
        labelInfo.setFont(FontForLabel2);
        this.add(labelInfo);
        
        this.setBounds(400, 200, 330, 400);
        this.setTitle("Sound");
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

    SoundWindow(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
