package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import com.example.gavri.node_hunter.Exceptions.InvalidColorException;
import com.example.gavri.node_hunter.NumbersDrawable;
import com.example.gavri.node_hunter.R;

import static android.graphics.Color.*;

/**
 * Created by gavri on 6/27/2017.
 * Servers act as blocks in the way which allow you to traverse the map to the locks without falling
 * off the screen.
 */

public class Server extends Node {

    //refs to visual layers
//    private Integer background; //shadows superclass background
    private Integer serverColor;
    private int inactiveColor;
    private String colorHex = "#FFFFFF";

    private String repString;

    private static Server activeServer;

    public Server(Node spotForNode, int color) throws InvalidColorException {
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
        setImageDrawable(new LayerDrawable(new Drawable[]{r.getDrawable(serverColor)}));

        /*Drawable[] layers = new Drawable[2];
        background = sixteenPartCircuit();
        layers[0] = r.getDrawable(background);
        layers[0] = r.getDrawable(serverColor);*/

    }

    @Override
    public boolean activate() {

        NumbersDrawable.setColor(colorHex);
        NumbersDrawable.updateCurrNum(false);
        if (activeServer != null) {
            try {
                activeServer.deactivate(); //we're moving away so deactivate the current server
            } catch (InvalidColorException e) {
                e.printStackTrace();
            }
        }

//        NumbersDrawable.paint.setColor(Color.parseColor(colorHex));
        serverColor = R.drawable.simple_server_active;
        setImage();
        activeServer = this;
        System.out.println("You hit a " + this + " server");
        return false;
    }

    //undo the glow before shifting the ref to a new node
    private void deactivate() throws InvalidColorException {

        switch (inactiveColor) {

            case RED:
                serverColor = R.drawable.simple_server_red;
                colorHex = "#FF0000";
                repString = "SR";
                break;
            case GREEN:
                serverColor = R.drawable.simple_server_green;
                repString = "SG";
                colorHex = "#00FF00";
                break;
            case CYAN:
                serverColor = R.drawable.simple_server_blue;
                colorHex = "#00FFFF";
                repString = "SB";
                break;
            default:
                //the inactive color should ONLY be one of the above
                throw new InvalidColorException();
        }

        setImage();
    }
}
