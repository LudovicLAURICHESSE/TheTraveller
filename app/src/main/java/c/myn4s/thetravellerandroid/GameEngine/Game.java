package c.myn4s.thetravellerandroid.GameEngine;

import android.graphics.Canvas;

import c.myn4s.thetravellerandroid.GameEngine.Grid.GameObjectGenerator;
import c.myn4s.thetravellerandroid.GameEngine.Grid.Grid;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Player;

import android.graphics.Canvas;
import android.util.Log;

import java.util.List;
/**
 * Created by Myn4s on 03/04/2018.
 */

public class Game {

    private List<GameObject> gameObjects;
    private Player player;
    private GameObjectGenerator creator;

    private GameObject plateformeGauche = null;
    private GameObject plateformeDroite = null;

    public Game (){
        creator = new GameObjectGenerator();
        gameObjects = creator.initialize();
        player = new Player(Grid.getBlocksSize(), Grid.getBlocksSize());
    }


    public void update(){
        GameObject nObj = creator.generate();
        if (nObj != null) gameObjects.add(nObj);

        for (GameObject obj: gameObjects) {
            if (player.collisionFromRight(obj)) {}
            //game over

            if (!player.isGrounded() && player.collisionFromBottom(obj)){
                player.setGrounded(true);
                player.setPosX(obj.getPosX() + Grid.getBlocksSize());
            }
        }

        if (gameObjects.get(0).getEndX() < Grid.getDestructionPoint())
            gameObjects.remove(0);

        for (GameObject obj: gameObjects) {
            obj.update();
        }

        for (int i = 0 ; i < 3 && i < gameObjects.size() ; i++){
            if (gameObjects.get(i).getPosX() <= player.getPosX() && gameObjects.get(i).getEndX() >= player.getPosX())
                plateformeGauche = gameObjects.get(i);

            if (gameObjects.get(i).getEndX() <= player.getPosX() && gameObjects.get(i).getEndX() >= player.getEndX())
                plateformeDroite = gameObjects.get(i);
        }

        player.setMaxDescente(this.getMaxDescente());
        player.update();
    }


    public void doDraw(Canvas canvas) {
        for (GameObject obj: gameObjects) {
            obj.doDraw(canvas);
        }

        player.doDraw(canvas);
    }

    public void resize() {
        for (GameObject obj: gameObjects) {
            obj.resize();
        }

        player.resize();
    }

    public void playerJump(){
        if (player.isGrounded())
            player.jump();
    }

    private int getMaxDescente(){
        if (plateformeGauche != null){
            if (plateformeDroite != null){
                if (plateformeGauche.getPosY() > plateformeGauche.getPosY())
                    return plateformeDroite.getPosY();
                else
                    return plateformeGauche.getPosY();
            }
            else
                return plateformeGauche.getPosY();
        }
        else
            return 0;
    }
}
