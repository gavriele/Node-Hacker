package com.example.gavri.node_hunter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.example.gavri.node_hunter.Nodes.Node;

import java.util.ArrayList;

/**
 * Created by gavri on 7/27/2017.
 * Holds the canvas for which the upper layer of the nodes display random numbers
 */

public class NumbersDrawable extends Drawable {
    public static final int FADENUMSIZE = 7;
    public static final int HIGHALPHA = 255;
    public static final int LOWALPHA = 65;
    public static final int DefaultMaxTurns = 12;

    private static ArrayList<NumbersDrawable> recent;
    private static int currNum;  //number the active node's drawable is displaying
    private int myNum; //number this drawable is displaying

    private Node myNode;
    private static String paintColor;


    static private Paint paint = new Paint();

    static {
        recent = new ArrayList<>(100);
        currNum = DefaultMaxTurns;
    }

    public NumbersDrawable(Node node) {
        paint.setColor(Color.GREEN);
        paint.setTextSize(scale(30));
        myNode = node;
        myNum = -1;
    }

    /* Set the drawable text to myNum
    * */
    @Override
    public void draw(@NonNull Canvas canvas) {

        if (this == recent.get(recent.size() - 1)) {
            myNum = currNum;
        }

        if (myNum == -1) { //this is not in recent yet
            return;
        }

        String zero = (myNum < 10) ? "0" : "";

        for (int i = recent.size() - 1; i > recent.size() - FADENUMSIZE - 1; i--) {
            if (i >= 0 && this == recent.get(i)) {
                int currAlpha = HIGHALPHA - (recent.size() - 1 - i) * ((HIGHALPHA - LOWALPHA) / FADENUMSIZE);
                paint.setAlpha(currAlpha);
                canvas.drawText(zero + myNum, scale(4), scale(35), paint);
                return; //we've found the node already so there's nothing else to do
            }
        }

        //if we get here the number must be a litter
        paint.setAlpha(LOWALPHA);
        canvas.drawText(zero + myNum, scale(5), scale(35), paint);

    }

    //check whether the hex codes match for shields
    public static boolean isPaintCOlor(String color) {
        System.out.println("NumbersDrawable color: " + paintColor + " shield color: " + color);
        return color.equals(paintColor);
    }

    //change the color when we hit a lock or server
    public static void setColor(String color) {
        paint.setColor(Color.parseColor(color));
        paintColor = color;
        System.out.println("Color is now " + color);
    }

    //reset all recent drawables (maybe will help with immediate color changes?)
    public static void resetRecent() {
        for (NumbersDrawable n : recent) n.myNode.setImage();
    }

    //make the last one represent the active node
    public static void addDrawable(NumbersDrawable numbersDrawable) {
        recent.remove(numbersDrawable);
        recent.add(numbersDrawable);
        resetRecent();
    }

    //if a specific level relies on a turn limit (set in LevelGenerator)
    public static void setMaxTurns(int maxTurns) {
        currNum = maxTurns;
    }

    //decrement total moves remaining and check if we have reached 0
    public static void updateCurrNum(boolean justWon) {
        currNum--;
        System.out.println("Moves remaining: " + currNum);
        if (!justWon && currNum == 0) GameEngine.getInstance().loseGame();
    }

    public String toString() {
        return "D" + myNode;
    }

    //reset defaults for new levels
    public static void reset() {
        currNum = DefaultMaxTurns;
        recent = new ArrayList<>(100);
        NumbersDrawable.setColor("#00FF00"); //default color is green
    }

    //scale for various device sizes. size(~100) is the bottom/right edge
    private static float scale(float size) {
        return size * GameActivity.scale;
    }

    @Override
    public void setAlpha(int alpha) { //@IntRange(from = 0, to = 255)

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) { //@Nullable

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
