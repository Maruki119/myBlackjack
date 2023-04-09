import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * Written by Nititorn Kiprasopchok
 * ID: 6530300295
 */

public class UI extends JFrame{
    //private variable
    private BufferedImage imgBg, imgIconOriginal, imgIconResized;
    private JLabel imgLabel, textLabel1, textLabel2, textLabel3;
    private JLayeredPane layered;
    private Font font1 = new Font("TimesRoman", Font.BOLD, 100),
                 font2 = new Font("TimesRoman", Font.BOLD, 60),
                 font3 = new Font("TimesRoman", Font.BOLD, 50);

    //defalut variable
    Container c = getContentPane();
    JLabel winLoseLabel, betLabel;
    JButton btnStart, btnExit, btnHit, btnStand, btnNxtRound, btnAgain, btnBetOne, btnBetTwo;
    JPanel btnPanel, btnPanel2, btnBetPanel, btnBetPanel2;
	JPanel dealerPanel, playerPanel;
	JLabel playerCardLabel[] = new JLabel[6];
	JLabel dealerCardLabel[] = new JLabel[6];
    JLabel dealerScore, dealerLive, playerScore, playerLive;

    //constructor
    public UI(){
        background();
        startButton();
        playButton();
        betButton();
        playerTable();
        dealerTable();
        textUI();
    }

    //create background
    public void background(){
        try {
            imgBg = ImageIO.read(new File("background/TableBackground.jpg"));
        } catch (IOException e) {
            System.out.println("Error: background not found!");
            System.exit(0);
        }
        imgLabel = new JLabel(new ImageIcon(imgBg));
        imgLabel.setBounds(0, 0, 1280, 720);

        int xCenter = getWidth()/2;
        int yCenter = getHeight()/2;
        textLabel1 = new JLabel("WELCOME");
        textLabel1.setFont(font1);
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setBounds(xCenter + 350, yCenter - 200, 1280, 720);

        textLabel2 = new JLabel("TO 21 RESIDENT EVIL BACKJACK");
        textLabel2.setFont(font2);
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setBounds(xCenter + 125, yCenter - 50, 1280, 720);

        textLabel3 = new JLabel("NITITORN KIJPRASOPCHOK");
        textLabel3.setFont(font3);
        textLabel3.setForeground(Color.WHITE);
        textLabel3.setBounds(xCenter + 275, yCenter + 75, 1280, 720);

        //create layer for add component
        layered = new JLayeredPane();
        layered.add(imgLabel, Integer.valueOf(1));
        layered.add(textLabel1, Integer.valueOf(2)); 
        layered.add(textLabel2, Integer.valueOf(3));
        layered.add(textLabel3, Integer.valueOf(4));
        c.setBackground(Color.BLACK);
        c.add(layered);
    }

    //create start and exit button
    public void startButton(){
        btnPanel = new JPanel();
        btnPanel.setBackground(Color.BLACK);

        btnStart = new JButton("START");
        btnStart.setFont(font3);
        btnStart.setBackground(Color.BLACK);
        btnStart.setForeground(Color.YELLOW);
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);

        btnExit = new JButton("EXIT");
        btnExit.setFont(font3);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.YELLOW);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);

        btnPanel.add(btnStart); btnPanel.add(btnExit);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textLabel1.setVisible(false);
                textLabel2.setVisible(false);
                textLabel3.setVisible(false);

                btnPanel.setVisible(false);
                btnPanel2.setVisible(true); //มาปรับทีหลัง

                playerPanel.setVisible(true);
                dealerPanel.setVisible(true);

                dealerScore.setVisible(true);
                dealerLive.setVisible(true);
                playerScore.setVisible(true);
                playerLive.setVisible(true);

                btnHit.setVisible(false);
                btnStand.setVisible(false);
                btnBetPanel.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }

    //create hit and stand button
    public void playButton(){
        btnPanel2 = new JPanel(new GridLayout(3, 1));

        btnHit = new JButton("HIT");
        btnHit.setFont(font3);
        btnHit.setBackground(Color.BLACK);
        btnHit.setForeground(Color.YELLOW);
        btnHit.setBorderPainted(false);
        btnHit.setFocusPainted(false);

        btnStand = new JButton("STAND");
        btnStand.setFont(font3);
        btnStand.setBackground(Color.BLACK);
        btnStand.setForeground(Color.YELLOW);
        btnStand.setBorderPainted(false);
        btnStand.setFocusPainted(false);

        btnNxtRound = new JButton("NEXT");
        btnNxtRound.setFont(font3);
        btnNxtRound.setBackground(Color.BLACK);
        btnNxtRound.setForeground(Color.YELLOW);
        btnNxtRound.setBorderPainted(false);
        btnNxtRound.setFocusPainted(false);
        btnNxtRound.setVisible(false);

        btnAgain = new JButton(" AGAIN");
        btnAgain.setFont(font3);
        btnAgain.setBackground(Color.BLACK);
        btnAgain.setForeground(Color.YELLOW);
        btnAgain.setBorderPainted(false);
        btnAgain.setFocusPainted(false);
        
        btnPanel2.add(btnHit); btnPanel2.add(btnStand); btnPanel2.add(btnNxtRound);
        btnPanel2.setVisible(false);
        btnPanel2.setBackground(Color.BLACK);
    }

    //create bet button and panel
    public void betButton(){
        btnBetPanel = new JPanel();
        btnBetPanel.setOpaque(true);
        btnBetPanel.setBounds(150, 200, 800, 250);
        btnBetPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        btnBetPanel2 = new JPanel();
        btnBetPanel2.setLayout(new GridLayout(1, 2, 40, 0));

        betLabel = new JLabel("CHOOSE YOUR LIVE BET");
        betLabel.setFont(font2);
        betLabel.setPreferredSize(new Dimension(750, 125));

        btnBetOne = new JButton("ONE");
        btnBetOne.setFont(font3);
        btnBetOne.setBackground(Color.BLACK);
        btnBetOne.setForeground(Color.YELLOW);
        btnBetOne.setBorderPainted(false);
        btnBetOne.setFocusPainted(false);

        btnBetTwo = new JButton("TWO");
        btnBetTwo.setFont(font3);
        btnBetTwo.setBackground(Color.BLACK);
        btnBetTwo.setForeground(Color.YELLOW);
        btnBetTwo.setBorderPainted(false);
        btnBetTwo.setFocusPainted(false);

        btnBetPanel2.add(btnBetOne); btnBetPanel2.add(btnBetTwo);
        btnBetPanel.add(betLabel); btnBetPanel.add(btnBetPanel2);
        btnBetPanel.setVisible(false);
        layered.add(btnBetPanel, Integer.valueOf(12));
    }

    //create player table panel
    public void playerTable(){
        playerPanel = new JPanel(new GridLayout(1, 6, 10, 20));
        playerPanel.setOpaque(false);
        playerPanel.setBounds(50, 350, 800, 250);
        playerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        layered.add(playerPanel, Integer.valueOf(5));
        playerPanel.setVisible(false);

        for(int i = 1; i < 6; i++){
            playerCardLabel[i] = new JLabel();
			playerCardLabel[i].setVisible(false);
			playerPanel.add(playerCardLabel[i]);
        }
    }

    //create dealer table panel
    public void dealerTable(){
        dealerPanel = new JPanel(new GridLayout(1, 6, 10, 20));
        dealerPanel.setOpaque(false);
        dealerPanel.setBounds(50, 80, 800, 250);
        dealerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); 
        layered.add(dealerPanel, Integer.valueOf(6));
        dealerPanel.setVisible(false);

        for(int i = 1; i < 6; i++) {
			dealerCardLabel[i] = new JLabel();
			dealerCardLabel[i].setVisible(false);
			dealerPanel.add(dealerCardLabel[i]);
		}	
    }

    //create score and live label
    public void textUI(){
        try{
            imgIconOriginal = ImageIO.read(new File("icon/heartFill.png"));
            int width = 100;
            int height = 100;

            imgIconResized = new BufferedImage(width, height, imgIconOriginal.getType());
            Graphics2D g = imgIconResized.createGraphics();

            g.drawImage(imgIconOriginal, 0, 0, width, height, null);
            g.dispose();

            File output = new File("icon/resized-image.png");
            ImageIO.write(imgIconResized, "png", output);

        }catch(IOException e){
            System.out.println("Error: heart icon not found!");
            System.exit(0);
        }

        dealerScore = new JLabel("DEALER: ");
        dealerScore.setFont(font3);
        dealerScore.setForeground(Color.WHITE);
        dealerScore.setBounds(50, 0, 500, 100);
        layered.add(dealerScore, Integer.valueOf(7));
        dealerScore.setVisible(false);

        dealerLive = new JLabel("Live: ");
        dealerLive.setIcon(new ImageIcon(imgIconResized));
        dealerLive.setFont(font3);
        dealerLive.setForeground(Color.WHITE);
        dealerLive.setBounds(585, 0, 500, 100);
        layered.add(dealerLive, Integer.valueOf(8));
        dealerLive.setVisible(false);

        playerScore = new JLabel("YOU: ");
        playerScore.setFont(font3);
        playerScore.setForeground(Color.WHITE);
        playerScore.setBounds(50, 580, 500, 100);
        layered.add(playerScore, Integer.valueOf(9));
        playerScore.setVisible(false);

        playerLive = new JLabel("Live: ");
        playerLive.setIcon(new ImageIcon(imgIconResized));
        playerLive.setFont(font3);
        playerLive.setForeground(Color.YELLOW);
        playerLive.setBounds(585, 580, 500, 100);
        layered.add(playerLive, Integer.valueOf(10));
        playerLive.setVisible(false);

        winLoseLabel = new JLabel("");
        winLoseLabel.setFont(font1);
        winLoseLabel.setForeground(Color.YELLOW);
        layered.add(winLoseLabel, Integer.valueOf(11));
        winLoseLabel.setVisible(false);
    }
}
