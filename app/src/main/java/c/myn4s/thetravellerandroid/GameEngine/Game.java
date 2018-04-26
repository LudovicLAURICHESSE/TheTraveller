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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Myn4s on 03/04/2018.
 */

public class Game {
    private int timer = 0;
    private boolean doUpdate = true;

    private List<GameObject> gameObjects;
    private List<Projectile> projectiles;
    private Player player;
    private GameObjectGenerator creator;
    private Score score;

    private List<Foe> foes;

    private GameObject plateformeGauche = null;
    private GameObject plateformeDroite = null;

    public Game (){
        creator = new GameObjectGenerator(); //création de la fabrique de plateformes
        //création des listes
        projectiles = new LinkedList<>();
        foes = new LinkedList<>();
        gameObjects = creator.initialize(); //remplissage de la liste de platefomes

        player = new Player(Grid.getBlocksSize(), Grid.getBlocksSize()); //instanciation du joueur
        score = new Score(); //initialisation du score
    }

    public void update() throws PlayerIsDeadException {
        if (doUpdate) { //si le jeu continue

            //gagner 1pt par seconde
            timer++;
            if (timer % 30 == 0){
                timer = 0;
                score.winPoint(1);
            }

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

            if (!projectiles.isEmpty() && projectiles.get(0).getPosX() > Grid.getScreenWidth())
                projectiles.remove(0);


            for (Projectile pro : projectiles) { //déplacement des projectiles
                pro.update();
            }

            LinkedList<Foe> foesToRemove = new LinkedList<>();
            LinkedList<Projectile> projectilesToRemove = new LinkedList<>();
            for (Projectile p : projectiles){
                for (Foe f : foes){
                    if (p.killFoe(f)) {
                        projectilesToRemove.add(p);
                        foesToRemove.add(f);
                    }
                }
            }
            projectiles.removeAll(projectilesToRemove);
            foes.removeAll(foesToRemove);

            //gagner 2 pts par ennemis tués par balle
            score.winPoint(2*foesToRemove.size());

            for (Foe f: foes) {
                f.update();
                if (player.getKill()) {
                    doUpdate = false;
                }
                if(f.isKilledByPlayer(player) && !(player.getEndY() > (f.getPosY()+(f.getSizeY()/2)))){
                    foes.remove(f);
                    //gagner 5 pt par ennemi tués par saut
                    score.winPoint(5);
                }else{
                    f.killPlayer(player);
                }
            }
        }
    }


    public void doDraw(Canvas canvas) { //dessiner le canvas avec tous ses éléments
        for (GameObject obj: gameObjects) { obj.doDraw(canvas); }

        player.doDraw(canvas);

        for (Foe obj: foes) { obj.doDraw(canvas); }

//        for (Projectile p : projectiles){ p.doDraw(canvas);}
        //il y avait visiblement un probleme d'accès concurrentiels dans la ligne précédente
        //le code suivant n'a pas l'air de souffir du même problème.
        Iterator<Projectile> iterP = projectiles.iterator();
        while (iterP.hasNext()){
            Projectile p = iterP.next();
            p.doDraw(canvas);
        }

    }

    public void resize() { //mise a jour des tailles
        for (GameObject obj: gameObjects) {
            obj.resize();
        }

        for (Foe obj: foes) {
            obj.resize();
        }

        for (Projectile obj: projectiles) {
            obj.resize();
        }
        player.resize();
    }

    public void playerJump(){
        if (player.isGrounded()) //le joueur ne peut sauter que si il est au sol
            player.jump();
    }

    public void playerShot() {
        if (projectiles.isEmpty() || projectiles.size() < 3) {
            projectiles.add(creator.generateProjectile(player));
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
