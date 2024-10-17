/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dgame;

/**
 *
 * @author austi
 */
import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 32;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 8;
    public final int maxScreenRow = 4;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize  * maxScreenRow;
    
    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //FPS
    int FPS = 60;
    
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    
    //Entity and object
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    Thread gameThread;
    
    
    public SuperObject obj[] = new SuperObject[10];
   
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        GamePanel gp;
        aSetter.setObject();
        
        playMusic(0);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime()+drawInterval;
        while(gameThread != null){
            update();

            repaint();
            
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public void update(){
        
        player.update();
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;
        
        //tile
        tileM.draw((Graphics2D) g2);
        
        //object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw((Graphics2D) g2, this);
            }
        }
        
        //player
        player.draw((Graphics2D) g2);
        g2.dispose();
        
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
               
    }
}
