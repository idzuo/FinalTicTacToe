
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


public class MainWindow extends JFrame implements ActionListener{
    
    private final JLabel labelSelect;
    private JLabel labelChoix;
    private final JButton btn2Player;  
    private final JButton btnWithPc;
    private final JButton btnOption;
    private JRadioButton choixWithPc[] = new JRadioButton[3];
    private boolean showHidePcPlay = false; 
    private final Font FontForLabel = new Font("Arial Black", Font.PLAIN, 16);
    private final Font FontForLabel2 = new Font("Arial Black", Font.PLAIN, 16);
    private static int i = 0;
    
    MainWindow() {
        
        labelSelect = new JLabel(PlayWindow.setColor("Select:", "red"));
        labelSelect.setBounds(20, 20, 315, 50); 
        labelSelect.setFont(FontForLabel2); 
        this.add(labelSelect);
        
        btn2Player = new JButton("2 Players");
        btn2Player.setBounds(20, 140, 250, 50);
        btn2Player.setFont(FontForLabel);
        btn2Player.addActionListener(event -> { 
        	new PlayWindow(0); 
            this.setVisible(false); 
        });
        this.add(btn2Player);
        
        btnWithPc = new JButton("Play With PC");
        btnWithPc.setBounds(20, 220, 250, 50);
        btnWithPc.setFont(FontForLabel);
        btnWithPc.addActionListener(event -> { 
        	showHidePcPlay = (showHidePcPlay)? false : true; 
        	labelChoix.setVisible(showHidePcPlay); 
        	for(i = 0; i < 3; i++)
        		choixWithPc[i].setVisible(showHidePcPlay); 
        	
            this.setSize(310, (showHidePcPlay)?450:400);
        });
        this.add(btnWithPc);
        
        labelChoix = new JLabel(PlayWindow.setColor("Please Select Level:", "red"));
        labelChoix.setBounds(30, 260, 250, 50);
        labelChoix.setFont(FontForLabel2);
        labelChoix.setVisible(false);
        this.add(labelChoix);
        
        choixWithPc[0] = new JRadioButton("Easy");
        choixWithPc[1] = new JRadioButton("Medium");
        choixWithPc[2] = new JRadioButton("Hard");
        
        for(int i = 0; i < 3; i++) {
        	choixWithPc[i].setBounds(40, 300 + 30 * i, 150, 30);
                choixWithPc[i].setFont(FontForLabel2);
        	choixWithPc[i].setVisible(false);
        	choixWithPc[i].addActionListener(this);
	        this.add(choixWithPc[i]);
        }
        
        btnOption = new JButton("Options");
        btnOption.setBounds(20, 60, 250, 50);
        btnOption.setFont(FontForLabel);
        btnOption.addActionListener(event -> {
            new OptionWindow();
            this.setVisible(false);
        });
        this.add(btnOption);
        
        setTitle("TicTacToc Game v1.0");
        setBounds(400, 200, 330, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 3; i++)
			if(e.getSource() == choixWithPc[i]) {
			    	new PlayWindow(i+1);
			        this.setVisible(false);
			}
	}
	
	public static void main(String[] args) {
        new MainWindow();
    }
}
