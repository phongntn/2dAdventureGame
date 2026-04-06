package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
//    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "right";
        spriteDirection = "right";
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
    }

    public void update() {
//
//        int mapSpeed = speed;
//
//        if (keyH.upPressed || keyH.downPressed ||
//                keyH.leftPressed || keyH.rightPressed) {
//
//            if ((keyH.upPressed || keyH.downPressed) && (keyH.leftPressed || keyH.rightPressed)) {
//                mapSpeed = (int) (mapSpeed / 1.3);
//            }
//
//            if (keyH.keyCount() == 3) {
//                mapSpeed = speed;
//            }
//
//            if (keyH.upPressed) {
//                //worldY -= mapSpeed;
//                direction = "up";
//            }
//            if (keyH.downPressed) {
//                //worldY += mapSpeed;
//                direction = "down";
//            }
//            if (keyH.leftPressed) {
//                //worldX -= mapSpeed;
//                direction = "left";
//                spriteDirection = "left";
//            }
//            if (keyH.rightPressed) {
//                //worldX += mapSpeed;
//                direction = "right";
//                spriteDirection = "right";
//            }
//
//            collisionOn = false;
//            gp.cChecker.checkTile(this);
//
//            if (!collisionOn) {
//                switch (direction) {
//                    case "up":
//                        worldY -= mapSpeed;
//                        break;
//                    case "down":
//                        worldY += mapSpeed;
//                        break;
//                    case "left":
//                        worldX -= mapSpeed;
//                        break;
//                    case "right":
//                        worldX += mapSpeed;
//                        break;
//                }
//            }
//        }

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
                spriteDirection = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
                spriteDirection = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                spriteDirection = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                spriteDirection = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int objIndex =  gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);


            //If collision is false, player can move
            if(collisionOn == false){
                switch(direction){
                    case "up" :
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject (int i){
        if(i != 999){
//            String objectName = gp.obj[i].name;
//
//            switch (objectName){
//                case "Key":
//                    gp.playSE(1);
//                    hasKey++;
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("You got a key!");
//                    break;
//                case "Door":
//                    if(hasKey > 0){
//                        gp.playSE(3);
//                        gp.obj[i] = null;
//                        hasKey--;
//                        gp.ui.showMessage("You opened the door!");
//                    }else{
//                        gp.ui.showMessage("You need a key!");
//                    }
//                    System.out.println("Key:" + hasKey);
//                    break;
//                case "Boots":
//                    gp.playSE(2);
//                    speed += 1;
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("Speed up!");
//                    break;
//                case "Chest":
//                    gp.ui.gameFinished = true;
//                    gp.stopMusic();
//                    gp.playSE(4);
//                    break;
//            }



        }
    }

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,null);
    }
}
