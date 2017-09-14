package com.example.gavri.node_hunter.Nodes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;

import com.example.gavri.node_hunter.Enums.Direction;
import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.R;

/**
 * Created by gavri on 6/27/2017.
 * Changes the direction of activation mid-motion to a given alternate direction by halting current
 * motion and immediately proceeding with activation in a new (or the same) direction.
 */

public class ControlNode extends Node {

    private Direction arrowDirection;
    private boolean isActive;
    private Bitmap activeArrowBitmap;
    private Bitmap inactiveArrowBitmap;

    public ControlNode(Node spotForNode, Direction direction) {
        super(spotForNode);
        arrowDirection = direction;

        switch (arrowDirection) {

            case UP:
                activeArrowBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.usb_active_up);
                inactiveArrowBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.usb_inactive_up);
                break;
            case DOWN:
                setRotatedBitmap(true, 180);
                setRotatedBitmap(false, 180);
                break;
            case RIGHT:
                setRotatedBitmap(true, 90);
                setRotatedBitmap(false, 90);
                break;
            case LEFT:
                setRotatedBitmap(true, -90);
                setRotatedBitmap(false, -90);
                break;
        }

        setImage();
    }

    //rotate bitmap as needed
    private void setRotatedBitmap(boolean isActiveBitmap, int rotateDegree) {
        Integer originalRes = isActiveBitmap ? R.drawable.usb_active_up : R.drawable.usb_inactive_up;
        Bitmap originalBmp = BitmapFactory.decodeResource(this.getResources(), originalRes);
        Bitmap rotatedBmp = Bitmap.createBitmap(originalBmp.getWidth(), originalBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(rotatedBmp);
        tempCanvas.rotate(rotateDegree, originalBmp.getWidth() / 2, originalBmp.getHeight() / 2);
        tempCanvas.drawBitmap(originalBmp, 0, 0, null);

        //set the correct bitmap
        if (isActiveBitmap) activeArrowBitmap = rotatedBmp;
        else inactiveArrowBitmap = rotatedBmp;
    }

    //apply directional commands and activate this node
    @Override
    public boolean activate() {

        isActive = true;
        setImage();

        //change back after a delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after delay
                isActive = false;
                setImage();
            }
        }, 300);

        //switching direction so start a new call on recursive activation
        GameEngine.getInstance().redirect(arrowDirection, this);
        return false;//we are not continuing the orignial call because we made a new one
    }

    //visual layers for the Node
    public void setImage() {
        if (isActive) setImageBitmap(activeArrowBitmap);
        else setImageBitmap(inactiveArrowBitmap);
    }

    @Override
    public String toString() {
        String direcStr = "";
        switch (arrowDirection) {

            case UP:
                direcStr = "U";
                break;
            case DOWN:
                direcStr = "D";
                break;
            case RIGHT:
                direcStr = "R";
                break;
            case LEFT:
                direcStr = "L";
                break;
        }
        return super.toString(0) + direcStr;
    }
}
