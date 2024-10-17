/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

import object.OBJ_Key;
import object.OBJ_Wheat;
import object.OBJ_Door;
/**
 *
 * @author austi
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 21 * gp.tileSize;
        gp.obj[0].worldY = 21 * gp.tileSize;
//        
//        gp.obj[1] = new OBJ_Wheat(gp);
//        gp.obj[1].worldX = 42 *gp.tileSize;
//        gp.obj[1].worldY = 42 *gp.tileSize;
//        
        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = 26 * gp.tileSize;
        gp.obj[2].worldY = 21 * gp.tileSize;
    }
}
