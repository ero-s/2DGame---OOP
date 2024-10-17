package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;
import pkg2dgame.KeyHandler;
import pkg2dgame.UtilityTool;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    
    public final int screenX, screenY;
    public int hasKey = 0;

    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth /2 - (gp.tileSize/2);
        screenY = gp.screenHeight /2 - (gp.tileSize/2);

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidArea.x = 36;
        solidArea.y = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 24;
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 6;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = setup("/pics/player/Andres/Walk back-1.png");
        up2 = setup("/pics/player/Andres/Walk back-2.png");
        left1 = setup("/pics/player/Andres/Walk left-1.png");
        left2 = setup("/pics/player/Andres/Walk left-2.png");
        right1 = setup("/pics/player/Andres/Walk right-1.png");
        right2 = setup("/pics/player/Andres/Walk right-2.png");
        down1 = setup("/pics/player/Andres/Walk front-1.png");
        down2 = setup("/pics/player/Andres/Walk front-2.png");
    }
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        
        BufferedImage image = null;
        
        try{
            image =  ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    /**
     *
     */
    public void update() {
        
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if (keyH.upPressed) {
                direction = "up";
                
            }
            else if (keyH.leftPressed) {
                direction = "left";
                
            }
            else if (keyH.downPressed) {
                direction = "down"; // Fix: should set direction to "down"
                
            }
            else if (keyH.rightPressed) {
                direction = "right";
                
            }
            
            //checks tile collsion
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //check obj collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            //if collision isfalse, player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":{
                        worldY -= speed;
                        break;
                    }
                    case "down":{
                        worldY += speed;
                        break;
                    }
                    case "left":{
                        worldX -= speed;
                        break;
                    }
                    case "right":{
                        worldX += speed;
                        break;
                    }
                    
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch(objectName){
                case "Key":{
                    hasKey++;
//                  gp.playSE(index);   plays SoundEffect for getting key
                    gp.obj[i]=null;
                    System.out.println("has key: "+hasKey);
                    break;
                    
                }
                case "Door":{
                    
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        System.out.println("has key: "+hasKey);
                    }
                    break;
                    
                    
                }

                case "Wheat":{
                    //speed buff
                    speed+=2;
                    gp.obj[i] = null;
                    break;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
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
                    image =down1;
                
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

        g2.drawImage(image, screenX, screenY, null);
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        
    }
}


