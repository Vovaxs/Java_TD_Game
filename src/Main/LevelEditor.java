/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.Level;
import Entities.TileType;
import static LevelEditor.Cartographer.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Vovaxs
 */
class LevelEditor {
    
    private Level map;
    private TileType[] types;
    private String angleID;
    private int index;
    private int tempI;
    
    LevelEditor() {
        this.map = loadMap("DefaultMap");
        this.types = new TileType[6];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.GrassDirt;
        this.types[3] = TileType.GrassDirtCorn;
        this.types[4] = TileType.GrassDirtCornTiny;
        this.types[5] = TileType.Water;
        this.angleID = "0";
        this.index = 0;
        this.tempI = 0;
    }

    void update() {
        map.draw();
        
        //Mouse Input hendler
        if (Mouse.isButtonDown(0))
        {
            setTile();
        }
        //Keyboard Input hendler
        while (Keyboard.next())
        {
            if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
            {
                changeIndex(true);
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState())
            {
                changeAngle();
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState())
            {
                saveMap("DefaultMap", map);
            }
        }
    }
    
    private void setTile(){
        if((int) Math.floor(Mouse.getX() / (map.getBlockSize() * map.getSCALE())) <  map.getMapWidth() && (int) Math.floor((map.getFrameHeight() - Mouse.getY() - 1) / (map.getBlockSize() * map.getSCALE())) <  map.getMapHeight())
        {
                 map.setTile((int) Math.floor(Mouse.getX() / (map.getBlockSize() * map.getSCALE())), (int) Math.floor((map.getFrameHeight() - Mouse.getY() - 1) / (map.getBlockSize() * map.getSCALE())), types[index], angleID);  
        }
    }
    
    private void changeAngle(){
    	tempI++;
    	if(tempI > 3){
    		tempI = 0;
    	}
    	angleID = tempI + "";
    }
    
    private void changeIndex(boolean i){
        if(i){
            index++; 
        }else{
            index--;
        }
        if (index > types.length -1)
        {
            index = 0;
        }else if (index < 0){
            index = types.length -1;
        }
        System.out.println("Tile changed to: " + types[index].name());
    }
    
}
