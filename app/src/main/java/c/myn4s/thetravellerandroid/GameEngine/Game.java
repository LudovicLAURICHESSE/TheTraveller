package c.myn4s.thetravellerandroid.GameEngine;

import android.graphics.Canvas;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import c.myn4s.thetravellerandroid.GameEngine.blocks.Block;
import c.myn4s.thetravellerandroid.GameEngine.blocks.BlockFactory;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.R;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Floor;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Foe;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Player;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Type;


/**
 * Created by Myn4s on 03/04/2018.
 */

public class Game {
    private BlockFactory blockFactory;
    private Block prevBlock;
    private Block currBlock;
    private Block nextBlock;

    private Player player;

    private boolean jumped = true;

    public Game (BlockFactory blockFactory){
        this.blockFactory = blockFactory;

        prevBlock = this.blockFactory.creationBlock(0);
        currBlock = this.blockFactory.creationBlock(1);
        nextBlock = this.blockFactory.creationBlock(2);

        player = new Player(R.mipmap.player);
    }


    public void update(){
        prevBlock.update();
        currBlock.update();
        nextBlock.update();

        if (prevBlock.getFinX() <= 0){
            prevBlock = currBlock;
            currBlock = nextBlock;
            nextBlock = blockFactory.creationBlock(2);
        }
    }

    public void playerJump() {
        if (!jumped) {
            player.setPosY(player.getPosY() - 1);
            player.setForce(new Vector(0, -100));
            jumped = true;
        }
    }

    public void doDraw(Canvas canvas) {
        player.draw(canvas);
        prevBlock.draw(canvas);
        currBlock.draw(canvas);
        nextBlock.draw(canvas);


    }

    public void resize() {
        prevBlock.resize();
        currBlock.resize();
        nextBlock.resize();
    }
}
