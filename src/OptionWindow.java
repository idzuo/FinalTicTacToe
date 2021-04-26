import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

public class OptionWindow extends JDialog implements MouseListener {
    
    private final JLabel labelSelect;
    private final JButton btnSound;
    private final JButton btnAbout;
    private final Font FontForLabel = new Font("Arial Black", Font.PLAIN, 16);
    private static int i = 0;
    private JButton btnBackToMain;
    
    public OptionWindow() {
        
        labelSelect = new JLabel(PlayWindow.setColor("Select  :", "red"));
        labelSelect.setBounds(20, 40, 315, 50);
        labelSelect.setFont(FontForLabel);
        this.add(labelSelect);
        
        btnSound = new JButton("Sound");
        btnSound.setBounds(20, 100, 250, 50);
        btnSound.setFont(FontForLabel);
        btnSound.addActionListener(event -> {
        	new SoundWindow();
            this.setVisible(false);
        });
        this.add(btnSound);
        
        btnAbout = new JButton("About");
        btnAbout.setBounds(20, 160, 250, 50);
        btnAbout.setFont(FontForLabel);
        btnAbout.addActionListener(event -> {
        	new AboutWindow();
            this.setVisible(false);
        });
        this.add(btnAbout);
        
        btnBackToMain = new JButton("Back To Main Menu");
        btnBackToMain.setBounds(20, 240, 250, 50);
        btnBackToMain.setFont(FontForLabel);
        btnBackToMain.addActionListener(event -> {
        	this.setVisible(false);
        	new MainWindow();
        });
        add(btnBackToMain);
        
        setTitle("Option Menu"); 
        setBounds(400, 200, 330, 400);
        setLayout(null);
        setVisible(true);
        
        btnBackToMain = new JButton("Back To Main");
        btnBackToMain.setFont(FontForLabel);
        btnBackToMain.addActionListener(event -> {
        	this.setVisible(false);
        	new MainWindow();
        });
        add(btnBackToMain);
    }

    public static void main(String[] args) {
        new OptionWindow().setVisible(true);
    }

    OptionWindow(int i) {
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