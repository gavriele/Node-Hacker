package com.example.gavri.node_hunter.Nodes;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;

import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.R;

/**
 * Created by gavri on 8/14/2017.
 * This is a panel button used for skipping to the next level on click
 */

public class Forward extends Panel {
    private Integer backwardView;

    public Forward(Node originalNode, int row, int col) {
        super(originalNode, row, col);
        backwardView = R.drawable.forward_inactive;
        setImage();
    }

    //visual layers for the Node
    public void setImage() {

        //if we get here there should be an upper layer so need to apply both
        Resources r = getResources();
        setImageDrawable(new LayerDrawable(new Drawable[]{r.getDrawable(backwardView)}));
    }

    @Override
    public void onClick(View v) {
        System.out.println("skipping to next level");
        backwardView = R.drawable.forward_active;
        setImage();
        GameEngine.getInstance().winLevel(true); //"won level" but we're skipping

    }

    public String toString() {
        return "XF";
    }
}
