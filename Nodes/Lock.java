package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import static android.graphics.Color.*;

import com.example.gavri.node_hunter.Exceptions.InvalidColorException;
import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.NumbersDrawable;
import com.example.gavri.node_hunter.R;

/**
 * Created by gavri on 6/27/2017.
 * These basically act like servers except there can be multiple active at the same time and
 * activating all of them will end the game
 */

public class Lock extends Node {
    private static int numLocked;
    private Integer lockView;
    private String repString;
    private boolean locked;
    private String colorHex = "#FFFFFF";

    public Lock(Node spotForNode, int color) throws InvalidColorException {
        super(spotForNode);

        numLocked++;
        locked = true;

        switch (color) {

            case RED:
                lockView = R.drawable.lock_red;
                colorHex = "#FF0000";
                repString = "LR";
                break;
            case GREEN:
                lockView = R.drawable.lock_green;
                repString = "LG";
                colorHex = "#00FF00";
                break;
            case CYAN:
                lockView = R.drawable.lock_blue;
                repString = "LB";
                colorHex = "#00FFFF";
                break;
            default:
                //the inactive color should ONLY be one of the above
                throw new InvalidColorException();
        }

        setImage();
    }

    @Override
    public String toString() {
        return repString;
    }


    //set this lock to unlocked and check if there are any remaining locks
    @Override
    public boolean activate() {

        NumbersDrawable.setColor(colorHex);
        lockView = R.drawable.unlock;
        setImage();
        System.out.println("You hit a " + this + " lock");

        if (locked && --numLocked == 0) { //initiate endgame sequence
            System.out.println("You Win! Game Over");
            NumbersDrawable.updateCurrNum(true); //update without triggering loss
            GameEngine.getInstance().winLevel(false); //won level without skipping
        } else {
            System.out.println("There are " + numLocked + " lock(s) remaining");
            NumbersDrawable.updateCurrNum(false); //update and possibly trigger loss
        }

        locked = false;
        return false;
    }

    //visual layers for the Lock
    @Override
    public void setImage() {

        Resources r = getResources();
        setImageDrawable(new LayerDrawable(new Drawable[]{r.getDrawable(lockView)}));

        /*Drawable[] layers = new Drawable[1];
        layers[0] = r.getDrawable(sixteenPartCircuit());
        layers[0] = r.getDrawable(lockView);*/
    }

    //reset all locks upon restarting or at the end of a level
    public static void reset() {
        numLocked = 0;
    }
}
