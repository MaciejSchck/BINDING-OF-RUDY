package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

        hitBox = new Rectangle(32, 32, 76,96);

        setDefaultValues();
    }

    public void setDefaultValues() {

        worldX = 1100;
        worldY = 44800;
        speed = 7;
        direction = "stationary";
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            stationary1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rud_front1.png"));
            stationary2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rud_front2.png"));
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmoveup1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmoveup2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmovedown1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmovedown2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmoveleft1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmoveleft2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmoveright1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/rudmoveright2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        direction = "stationary";
        if (keyH.upPressed == true) {
            direction = "up";
        } if (keyH.downPressed == true) {
            direction = "down";
        } if (keyH.leftPressed == true) {
            direction = "left";
        } if (keyH.rightPressed == true) {
            direction = "right";
        }

        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cDetector.checkTile(this);

        //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn==false){
            switch(direction){
                case "up":
                    worldY = worldY - speed;
                    break;
                case "down":
                    worldY = worldY + speed;
                    break;
                case "left":
                    worldX = worldX - speed;
                    break;
                case "right":
                    worldX = worldX + speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 15){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "stationary":
                if(spriteNumber == 1) {
                    image = stationary1;
                }
                if(spriteNumber == 2) {
                    image = stationary2;
                }
                break;
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
