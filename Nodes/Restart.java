package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.view.View;

import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.R;

/**
 * Created by gavri on 7/25/2017.
 * This is a panel button used for resetting the current level on click
 */

public class Restart extends Panel {
    private Integer restartView;

    public Restart(Node originalNode, int row, int col) {
        super(originalNode, row, col);
        restartView = R.drawable.restart;
        setImage();
    }

    //visual layers for the Node
    public void setImage() {

        //if we get here there should be an upper layer so need to apply both
        Resources r = getResources();
        setImageDrawable(new LayerDrawable(new Drawable[]{r.getDrawable(restartView)}));
    }

    @Override
    public void onClick(View v) {
        System.out.println("restarting level");
        restartView = R.drawable.restart_active;
        setImage();
        //delay so we can see view the green before restarting
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after delay
                GameEngine.getInstance().restartLevel();
            }
        }, 140);

    }

    public String toString() {
        return "XR";
    }
}
