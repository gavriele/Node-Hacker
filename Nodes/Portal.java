package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;

import com.example.gavri.node_hunter.Exceptions.BadNumberPortalsException;
import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.R;

/**
 * Created by gavri on 8/13/2017.
 * Hitting one gate of a portal causes the active node to switch to the other gate of the portal.
 * After teleporting, activation proceeds to shift in the same direction it was shifting before.
 */

public class Portal extends Node {

    public final Portal otherGate;
    public boolean teleporting;
//    private Integer background; //shadows superclass background
    private int numDrawables = 4;

    public Portal(Node a, Node b) {
        super(a);
        otherGate = new Portal(b, this); //create the second gate and store it
        System.out.println("creating portals");

        setImage();

    }

    //second gate, identical to first
    private Portal(Node b, Portal a) {
        super(b);
        otherGate = a; //store the first gate in the second
        setImage();

    }

    @Override
    public String toString() {
        return "P ";
    }

    //visual layers for the Node
    @Override
    public void setImage() {
        System.out.println("Setting portal image");

        //if we get here there should be an upper layer so need to apply both
        Resources r = getResources();
        Drawable[] layers = new Drawable[numDrawables];
        layers[0] = r.getDrawable(R.drawable.wifi_level0_inactive);
        /*the rest of the drawables will only show up if the portal is inactive or if it is active
        and we have reached those drawables in the animation*/
        if (numDrawables > 1) layers[1] = r.getDrawable(R.drawable.wifi_level1_inactive);
        if (numDrawables > 2) layers[2] = r.getDrawable(R.drawable.wifi_level2_inactive);
        if (numDrawables > 3) layers[3] = r.getDrawable(R.drawable.wifi_level3_inactive);

        setImageDrawable(new LayerDrawable(layers));
    }

    @Override
    public boolean activate() {

        System.out.println("activating portal");
        teleporting = true;
        numDrawables = 1;
        otherGate.numDrawables = 1;

        //animate!
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setImage();
                otherGate.setImage();
                numDrawables++;
                otherGate.numDrawables++;
            }
        };

        final Handler handler = new Handler();
        for (int i = 0; i < 4; i++) {
            handler.postDelayed(runnable, 100 * i);
        }


        try {
            GameEngine.getInstance().teleport(otherGate);
        } catch (BadNumberPortalsException e) {
            System.out.println("Could not find the other gate");
            e.printStackTrace();
        }

        return false; //we want to set the other gate to currNode, not this one

    }
}
