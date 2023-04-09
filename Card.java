import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Written by Nititorn Kiprasopchok
 * ID: 6530300295
 */

public class Card {
    BufferedImage[] cardOriginal = new BufferedImage[12];
    BufferedImage[] cards = new BufferedImage[12];
    BufferedImage back, backResized;

    //constructor
    public Card(){
        int cardWidth = 150;
        int cardHeight = 225;

        //create back of card
        try{
            back = ImageIO.read(new File("card/BackOfCard.jpg"));

            backResized = new BufferedImage(cardWidth, cardHeight, back.getType());
            Graphics2D g = backResized.createGraphics();

            g.drawImage(back, 0, 0, cardWidth, cardHeight, null);
            g.dispose();

            File output = new File("card/resized-back.jpg");
            ImageIO.write(backResized, "jpg", output);

        }catch(IOException e){
            System.out.println("Error: back of card image not found!");
            System.exit(0);
        }

        //create all card
        try{
            for(int i = 1; i < 12; i++){
                cardOriginal[i] = ImageIO.read(new File("card/" + i + ".jpg"));

                cards[i] = new BufferedImage(cardWidth, cardHeight, cardOriginal[i].getType());
                Graphics2D g = cards[i].createGraphics();

                g.drawImage(cardOriginal[i], 0, 0, cardWidth, cardHeight, null);
                g.dispose();

                File output = new File("card/resized-card-"+ i + ".jpg");
                ImageIO.write(cards[i], "jpg", output);
            }
        }catch(IOException e){
            System.out.println("Error: card image not found!");
            System.exit(0);
        }
    }
}
