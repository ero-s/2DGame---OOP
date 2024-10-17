package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import pkg2dgame.GamePanel;
import pkg2dgame.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        //
        loadMap("/pics/maps/world01.txt");
        
 
    }

    public void getTileImage() {
        setup(0, "/pics/tiles/demo/grass.png", false);
        setup(1, "/pics/tiles/acacia parts/acacia left.png", true);
        setup(2, "/pics/tiles/acacia parts/acacia middleleft.png", true);
        setup(3, "/pics/tiles/acacia parts/acacia middleright.png", true);
        setup(4, "/pics/tiles/acacia parts/acacia right.png", true);
        
        setup(5, "/pics/tiles/houses/house.png", true);
        setup(6, "/pics/tiles/houses/house2.png", true);
        
        setup(7, "/pics/tiles/mango parts/mango left.png", true);
        setup(8, "/pics/tiles/mango parts/mango right.png", true);
        
        setup(9, "/pics/tiles/acacia parts/forest acacia topleft.png", true);
        setup(10, "/pics/tiles/acacia parts/forest acacia top-centereft.png", true);
        setup(11, "/pics/tiles/acacia parts/forest acacia top-centerright.png", true);
        setup(12, "/pics/tiles/acacia parts/forest acacia topright.png", true);
        
        setup(13, "/pics/tiles/acacia parts/forest acacia first middleleft.png", true);
        setup(14, "/pics/tiles/acacia parts/forest acacia first middle-centerleft.png", true);
        setup(15, "/pics/tiles/acacia parts/forest acacia first middle-centerright.png", true);
        setup(16, "/pics/tiles/acacia parts/forest acacia first middleright.png", true);
        
        setup(17, "/pics/tiles/acacia parts/forest acacia second middleleft.png", true);
        setup(18, "/pics/tiles/acacia parts/forest acacia second middle-centerleft.png", true);
        setup(19, "/pics/tiles/acacia parts/forest acacia second middle-centerright.png", true);
        setup(20, "/pics/tiles/acacia parts/forest acacia second middleright.png", true);
        
        setup(21, "/pics/tiles/acacia parts/forest acacia third middleleft.png", true);
        setup(22, "/pics/tiles/acacia parts/forest acacia third middle-centerleft.png", true);
        setup(23, "/pics/tiles/acacia parts/forest acacia third middle-centerright.png", true);
        setup(24, "/pics/tiles/acacia parts/forest acacia third middleright.png", true);
        
        setup(25, "/pics/tiles/acacia parts/forest acacia bottomleft.png", true);
        setup(26, "/pics/tiles/acacia parts/forest acacia bottom-centerleft.png", true);
        setup(27, "/pics/tiles/acacia parts/forest acacia bottom-centerright.png", true);
        setup(28, "/pics/tiles/acacia parts/forest acacia bottomright.png", true);
        
        setup(29, "/pics/tiles/demo/path_topleft.png", false);
        setup(30, "/pics/tiles/demo/path_top.png", false);
        setup(31, "/pics/tiles/demo/path_topright.png", false);
        setup(32, "/pics/tiles/demo/path_left.png", false);
        setup(33, "/pics/tiles/demo/path.png", false);
        setup(34, "/pics/tiles/demo/path_right.png", false);
        setup(35, "/pics/tiles/demo/path_bottomleft.png", false);
        setup(36, "/pics/tiles/demo/path_bottom.png", false);
        setup(37, "/pics/tiles/demo/path_bottomright.png", false);
//        setup(10, "/pics/tiles/mango parts/mango right.png", true);
//        setup(11, "/pics/tiles/mango parts/mango left.png", true);
//        setup(12, "/pics/tiles/mango parts/mango right.png", true);
//        setup(13, "/pics/tiles/mango parts/mango left.png", true);
//        setup(14, "/pics/tiles/mango parts/mango right.png", true);
//        setup(15, "/pics/tiles/mango parts/mango left.png", true);
//        setup(16, "/pics/tiles/mango parts/mango right.png", true);
//        setup(17, "/pics/tiles/mango parts/mango left.png", true);
//        setup(18, "/pics/tiles/mango parts/mango right.png", true);
//        setup(19, "/pics/tiles/mango parts/mango left.png", true);
//        setup(20, "/pics/tiles/mango parts/mango right.png", true);
//        setup(21, "/pics/tiles/mango parts/mango left.png", true);
//        setup(22, "/pics/tiles/mango parts/mango right.png", true);
//        setup(23, "/pics/tiles/mango parts/mango left.png", true);
//        setup(24, "/pics/tiles/mango parts/mango right.png", true);
        
        
    }
    
    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize,gp.tileSize);
            tile[index].collision = collision;
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;  // Load base map

                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX && 
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);  // Draw tile
            }
            

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
