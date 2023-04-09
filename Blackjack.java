import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Written by Nititorn Kiprasopchok
 * ID: 6530300295
 */

public class Blackjack {
    static UI ui = new UI();
    static Card cards = new Card();
    static ArrayList<Integer> numRand = new ArrayList<>();
    static int playerCardHas = 0, dealerCardHas = 0;
    static int playerValue = 0, dealerValue = 0;
    static int playerLiveValue = 5, dealerLiveValue = 5;
    static int pickedCardNumber, dealerFirstCard, playerFirstCard, liveBet;
    public static void main(String[] args) {
        //create frame for this app
        JFrame app = new JFrame("21 Resident Evil Game");

        //add panel from ui class
        app.add(ui.c);
        app.add(ui.btnPanel, BorderLayout.SOUTH);
        app.add(ui.btnPanel2, BorderLayout.EAST);

        gameStart();

        //set frame option
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setSize(1280, 720);
        app.setLocationRelativeTo(null);
        app.setResizable(false);

        //add hit button attribute
        ui.btnHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                playerCardHas++;
                shuffleCard();

                ui.playerCardLabel[playerCardHas].setVisible(true);
                ui.playerCardLabel[playerCardHas].setIcon(new ImageIcon(cards.cards[numRand.get(0)]));
                playerValue += numRand.get(0);
                playerFirstCard = numRand.get(0);
                //System.out.println("remove " + numRand.get(1)); //print removed card from player
                numRand.remove(0);
                ui.playerScore.setText("YOU: " + playerValue);
                ui.btnStand.setVisible(true);
                valueCheckMore();

                if(playerValue < 21){
                    dealerTurn();
                }
            }
        });

        //add stand button attribute
        ui.btnStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.btnHit.setVisible(false);
                ui.btnStand.setVisible(false);
                dealerTurn();
                if((dealerValue + dealerFirstCard) < 21){
                    valueCheck();
                }
            }
        });

        //add Next Round button attribute
        ui.btnNxtRound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                nextRound();
            }
        });

        ui.btnAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.btnPanel2.remove(ui.btnAgain);
                gameReset();
            }
        });

        //add bet one live button attribute
        ui.btnBetOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                liveBet = 1;
                ui.btnBetPanel.setVisible(false);
                ui.btnHit.setVisible(true);
                ui.btnStand.setVisible(true);
            }
        });

        //add bet two live button attribute
        ui.btnBetTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                liveBet = 2;
                ui.btnBetPanel.setVisible(false);
                ui.btnHit.setVisible(true);
                ui.btnStand.setVisible(true);
            }
        });
    }

    //method for start the game
    public static void gameStart(){
        ui.dealerLive.setText("Live: " + dealerLiveValue);
        ui.playerLive.setText("Live: " + playerLiveValue);

        for(int i = 1; i < 12; i++){
            numRand.add(i);
        }

        
        // //print all added card
        // for(int i = 0; i < numRand.size(); i++){
        //     System.out.print(numRand.get(i) + " ");
        // }
        // System.out.println("");
        
        playerDraw();
        dealerDraw();
        ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.backResized));

    }

    //method for draw card by player
    public static void playerDraw() { 
        shuffleCard();
        playerCardHas++;

        ui.playerCardLabel[playerCardHas].setVisible(true);
        ui.playerCardLabel[playerCardHas].setIcon(new ImageIcon(cards.cards[numRand.get(0)]));
        //System.out.println("remove " + numRand.get(0)); //print removed card from player
        playerValue += numRand.get(0);
        numRand.remove(0);
        ui.playerScore.setText("YOU: " + playerValue);
    }

    //method for draw card by dealer
    public static void dealerDraw() {
        shuffleCard();
        dealerCardHas++;

        ui.dealerCardLabel[dealerCardHas].setVisible(true);
        ui.dealerCardLabel[dealerCardHas].setIcon(new ImageIcon(cards.cards[numRand.get(0)]));
        //System.out.println("remove " + numRand.get(0)); //print removed card from dealer
        if(dealerCardHas != 1){
            dealerValue += numRand.get(0);
        }

        if(dealerCardHas == 1){
            dealerFirstCard = numRand.get(0);
            ui.dealerScore.setText("DEALER: ?");
        }else{
            ui.dealerScore.setText("DEALER: ? + " + dealerValue);
        }
        numRand.remove(0);
        valueCheckMore();
    }

    //method dealer turn
    public static void dealerTurn(){
        if((dealerValue + dealerFirstCard) < 17){
            dealerDraw();
        }else if((dealerValue + dealerFirstCard) >= 17){
            ui.btnHit.setVisible(false);
        }
    }

    //method for check score
    public static void valueCheck(){
        if((dealerValue + dealerFirstCard) >= playerValue && (dealerValue + dealerFirstCard) <= 21){
            playerLiveValue -= liveBet;
            ui.playerLive.setText("Live: " + playerLiveValue);
            ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.cards[dealerFirstCard]));
            ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));

            printDealerWinLabel();
            gameResetButtonSet();
        }else if(playerValue > (dealerValue + dealerFirstCard) && playerValue <= 21){
            dealerLiveValue -= liveBet;
            ui.dealerLive.setText("Live: " + dealerLiveValue);
            ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.cards[dealerFirstCard]));
            ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));

            printPlayerWinLabel();
            gameResetButtonSet();
        }
    }

    //method for check score if more than 21 or equ 11
    public static void valueCheckMore(){
        if(playerValue > 21){
            ui.btnHit.setVisible(false);
            ui.btnStand.setVisible(false);
            playerLiveValue -= liveBet;
            ui.playerLive.setText("Live: " + playerLiveValue);
            ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.cards[dealerFirstCard]));
            ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));

            printDealerWinLabel();
            gameResetButtonSet();
        }else if(playerValue == 21){
            ui.btnHit.setVisible(false);
            ui.btnStand.setVisible(false);
            dealerLiveValue -= liveBet;
            ui.dealerLive.setText("Live: " + dealerLiveValue);
            ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.cards[dealerFirstCard]));
            ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));

            printPlayerWinLabel();
            gameResetButtonSet();
        }else if((dealerValue + dealerFirstCard) > 21){
            dealerLiveValue -= liveBet;
            ui.dealerLive.setText("Live: " + dealerLiveValue);
            ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.cards[dealerFirstCard]));
            ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));

            printPlayerWinLabel();
            gameResetButtonSet();
        }else if((dealerValue + dealerFirstCard) == 21){
            playerLiveValue -= liveBet;
            ui.playerLive.setText("Live: " + playerLiveValue);
            ui.dealerCardLabel[1].setIcon(new ImageIcon(cards.cards[dealerFirstCard]));
            ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));

            printDealerWinLabel();
            gameResetButtonSet();
        }
    }

    //method for print player win text
    public static void printPlayerWinLabel(){
        ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));
        ui.winLoseLabel.setText("YOU WIN!");
        ui.winLoseLabel.setBounds(300, 80, 1000, 500);
        ui.winLoseLabel.setVisible(true);

        ui.btnHit.setVisible(false);
        ui.btnStand.setVisible(false);
        ui.btnNxtRound.setVisible(true);
    }

    //method for print dealer win text
    public static void printDealerWinLabel(){
        ui.dealerScore.setText("DEALER: " + (dealerValue + dealerFirstCard));
        ui.winLoseLabel.setText("DEALER WIN!");
        ui.winLoseLabel.setBounds(200, 80, 1000, 500);
        ui.winLoseLabel.setVisible(true);

        ui.btnHit.setVisible(false);
        ui.btnStand.setVisible(false);
        ui.btnNxtRound.setVisible(true);
    }

    //method for do next round
    public static void nextRound(){
        
        /* 
        //print card
        System.out.print("You have: ");
        for(int i = 0; i < numRand.size(); i++){
            System.out.print(numRand.get(i) + " ");
        }
        System.out.println("");
        */

        if(playerLiveValue > 0 && dealerLiveValue > 0){
            ui.btnBetPanel.setVisible(true);
        }
        if(playerLiveValue == 1 || dealerLiveValue == 1){
            ui.btnBetPanel2.remove(ui.btnBetTwo);
        }

        ui.btnNxtRound.setVisible(false);
        ui.winLoseLabel.setVisible(false);
        ui.playerScore.setText("YOU: " + playerValue);
        ui.dealerScore.setText("DEALER: ?");

        for(int i = 1; i < 6; i++){
            ui.playerCardLabel[i].setVisible(false);
            ui.dealerCardLabel[i].setVisible(false);
        }

        playerValue = 0; dealerValue = 0;
        playerCardHas = 0; dealerCardHas = 0;

        for(int i = 0; i < numRand.size(); i = 0){
            //System.out.println("remove " + numRand.get(i)); //print removed number
            numRand.remove(i);
        }

        
        // //print numRand list after remove number
        // System.out.print("You have: " + numRand.size() + " card in index");
        // for(int i = 0; i < numRand.size(); i++){
        //     System.out.print(numRand.get(i) + " ");
        // }
        // System.out.println("");
        

        if(playerLiveValue > 0 && dealerLiveValue > 0){
            gameStart();
        }

        if(playerLiveValue == 0){
            ui.winLoseLabel.setText("Sorry, You LOSE!");
            ui.winLoseLabel.setBounds(100, 80, 1000, 500);
            ui.winLoseLabel.setVisible(true);
            ui.btnHit.setVisible(false);
            ui.btnStand.setVisible(false);
        }

        if(dealerLiveValue == 0){
            ui.winLoseLabel.setText("Congrats, You WIN!!");
            ui.winLoseLabel.setBounds(50, 80, 1000, 500);
            ui.winLoseLabel.setVisible(true);
            ui.btnHit.setVisible(false);
            ui.btnStand.setVisible(false);
        }
    }

    //method for set button for reset the game
    public static void gameResetButtonSet(){
        if(playerLiveValue == 0 || dealerLiveValue == 0){
            ui.btnPanel2.remove(ui.btnHit);
            ui.btnPanel2.remove(ui.btnStand);
            ui.btnPanel2.remove(ui.btnNxtRound);
            ui.btnPanel2.add(ui.btnAgain);
            ui.btnBetPanel2.add(ui.btnBetTwo);
        }
    }

    //method for reset the game
    public static void gameReset(){
        playerLiveValue = 5; dealerLiveValue = 5;
        ui.winLoseLabel.setVisible(false);
        ui.btnPanel2.remove(ui.btnAgain);
        ui.btnPanel2.add(ui.btnHit);
        ui.btnPanel2.add(ui.btnStand);
        ui.btnPanel2.add(ui.btnNxtRound);
        nextRound();
    }

    //method for shuffle the card
    public static void shuffleCard() {
        Collections.shuffle(numRand);
	}
}