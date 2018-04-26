package c.myn4s.thetravellerandroid.GameEngine.Grid;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Foe;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.PlatForm;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Player;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.Projectile;

public class GameObjectGenerator {
    private GameObject lastGeneratedPlatform;   //référence sur la dernière plateforme générée
    private int lastUsedLevel;                  //niveau de la dernière plateforme générée
    private Random rand;                        //générateur de nombre aléatoires
    private boolean first = true;               //premier appel ou non

    private int timer = 0;

    public GameObjectGenerator (){
        lastGeneratedPlatform = null;
        lastUsedLevel = 0;

        rand = new Random();
    }

    public GameObject generate(){
        //on ne peut pas générer si la dernière plateforme des nulle
        //il est inutile de générer si la dernière plateforme n'a pas quittée la zone de génération
        if (lastGeneratedPlatform != null && lastGeneratedPlatform.getEndX() > Grid.getGenerationPoint() ){
            return null;
        }
        else{
            int x = Grid.getGenerationPoint();  //le point de génération
            int level = randomLevel();          //nouveau niveau de génération

            int y = (Grid.getBlocksOnHeight() - level) * Grid.getBlocksSize(); //traduction du niveau en valeur en pixels
            int size = randomSize(); //la longueur de la plateforme générée

            lastGeneratedPlatform = new PlatForm(x,y,size,10); //création de la nouvelle plateforme
            lastUsedLevel = level;  //mise en mémoire du niveau utilisé

            return lastGeneratedPlatform;
        }
    }

    public Foe generateFoe(){
        timer ++;
        if (timer % 100 == 0){
            int nPosX = lastGeneratedPlatform.getPosX() + ((lastGeneratedPlatform.getEndX() - lastGeneratedPlatform.getPosX())/2);
            int nPosY = lastGeneratedPlatform.getPosY() - Grid.getBlocksSize()*10; //on le pose sur la plateforme
            return new Foe(nPosX,nPosY);
        }
        else {
            return null;
        }
    }

    public List<Projectile> generateProjectile(Player p){
        List<Projectile> li = new LinkedList<>();
            for(int i =0; i<3;i++){
                li.add(new Projectile(p.getPosX(),p.getPosY()));
            }
            return li;
    }

    public List<GameObject> initialize() {
        LinkedList<GameObject> gameObjects = new LinkedList<>();
        int cpt = 0;
        int x = 0;
        int level, y, size;

        //boucle qui rempli la liste a retourner jusqu'a atteindre le point de génération
        while (gameObjects.isEmpty() || gameObjects.getLast().getEndX() < Grid.getGenerationPoint()){
            level = randomLevel();
            y = (Grid.getBlocksOnHeight() - level) * Grid.getBlocksSize();
            size = randomSize();

            gameObjects.add(new PlatForm(x,y,size,10));

            cpt += size;
            lastUsedLevel = level;
            x += size * Grid.getBlocksSize();
        }


        lastGeneratedPlatform = gameObjects.getLast(); //mise en mémoire de la dernière plateforme créée
        return gameObjects; //retour de la liste
    }

    private int randomLevel(){
        int newLevel = 10;
        if (first){ //le premier est toujours 1
            first = false;
        }
        else {
            if (Grid.getBlocksOnHeight() > lastUsedLevel + 20){
                newLevel = rand.nextInt(lastUsedLevel + 10) + 10;
                if (newLevel == lastUsedLevel) newLevel +=10;
            }

            else { //le dernier level était le plus haut possible
                newLevel = rand.nextInt(Grid.getBlocksOnHeight()) + 10;
                if (newLevel == lastUsedLevel) newLevel -=10;
            }
        }
        return newLevel;
    }

    private int randomSize(){
        return (rand.nextInt(5) +3)*10;
    } //génere une taille comprise entre 3 et 8 blocks
}
