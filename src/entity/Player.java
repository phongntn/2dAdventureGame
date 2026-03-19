package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.sqrt;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

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

        try {
            left = ImageIO.read(getClass().getResourceAsStream("/player/marisa_left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/marisa_right.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        int mapSpeed = speed;

        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {

            if ((keyH.upPressed || keyH.downPressed) && (keyH.leftPressed || keyH.rightPressed)) {
                mapSpeed = (int) (mapSpeed / 1.3);
            }

            if (keyH.keyCount() == 3) {
                mapSpeed = speed;
            }

            if (keyH.upPressed) {
                worldY -= mapSpeed;
                direction = "up";
            }
            if (keyH.downPressed) {
                worldY += mapSpeed;
                direction = "down";
            }
            if (keyH.leftPressed) {
                worldX -= mapSpeed;
                direction = "left";
                spriteDirection = "left";
            }
            if (keyH.rightPressed) {
                worldX += mapSpeed;
                direction = "right";
                spriteDirection = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (!collisionOn) {

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
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteDirection.equals("left")) {
            image = left;
        }
        else if (spriteDirection.equals("right")) {
            image = right;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
