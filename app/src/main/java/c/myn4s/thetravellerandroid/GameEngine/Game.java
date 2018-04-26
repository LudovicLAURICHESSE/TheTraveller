package c.myn4s.thetravellerandroid.GameEngine;

import android.graphics.Canvas;

import c.myn4s.thetravellerandroid.GameEngine.Grid.GameObjectGenerator;
import c.myn4s.thetravellerandroid.GameEngine.Grid.Grid;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Foe;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Player;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.PlayerIsDeadException;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Projectile;

import android.graphics.Canvas;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by Myn4s on 03/04/2018.
 */

public class Game {
    private boolean doUpdate = true;

    private List<GameObject> gameObjects;
    private List<Projectile> projectiles;
    private Player player;
    private GameObjectGenerator creator;
    private Score score;

    private List<Foe> foes;

    private GameObject plateformeGauche = null;
    private GameObject plateformeDroite = null;

    private int cptTire=0;

    public Game (){
        creator = new GameObjectGenerator(); //création de la fabrique de plateformes
        gameObjects = creator.initialize(); //remplissage de la liste de platefomes
        foes = new LinkedList<>();
        player = new Player(Grid.getBlocksSize(), Grid.getBlocksSize()); //instanciation du joueur
        projectiles = creator.generateProjectile(player);
        score = new Score(); //initialisation du score
    }

    public void update() throws PlayerIsDeadException {
        if (doUpdate) { //si le jeu continue
            score.winPoint(1); //incrémentation du score

            GameObject nObj = creator.generate(); //appel de la fabrique (renvoie null si il n'y a pas besoin de générer de plateforme
            if (nObj != null) gameObjects.add(nObj);

            Foe nFoe = creator.generateFoe();
            if (nFoe != null) foes.add(nFoe);


            if (gameObjects.get(0).getEndX() < Grid.getDestructionPoint()) //suppression des plateformes sorties de l'écran
                gameObjects.remove(0);

            if (!foes.isEmpty() && foes.get(0).getEndX() < Grid.getDestructionPoint()) //suppression des plateformes sorties de l'écran
                foes.remove(0);

            for (GameObject obj : gameObjects) { //déplacement des plateformes
                obj.update();
            }

            for (int i = 0; i < 3 && i < gameObjects.size(); i++) {
                //si parmis les 3 premiers objets de la liste
                //on trouve un objet dont les bornes en Y entourent posY du joueur
                if (gameObjects.get(i).getPosX() <= player.getPosX() &&  player.getPosX() <= gameObjects.get(i).getEndX())
                    plateformeGauche = gameObjects.get(i); //cet objet est la plateforme de gauche

                //idem autour de endX pour trouver la plateforme de droite
                if (gameObjects.get(i).getPosX() <= player.getEndX() && player.getEndX() <= gameObjects.get(i).getEndX())
                    plateformeDroite = gameObjects.get(i);
            }

            //vérification si le joueur n'est pas tué par une plateforme ou tombé
            if (player.isKilledBy(plateformeDroite) || player.getPosY() > Grid.getScreenHeight()){
                doUpdate = false;
            }


            player.setMaxDescente(this.getMaxDescente()); //mise a jour de la limite de chute du joueur
            player.update(); //mise a jour du joueur

            for (Projectile pro : projectiles) { //déplacement des plateformes
                pro.update(player);
            }

            for (Foe f: foes) {
                f.update();
                if (player.getKill()) {
                    doUpdate = false;
                }
                if(f.isKilledByPlayer(player) && !(player.getEndY() > (f.getPosY()+(f.getSizeY()/2)))){
                    foes.remove(f);
                }else{
                    f.killPlayer(player);
                }
            }


        }
    }


    public void doDraw(Canvas canvas) { //dessiner le canvas avec tous ses éléments
        for (GameObject obj: gameObjects) {
            obj.doDraw(canvas);
        }

        player.doDraw(canvas);

        for (Foe obj: foes) {
            obj.doDraw(canvas);
        }

    }

    public void resize() { //mise a jour des tailles
        for (GameObject obj: gameObjects) {
            obj.resize();
        }

        player.resize();
    }

    public void playerJump(){
        if (player.isGrounded()) //le joueur ne peut sauter que si il est au sol
            player.jump();
    }

    public void playerShot(){
        if(!player.getIsShot() && !projectiles.isEmpty() && cptTire<3){
            projectiles.get(cptTire).shoot();
            player.setIsShot(false);
            cptTire++;
        }
        else if(cptTire>=3){
            cptTire=0;

        }
    }

    private int getMaxDescente(){ //obtenir le sol le plus proche du joueur
        if (plateformeGauche != null){
            if (plateformeDroite != null){
                //minimum car les valeurs en y sont inversées par rapport à la logique humaine
                //minimum entre le dessus de la plateforme située sous la droite et celle située sous la gauche du joueur
                return Math.min(plateformeGauche.getPosY() , plateformeDroite.getPosY());
            }
            else
                return plateformeGauche.getPosY();
        }
        else
            return 0; //cas jamais atteint (sauf bug)
    }

    public Score onGameOver(){return score;} //renvoyer le score
}
