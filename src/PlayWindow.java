import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PlayWindow extends JFrame implements ActionListener{
	
	private JLabel 	TableOfScore;
	private int xScore = 0;
    private int oScore = 0;
    private JButton buttonsXO[];
    private JButton btnReset;
    private JButton btnClear; 
    private JButton btnBackToMain; 
    private final static int BUTTON_XO_WIDTH = 80;
    private final static int BUTTON_XO_HEIGHT = 80; 
	private final static int POSITION_XO_H[] = {30, BUTTON_XO_WIDTH * 1 + 30, BUTTON_XO_WIDTH * 2 + 30, BUTTON_XO_WIDTH * 3 + 30}; 
	private final static int POSITION_XO_V[] = {120, BUTTON_XO_WIDTH * 1 + 120, BUTTON_XO_WIDTH * 2 + 120, BUTTON_XO_WIDTH * 3 + 120 + 20}; 
	private static int i = 0; 

	private final int CHOIX_LEVEL;
 
    private final int CHOIX_FRIEND = 0;
    private final int CHOIX_EASY 	= 1;
    private final int CHOIX_MEDIUM = 2;
    private final int CHOIX_HARD	= 3;
    
   
    private static boolean player1 = true; 
    
    
    private static Random rand = new Random(); 
    private boolean printRand = true; 
    
    
    private int arrayRows[] = new int[8]; 
    private boolean mCenterFirst = false;
    private boolean mCornerFirst = false;
    private boolean mHvFirst = false;
    private boolean mHvAfterCorner = false;
    private boolean mCornerAfterHv = false;
    private boolean mCornerAfterCenter = false;
    private boolean mHvAfterHv = false;
    private int mCounter = 0;
    private boolean mCorner = false;
    private boolean mHvAfterCenter = false;  
    
    PlayWindow(int CHOIX_LEVEL) { 
    	
    	this.CHOIX_LEVEL = CHOIX_LEVEL;
    	
    	if (this.CHOIX_LEVEL == CHOIX_FRIEND)
    		this.setTitle("Play 2 Players");
    	else if (this.CHOIX_LEVEL == CHOIX_EASY)
    		this.setTitle("Play With PC Easy");
    	else if (this.CHOIX_LEVEL == CHOIX_MEDIUM)
    		this.setTitle("Play With PC Medium");
    	else
    		this.setTitle("Play With PC Hard");
    	
    	TableOfScore = new JLabel("");
        printScore(xScore, oScore); 
        TableOfScore.setBounds(170, 10, 200, 100); 
        TableOfScore.setFont(new Font("Arial Black", Font.PLAIN, 14)); 
        this.add(TableOfScore);

        buttonsXO = new JButton[9];        

        for (i = 0; i < buttonsXO.length; i++) { 
            buttonsXO[i] = new JButton();
            if (i < 3) {
                buttonsXO[i].setBounds(POSITION_XO_H[i], POSITION_XO_V[0], BUTTON_XO_WIDTH, BUTTON_XO_HEIGHT);
            } else if (i < 6) {
                buttonsXO[i].setBounds(POSITION_XO_H[i - 3], POSITION_XO_V[1], BUTTON_XO_WIDTH, BUTTON_XO_HEIGHT);
            } else if (i < 9) {
                buttonsXO[i].setBounds(POSITION_XO_H[i - 6], POSITION_XO_V[2], BUTTON_XO_WIDTH, BUTTON_XO_HEIGHT);
            }

            buttonsXO[i].setFont(new Font("Arial Black", Font.PLAIN, 26));
            buttonsXO[i].setBackground(Color.WHITE); 
            this.add(buttonsXO[i]);
        }
        for (i = 0; i < 9; i++) {
            buttonsXO[i].addActionListener(this);
        }

        btnReset = new JButton("Reset");
        btnReset.setBounds(POSITION_XO_H[0], POSITION_XO_V[3], BUTTON_XO_WIDTH * 3 / 2 - 10, 40);
        btnReset.setFont(new Font("Arial Black", Font.PLAIN, 14));
        btnReset.addActionListener(event -> {
        	clear();
        	resetScore();
        });
        this.add(btnReset);

        btnClear = new JButton("Clear");
        btnClear.setBounds(POSITION_XO_H[0] + btnReset.getWidth() + 20, POSITION_XO_V[3], BUTTON_XO_WIDTH * 3 / 2 - 10, 40);
        btnClear.setFont(new Font("Arial Black", Font.PLAIN, 14));
        btnClear.addActionListener(event -> {
        	clear();
        });
        this.add(btnClear);

        btnBackToMain = new JButton("Back To Main Menu");
        btnBackToMain.setBounds(POSITION_XO_H[0], POSITION_XO_V[3] + 50, BUTTON_XO_WIDTH * 3, 40);
        btnBackToMain.setFont(new Font("Arial Black", Font.PLAIN, 14));
        btnBackToMain.addActionListener(event -> {
        	this.setVisible(false);
        	new MainWindow();
        });
        add(btnBackToMain);

        this.setBounds(400, 170, buttonsXO[0].getWidth() * 3 + 75, TableOfScore.getHeight() + buttonsXO[0].getHeight() * 3 + btnReset.getHeight() + btnBackToMain.getHeight() + 120);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    	
    	if (CHOIX_LEVEL == CHOIX_FRIEND) {
    		for(i = 0; i < 9; i++){
        		if (e.getSource().equals(buttonsXO[i])) 
        			printXOForFriend(i); 
    	    }
    	}else if (CHOIX_LEVEL == CHOIX_EASY) {
    		for (i = 0; i < 9; i++) {
                if (e.getSource() == buttonsXO[i])
                    printXOForEasy(i);
            }
		}else if (CHOIX_LEVEL == CHOIX_MEDIUM) {
			for (i = 0; i < 9; i++)
                if (e.getSource() == buttonsXO[i]){
                    if (buttonsXO[i].getText().equals("")) {
                        printXOForMeMedium(i);
                        
                        if(getResult(true)) {
                            printXOForPcMedium();
                            getResult(false);
                        }
                    } 
                }	
		}else if (CHOIX_LEVEL == CHOIX_HARD) {
			for (i = 0; i < 9; i++)
                if (e.getSource() == buttonsXO[i]){
                    if (buttonsXO[i].getText().equals("")) {
                        printXOForMeHard(i);
                        
                        if(getResult(true)) {
                            printXOForPcHard();
                            getResult(false);
                        }
                    } 
                }
		}

    }
    
    
    
    private void printScore(int x, int o) { 
        String xFormat = setColorOnly(String.valueOf(x), "red"),
                oFormat = setColorOnly(String.valueOf(o), "blue");
        if (x > o) {
            xFormat = setColorOnly(String.valueOf(x), "red");
            oFormat = setColorOnly(String.valueOf(o), "blue");
        } else if (x < o) {
            xFormat = setColorOnly(String.valueOf(x), "red");
            oFormat = setColorOnly(String.valueOf(o), "blue");
        }
        
        String p1 = "You";
        String p2 = "Pc";
        
        if (CHOIX_LEVEL == CHOIX_FRIEND) {
        	p1 = "X";
        	p2 = "O";
        }
        	
        TableOfScore.setText("<html><table border='1'><tr><th colspan='2'>Score :&nbsp;&nbsp;&nbsp;</th></tr>"
                                            + "<tr><td><b>"+ p1 + "</b></td><td>" + xFormat + "</td></tr>"
                                            + "<tr><td><b>"+ p2 + "</b></td><td>" + oFormat + "</td></tr></html>");
    }
    
	private boolean getResult(boolean Player1Win) { 
        if (((buttonsXO[0].getText().equals(buttonsXO[3].getText())) && (buttonsXO[0].getText().equals(buttonsXO[6].getText())) && (!buttonsXO[0].getText().equals("")))
                || ((buttonsXO[1].getText().equals(buttonsXO[4].getText())) && (buttonsXO[1].getText().equals(buttonsXO[7].getText())) && (!buttonsXO[1].getText().equals("")))
                || ((buttonsXO[2].getText().equals(buttonsXO[5].getText())) && (buttonsXO[2].getText().equals(buttonsXO[8].getText())) && (!buttonsXO[2].getText().equals("")))
                || ((buttonsXO[0].getText().equals(buttonsXO[1].getText())) && (buttonsXO[0].getText().equals(buttonsXO[2].getText())) && (!buttonsXO[0].getText().equals("")))
                || ((buttonsXO[3].getText().equals(buttonsXO[4].getText())) && (buttonsXO[3].getText().equals(buttonsXO[5].getText())) && (!buttonsXO[3].getText().equals("")))
                || ((buttonsXO[6].getText().equals(buttonsXO[7].getText())) && (buttonsXO[6].getText().equals(buttonsXO[8].getText())) && (!buttonsXO[6].getText().equals("")))
                || ((buttonsXO[0].getText().equals(buttonsXO[4].getText())) && (buttonsXO[0].getText().equals(buttonsXO[8].getText())) && (!buttonsXO[0].getText().equals("")))
                || ((buttonsXO[2].getText().equals(buttonsXO[4].getText())) && (buttonsXO[2].getText().equals(buttonsXO[6].getText())) && (!buttonsXO[2].getText().equals("")))) {
        	
            if (Player1Win) {
                xScore++;
                if (CHOIX_LEVEL == CHOIX_FRIEND)
                	JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("Player 1 (X) ", "red") + setColorOnly("Win !" , "red")  + "</html>");
                else
                	JOptionPane.showMessageDialog(null, setColor("You Win !" , "red"));
            } else {
                oScore++;
                if (CHOIX_LEVEL == CHOIX_FRIEND)
                	JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("Player 2 (O) ", "blue") + setColorOnly("Win !" , "blue")  + "</html>");
                else
                	JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("You Lose ", "blue") + "<br>" + setColor("PC Win !", "blue") + "</html>");
            }
            printScore(xScore, oScore);
            clear();
            return false;
        } else {
            if (!buttonsXO[0].getText().equals("")
	                && !buttonsXO[1].getText().equals("")
	                && !buttonsXO[2].getText().equals("")
	                && !buttonsXO[3].getText().equals("")
	                && !buttonsXO[4].getText().equals("")
	                && !buttonsXO[5].getText().equals("")
	                && !buttonsXO[6].getText().equals("")
	                && !buttonsXO[7].getText().equals("")
	                && !buttonsXO[8].getText().equals("")) {
                JOptionPane.showMessageDialog(null, " Draw !");
                clear();
                return false;
            } else {
                printRand = true;
            }
        }
        return true;
    }
	
    private void clear() { 
        for (i = 0; i < 9; i++) {
            buttonsXO[i].setText(""); 
        }
        if(CHOIX_LEVEL == CHOIX_EASY) 
        	printRand = false;
        else { 
        	for (i = 0; i < 8; i++)
                arrayRows[i] = 0;
        	mCenterFirst = false;
            mCornerFirst = false;
            mHvFirst = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mCorner = false;
            mHvAfterCenter = false;
            mCounter = 0;
        }
    }

    private void resetScore() { 
        clear(); 
        printScore(xScore = 0, oScore = 0); 
    }
    
    protected static String setColor(String before, String color) { 
        return "<html><font color='" + color + "'>" + before + "</font></html>";
    }
    
    private static String setColorOnly(String before, String color) { 
        return "<font color='" + color + "'>" + before + "</font>";
    }

    
    
    private void printXOForFriend(int index) { 
    	if (buttonsXO[index].getText().equals("")) {
            if (player1) { 
            	buttonsXO[index].setText(setColor("X", "red"));
            	getResult(player1); 
                player1 = false;
            } else { 
            	buttonsXO[index].setText(setColor("O", "blue"));
            	getResult(player1); 
                player1 = true;
            }
    	}
    }
    
    
    
    private void printXOForEasy(int i) { 
        if (buttonsXO[i].getText().equals("")) { 
            buttonsXO[i].setText(setColor("X", "red"));
            getResult(true);

            if (printRand) {
                while (true) { 
                    int index = rand.nextInt(9); 
                    if (buttonsXO[index].getText().equals("")) {
                    	try{
                    		Thread.sleep(100); 
                    	}catch (InterruptedException e){}
                    	
                        buttonsXO[index].setText(setColor("O", "blue"));
                        getResult(false);
                        break;
                    }
                }
            }
        }
    }

    
    
    private void printXOForMeMedium(int index) {
        buttonsXO[index].setText(setColor("X", "red"));
        if (index == 4) {
            if (mCounter == 0) { 
                mCenterFirst = true;
            }
        } else if (index == 0 || index == 2 || index == 6 || index == 8) { 
            mCorner = true;
        } else {
            mCorner = false;
        }
        fillArray(index, 1);
        mCounter++;
    }
    
    private void printXOForMeHard(int index) { 
        buttonsXO[index].setText(setColor("X", "red"));
        if (index == 4) {
            if (mCounter == 0) { 
                mCenterFirst = true;
            }
        } else if (index == 0 || index == 2 || index == 6 || index == 8) { 
            if (mCounter == 0) { 
                mCornerFirst = true;
            } else {
                if (mCenterFirst) {
                    mCornerAfterCenter = true;
                    mCenterFirst = false;
                }
                mCorner = true;
                mCornerFirst = false;
                if (mHvFirst) {
                    mCornerAfterHv = true;
                }
                mHvFirst = false;
            }
        } else {
            if (mCounter == 0) {
                mHvFirst = true;
            } else {
                if (mCornerFirst) {
                    mHvAfterCorner = true;
                    mCornerFirst = false;
                } else if (mHvFirst) {
                    mHvAfterHv = true;
                    mHvFirst = false;
                } else if (mCenterFirst) {
                    mHvAfterCenter = true;
                    mCornerAfterCenter = false;
                    mCenterFirst = false;
                } else {
                    mCorner = false;
                }
            }
        }
        fillArray(index, 1);
        mCounter++;  
    }
    
    private void printXOForPcMedium() {
        if (mCenterFirst) {
            
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
            mCenterFirst = false;
        } else if (buttonsXO[4].getText().equals("")) {
            fillCasePc(4);
        } else if (meWantWin() != -1) {
            
            int indexOfTheRow = meWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (adverserWantWin() != -1) {
            
            int indexOfTheRow = adverserWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (mCorner) {
            
            if (buttonsXO[1].getText().equals("")) {
                fillCasePc(1);
            } else if (buttonsXO[3].getText().equals("")) {
                fillCasePc(3);
            } else if (buttonsXO[5].getText().equals("")) {
                fillCasePc(5);
            } else if (buttonsXO[7].getText().equals("")) {
                fillCasePc(7);
            }
        } else {
                if (buttonsXO[0].getText().equals("")) {
                    fillCasePc(0);
                } else if (buttonsXO[2].getText().equals("")) {
                    fillCasePc(2);
                } else if (buttonsXO[6].getText().equals("")) {
                    fillCasePc(6);
                } else if (buttonsXO[8].getText().equals("")) {
                    fillCasePc(8);
                }
            }
    }
    
    private void printXOForPcHard() {
        if (mCenterFirst) {
            
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
        } else if (mCornerFirst) {
            
            fillCasePc(4);
        } else if (mHvFirst) {
            fillCasePc(4);
            
        } else if (mHvAfterHv) {
            int indexOfTheBestCorner = getBestCorner();
            fillCasePc(indexOfTheBestCorner);
            mCorner = false;
            mHvAfterHv = false;
            
        } else if (mCornerAfterCenter) {
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
            mCornerAfterCenter = false;
            
        } else if (meWantWin() != -1) {
            
            int indexOfTheRow = meWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (adverserWantWin() != -1) {
            
            int indexOfTheRow = adverserWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (mCornerAfterHv) {
            int indexOfBestHV = getBestHV();
            fillCasePc(indexOfBestHV);
            mCorner = false;
            mCornerAfterHv = false;
            
        } else if (mHvAfterCorner) {
            int indexOfBestHV = getBestHV();
            fillCasePc(indexOfBestHV);
            mCorner = false;
            mHvAfterCorner = false;
        } else if (mCorner) {
            
            if (buttonsXO[1].getText().equals("")) {
                fillCasePc(1);
            } else if (buttonsXO[3].getText().equals("")) {
                fillCasePc(3);
            } else if (buttonsXO[5].getText().equals("")) {
                fillCasePc(5);
            } else if (buttonsXO[7].getText().equals("")) {
                fillCasePc(7);
            }
        } else {
            if (mCounter <= 4) {
                int indexOfTheBestCorner = getBestCorner();
                fillCasePc(indexOfTheBestCorner);
                mCorner = false;
                

            } else {
                if (buttonsXO[0].getText().equals("")) {
                    fillCasePc(0);
                } else if (buttonsXO[2].getText().equals("")) {
                    fillCasePc(2);
                } else if (buttonsXO[6].getText().equals("")) {
                    fillCasePc(6);
                } else if (buttonsXO[8].getText().equals("")) {
                    fillCasePc(8);
                }
            }
        }
    }
    
    private void fillArray(int cases, int current) {
        switch (cases) {
            case 0:
                arrayRows[0] += current;
                arrayRows[3] += current;
                arrayRows[7] += current;
                break;
            case 1:
                arrayRows[0] += current;
                arrayRows[4] += current;
                break;
            case 2:
                arrayRows[0] += current;
                arrayRows[5] += current;
                arrayRows[6] += current;
                break;
            case 3:
                arrayRows[1] += current;
                arrayRows[3] += current;
                break;
            case 4:
                arrayRows[1] += current;
                arrayRows[4] += current;
                arrayRows[6] += current;
                arrayRows[7] += current;
                break;
            case 5:
                arrayRows[1] += current;
                arrayRows[5] += current;
                break;
            case 6:
                arrayRows[2] += current;
                arrayRows[3] += current;
                arrayRows[6] += current;
                break;
            case 7:
                arrayRows[2] += current;
                arrayRows[4] += current;
                break;
            case 8:
                arrayRows[2] += current;
                arrayRows[5] += current;
                arrayRows[7] += current;
                break;
        }
    }
    
    private void fillCasePc(int index){
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){}
        
        buttonsXO[index].setText(setColor("O", "blue"));
        fillArray(index, -1);
        mCounter++;
        mCorner = false;
    }

    private int adverserWantWin() {
        int index = -1;
        for (int i = 0; i < arrayRows.length; i++)
            index = (arrayRows[i] == 2)? i: index;
        return index;
    }

    private int meWantWin() {
        int index = -1;
        for (int i = 0; i < arrayRows.length; i++)
            index = (arrayRows[i] == -2)? i : index;
        return index;
    }

    private static int[] getTargetRow(int index) {
        switch (index) {
            case 0:
                return new int[]{0, 1, 2};
            case 1:
                return new int[]{3, 4, 5};
            case 2:
                return new int[]{6, 7, 8};
            case 3:
                return new int[]{0, 3, 6};
            case 4:
                return new int[]{1, 4, 7};
            case 5:
                return new int[]{2, 5, 8};
            case 6:
                return new int[]{2, 4, 6};
            case 7:
                return new int[]{0, 4, 8};
            default:
                return new int[]{};
        }
    }

    private int getIndexOfTheEmptyCase(int[] array) {
        int index = -1;
        for (int i = 0; i < 3; i++)
            if (buttonsXO[array[i]].getText().equals(""))
                index = array[i];
        
        return index;
    }

    private int getBestHV() {
        int ZeroFour = arrayRows[0] + arrayRows[4];
        int TwoFour = arrayRows[2] + arrayRows[4];
        int OneThree = arrayRows[1] + arrayRows[3];
        int OneFive = arrayRows[1] + arrayRows[5];

        if ((ZeroFour <= OneFive) && (ZeroFour <= TwoFour) && (ZeroFour <= OneThree)) {
            return 1;
        } else if ((TwoFour <= OneThree) && (TwoFour <= ZeroFour) && (TwoFour <= OneFive)) {
            return 7;
        } else if ((OneThree <= TwoFour) && (OneThree <= ZeroFour) && (OneThree <= OneFive)) {
            return 3;
        } else {
            return 5;
        }
    }

    private int getBestCorner() {
        int ZeroThree = arrayRows[0] + arrayRows[3];
        int TwoThree = arrayRows[2] + arrayRows[3];
        int ZeroFive = arrayRows[0] + arrayRows[5];
        int TwoFive = arrayRows[2] + arrayRows[5];

        if ((ZeroThree >= TwoThree) && (ZeroThree >= ZeroFive) && (ZeroThree >= TwoFive)) {
            return 0;
        } else if ((TwoThree >= ZeroThree) && (TwoThree >= ZeroFive) && (TwoThree >= TwoFive)) {
            return 6;
        } else if ((ZeroFive >= ZeroThree) && (ZeroFive >= TwoThree) && (ZeroFive >= TwoFive)) {
            return 2;
        } else {
            return 8;
        }
    }
}