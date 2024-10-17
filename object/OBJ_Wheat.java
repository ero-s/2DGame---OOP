/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;

/**
 *
 * @author austi
 */
public class OBJ_Wheat extends SuperObject{
    GamePanel gp;
    public OBJ_Wheat(GamePanel gp){
        this.gp = gp;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/pics/objects/wheat.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        collision = true;
        
    }
    
    
}
