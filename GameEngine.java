package com.example.gavri.node_hunter;

import android.content.Context;
import android.os.Handler;
import android.widget.GridView;
import android.widget.Toast;

import com.example.gavri.node_hunter.Enums.Direction;
import com.example.gavri.node_hunter.Exceptions.BadNumberPortalsException;
import com.example.gavri.node_hunter.Nodes.ControlNode;
import com.example.gavri.node_hunter.Nodes.Lock;
import com.example.gavri.node_hunter.Nodes.Node;
import com.example.gavri.node_hunter.Nodes.Portal;
import com.example.gavri.node_hunter.Threads.SoundThread;

/**
 * Created by gavri on 6/27/2017.
 * Main control zone for the game
 * Stores a map of the current level and communicates between all the nodes
 * Set off winning/losing the level/game scenarios
 */

public class GameEngine {
    public static int WIDTH;
    public static int HEIGHT;
    public static boolean moving;
    private static boolean playing = true;
    public static final int startSpeed = 145;

    private static boolean teleporting;
    private static boolean redirecting;
    private static int currRow;
    private static int currCol;
    private static int lvlNum = 0; //set to one below currently editing OR set to 0 (beginning)
    private GridView gameGrid;
    private Context context;
    Node[][] map;

    //sound effects:
    private SoundThread teleportSoundThread;
    private SoundThread winSoundThread;
    private SoundThread serverSoundThread;
    private SoundThread deathSoundThread;

    private static final GameEngine ourInstance = new GameEngine();

    public static GameEngine getInstance() {
        return ourInstance;
    }

    private GameEngine() {
    }

    //create the grid for the level
    public void createGrid(Context gameActivity, GridView gridView) {
        context = gameActivity;

        //setup the sound effects
        teleportSoundThread = new SoundThread(gameActivity, R.raw.laser_single);
        winSoundThread = new SoundThread(gameActivity, R.raw.lvl_complete_cut);
        serverSoundThread = new SoundThread(gameActivity, R.raw.computer_bleeps0);
        serverSoundThread.initializeBleeps(gameActivity);
        deathSoundThread = new SoundThread(gameActivity, R.raw.power_down_cut);

        //initialize the board
        nextLevel();
        printGrid();
        gameGrid = gridView;
        gridView.setAdapter(new GameAdaptor(context));
    }

    public Node getNode(int row, int col) {
        return map[row][col];
    }

    public Node getCurr() {
        return map[currRow][currCol];
    }

    //return the next 2D array representing the next level
    private void nextLevel() {
        map = LevelGenerator.getInstance().getLevelMap(++lvlNum, context);
        //starting pos for this level:
        currRow = LevelGenerator.getInstance().startingPost(lvlNum)[0];
        currCol = LevelGenerator.getInstance().startingPost(lvlNum)[1];
    }

    //same as nextLevel except don't advance
    public void restartLevel() {
        lvlNum--;
        Lock.reset();
        NumbersDrawable.reset();
//        NumbersDrawable.setLocations();
        nextLevel();
        map[currRow][currCol].setActive(); //activate the first curr node
        gameGrid.invalidateViews();
        moving = false;
        playing = true;
    }

    public void resetViews() {
        System.out.println("invalidating");
        gameGrid.invalidateViews();
    }

    /*public void swapNodes(int otherRow, int otherCol) {
        //swapping the nodes
        System.out.println("Curr: " + currRow + "," + currCol + " to swap with other: " + otherRow + "," + otherCol);
        Node temp = map[otherRow][otherCol];
        map[otherRow][otherCol] = map[currRow][currCol];
        map[currRow][currCol] = temp;

        //update the node positions
        map[otherRow][otherCol].swapPos(map[currRow][currCol]);

        //resetting the view
        gameGrid.invalidateViews();

        //updating the current position
        currRow = otherRow;
        currCol = otherCol;
        System.out.println("The currect position is " + currRow + ", " + currCol);
    }*/

    /*sends the current node back to Node for activation and updates the currRow and currCol
    accordingly*/
    public boolean activate(int otherRow, int otherCol) {

        teleporting = false; //reset
        if (map[otherRow][otherCol].activate()) {
            currRow = otherRow;
            currCol = otherCol;
            return true;
        } else { //we might still be able to activate the "next" node, if we are teleporting
            map[otherRow][otherCol].setImage(); //reflect color changes immediately
            gameGrid.invalidateViews();
            return teleporting; //we either must stop or we're teleporting
        }
    }

    //teleport to the other gate
    public void teleport(Portal gate) throws BadNumberPortalsException {
        teleporting = true;
        for (int i = 0; i < map.length; i++) { //find the other gate
            for (int j = 0; j < map[0].length; j++) {
                if (gate == map[i][j]) {
                    currRow = i;
                    currCol = j;
                    System.out.println("teleporting to " + i + "," + j);

                    //teleport sound effect
                    new SoundThread(teleportSoundThread).start();
                    return;
                }
            }
        }

        throw new BadNumberPortalsException();

    }

    //redirect
    public void redirect(Direction direction, ControlNode startPoint) {
        redirecting = true;
        for (int i = 0; i < map.length; i++) { //find the control node
            for (int j = 0; j < map[0].length; j++) {
                if (startPoint == map[i][j]) {
                    currRow = i;
                    currCol = j;
                    System.out.println("redirecting from " + i + "," + j + " in direction " + direction.toString());

                    //redirect sound effect
                    new SoundThread(teleportSoundThread).start();

                    //start new path in the given direction
                    activate(direction, startSpeed);
                }
            }
        }

    }

    //recursively shifts activation in a given direction if possible
    public void activate(final Direction direction, final int delayTime) {

        boolean continuable = false;

        //if (!animateOnly) { for 4 part animation
        playing = currRow > 0 && currRow < HEIGHT - 1 && currCol < WIDTH - 1 && currCol > 0;

        if (playing) {
            //animation on the edge so activate the next node
            switch (direction) {

                case UP:
                    continuable = activate(currRow - 1, currCol);
                    break;
                case DOWN:
                    continuable = activate(currRow + 1, currCol);
                    break;
                case RIGHT:
                    continuable = activate(currRow, currCol + 1);
                    break;
                case LEFT:
                    continuable = activate(currRow, currCol - 1);
                    break;
            }
        } else { //went off the edge
            loseGame();
        }

        /*} else { for 4 part animation
            //update the animation
            continuable = true;
        }*/

        if (continuable) {
            final int delay = teleporting ? 500 : (delayTime > 69) ? delayTime - 15 : delayTime;
            System.out.println("Delay: " + delay);
            //increase speed by decreasing delay time with a min delay of 70 ms

//            NumbersDrawable.update(animateOnly); for 4 part animation

            //delay so we can see the change
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after delay
                    if (teleporting) activate(direction, delayTime);
                    else activate(direction, delay);
                    //continue until we reach the end
                }
            }, delay);
        } else {
            if (!redirecting) {
                if (playing) { //we hit a server or a lock; play a random bleep
                    new SoundThread(serverSoundThread.randomBleep()).start();
                    moving = false;
                } //otherwise we died; play the death sound in losegame()
                System.out.println("Resting on " + currRow + "," + currCol);
                printGrid();
            } else { //we didn't stop yet, just switching direction
                redirecting = false; //reset
            }
        }
    }

    public void printGrid() {
        System.out.println("Printing Grid: ");
        for (Node[] nodeRow : map) {
            for (Node node : nodeRow) {
                System.out.print(node.toString() + " ");
            }
            System.out.println();
        }
    }

    public void winLevel(boolean skipping) {
        playing = false;
        moving = true; //don't move

        if (!skipping) {
            String beaten = (lvlNum == LevelGenerator.TOTLVLS) ? "the Game" : "Level " + lvlNum;
            Toast toast = Toast.makeText(context, "You Beat " + beaten, Toast.LENGTH_SHORT);
            toast.show();

            //winning sound effect
            new SoundThread(winSoundThread).start();
        } else {
            int showLevel = (lvlNum < LevelGenerator.TOTLVLS) ? lvlNum + 1 : 1;
            Toast toast = Toast.makeText(context, "Skipping to Level " + showLevel, Toast.LENGTH_SHORT);
            toast.show();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after delay
                //if this is the last level then trigger endgame sequence
                if (lvlNum == LevelGenerator.TOTLVLS) {
                    lvlNum = 1;
                } else {
                    nextLevel();
                }
                restartLevel();
                gameGrid.invalidateViews();
            }
        }, 500);
    }

    public void loseGame() {
        System.out.println("You Lost");
        playing = false;
        moving = true;
        new SoundThread(deathSoundThread).start();
        Toast toast = Toast.makeText(context, "Game Over. Try Again!", Toast.LENGTH_SHORT);
        toast.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after delay
                restartLevel();
            }
        }, 500);
    }

    public void goBackLevel() {
        lvlNum = (lvlNum < 2) ? LevelGenerator.TOTLVLS : lvlNum - 1;
        Toast toast = Toast.makeText(context, "Going Back to Level " + lvlNum, Toast.LENGTH_SHORT);
        toast.show();
    }
}
