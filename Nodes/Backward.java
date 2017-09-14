package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.view.View;

import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.R;

/**
 * Created by gavri on 8/16/2017.
 * This is a panel button used for going back to the previous level on click
 */

public class Backward extends Panel {
    private Integer backwardView;

    public Backward(Node originalNode, int row, int col) {
        super(originalNode, row, col);
        backwardView = R.drawable.backward_inactive;
        setImage();
    }

    //visual layers for the Node
    public void setImage() {
        Resources r = getResources();
        setImageDrawable(new LayerDrawable(new Drawable[]{r.getDrawable(backwardView)}));
    }

    @Override
    public void onClick(View v) {
        System.out.println("going back one level");
        backwardView = R.drawable.backward_active;
        setImage();

        //delay so we can see view the green before restarting
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after delay
                GameEngine.getInstance().goBackLevel();
                GameEngine.getInstance().restartLevel();
            }
        }, 140);

    }

    public String toString() {
        return "XB";
    }
}
