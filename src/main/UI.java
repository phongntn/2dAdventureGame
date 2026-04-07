package main;

import object.OBJ_Chest;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import static java.awt.Font.createFont;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
//    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;


//
//    double playTime;
//    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa_Bold.ttf");
            purisaB = createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create hud object
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2){
//        if (gameFinished == true){
//            g2.setFont(arial_30);
//            g2.setColor(Color.white);
//
//            String text;
//            int textLenghth;
//            int x;
//            int y;
//
//            text = "You found the treasure!";
//            textLenghth = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLenghth/2;
//            y = gp.screenHeight/2 - (gp.tileSize*3);
//            g2.drawString(text, x, y);
//
//            text = "Your time is : " + dFormat.format(playTime) + "!";
//            textLenghth = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLenghth/2;
//            y = gp.screenHeight/2 - (gp.tileSize*4);
//            g2.drawString(text, x, y);
//
//            g2.setFont(arial_80B);
//            g2.setColor(Color.yellow);
//            text = "Congratulation!";
//            textLenghth = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLenghth/2;
//            y = gp.screenHeight/2 + (gp.tileSize*2);
//            g2.drawString(text, x, y);
//
//            gp.gameThread = null;
//        } else {
//            g2.setFont(arial_30);
//            g2.setColor(Color.white);
//            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//            //Time
//            playTime += (double) 1/60;
//            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 13, 65);
//
//            //Message
//            if(messageOn == true){
//                g2.setFont(g2.getFont().deriveFont(20F));
//                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
//
//                messageCounter++;
//
//                if(messageCounter > 120){
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//
//        }

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //Title State
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //Play state
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //Pause state
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }

        //Dialogue state
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife(){
        gp.player.life = 2;

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //Draw blank heart
        while(i < gp.player.maxLife / 2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //Reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //Draw current life
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(70,120,80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //Title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            String text = "Phong sech ngu nguc";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            //Shadow
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);

            //Main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //Blue boy image
            x = gp.screenWidth / 2 - (gp.tileSize * 2)/2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x -  gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x -  gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){

            //Class selection screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    public void drawDialogueScreen(){

        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
