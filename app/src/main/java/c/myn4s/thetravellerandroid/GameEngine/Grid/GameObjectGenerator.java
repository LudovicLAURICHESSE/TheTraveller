package c.myn4s.thetravellerandroid.GameEngine.Grid;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import c.myn4s.thetravellerandroid.GameEngine.gameObjects.GameObject;
import c.myn4s.thetravellerandroid.GameEngine.gameObjects.PlatForm;

public class GameObjectGenerator {
    private GameObject lastGeneratedPlatform;
    private int lastUsedLevel;
    private Random rand;
    private boolean first = true;

    public GameObjectGenerator (){
        lastGeneratedPlatform = null;
        lastUsedLevel = 0;

        rand = new Random();
    }

    public GameObject generate(){
        if (lastGeneratedPlatform != null && lastGeneratedPlatform.getEndX() > Grid.getGenerationPoint() + Grid.getBlocksSize()/2 ){
            return null;
        }
        else{
            int x = Grid.getGenerationPoint();
            int level = randomLevel();

            int y = (Grid.getBlocksOnHeight() - level) * Grid.getBlocksSize();

            int size = randomSize();

            lastGeneratedPlatform = new PlatForm(x,y,size,1);
            lastUsedLevel = level;

            return lastGeneratedPlatform;
        }
    }

    public List<GameObject> initialize() {
        LinkedList<GameObject> gameObjects = new LinkedList<>();
        int cpt = 0;
        int x = 0;
        int level, y, size;

        while (gameObjects.isEmpty() || gameObjects.getLast().getEndX() < Grid.getGenerationPoint()){

            level = randomLevel();
            y = (Grid.getBlocksOnHeight() - level) * Grid.getBlocksSize();
            size = randomSize();

            gameObjects.add(new PlatForm(x,y,size,1));

            cpt += size;
            lastUsedLevel = level;
            x += size * Grid.getBlocksSize();
        }


        lastGeneratedPlatform = gameObjects.getLast();
        return gameObjects;
    }

    private int randomLevel(){
        if (first){
            first = false;
            return 1;
        }
        if (Grid.getBlocksOnHeight() > lastUsedLevel+2)
            return rand.nextInt(lastUsedLevel +1)+1;
        else
            return rand.nextInt(Grid.getBlocksOnHeight())+1;
    }

    private int randomSize(){
        return rand.nextInt(8) +3;
    }
}
