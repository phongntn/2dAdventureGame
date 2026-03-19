package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage left, right;
    public String direction;
    public String spriteDirection;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
