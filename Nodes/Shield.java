package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;

import com.example.gavri.node_hunter.Exceptions.InvalidColorException;
import com.example.gavri.node_hunter.NumbersDrawable;
import com.example.gavri.node_hunter.R;

import static android.graphics.Color.CYAN;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

/**
 * Created by gavri on 6/27/2017.
 * Servers act as blocks in the way which allow you to traverse the map to the locks without falling
 * off the screen.
 */

public class Shield extends Node {

    //refs to visual layers
//    private Integer background; //shadows superclass background
    private Integer shieldColor;
    private int inactiveColor;
    private String shieldHexColor; //for comparison with NumbersDrawable


    private String repString;

    public Shield(Node spotForNode, int color) throws InvalidColorException {
        super(spotForNode);
        inactiveColor = color;
        deactivate(); //all servers initially show non-active colors
    }

    @Override
    public String toString() {
        return repString;
    }


    //visual layers for the Node
    @Override
    public void setImage() {

        //if we get here there should be an upper layer so need to apply both
        Resources r = getResources();
        setImageDrawable(new LayerDrawable(new Drawable[]{r.getDrawable(shieldColor)}));

    }

    @Override
    public boolean activate() {

        //if paint's current color matches then do this
        if (NumbersDrawable.isPaintCOlor(shieldHexColor)) {
            shieldColor = R.drawable.active_shield;
            setImage();

            //change back after a delay
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after delay
                    try {
                        deactivate();
                    } catch (InvalidColorException e) {
                        System.out.println("Shield color changed to an invalid one");
                        e.printStackTrace();
                    }
                }
            }, 300);

            System.out.println("You hit a " + this + " shield");
            return true;
        } else { //otherwise return false
            NumbersDrawable.updateCurrNum(false); //update and possibly trigger loss
            return false;
        }


    }

    //undo the glow before shifting the ref to a new node
    private void deactivate() throws InvalidColorException {

        switch (inactiveColor) {

            case RED:
                shieldColor = R.drawable.red_shield;
                repString = "BR";
                shieldHexColor = "#FF0000";
                break;
            case GREEN:
                shieldColor = R.drawable.green_shield;
                repString = "BG";
                shieldHexColor = "#00FF00";
                break;
            case CYAN:
                shieldColor = R.drawable.blue_shield;
                repString = "BB";
                shieldHexColor = "#00FFFF";
                break;
            default:
                //the inactive color should ONLY be one of the above
                throw new InvalidColorException();
        }

        setImage();
    }
}
