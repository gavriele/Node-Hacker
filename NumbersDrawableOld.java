package com.example.gavri.node_hunter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.example.gavri.node_hunter.Nodes.Node;

import java.util.Random;

/**
 * Created by gavri on 7/27/2017.
 * This is the old implementation.  Uses random numbers which shift in position and have no
 * significance to the game beyond aesthetics.
 * Holds the canvas for which the upper layer of the nodes display random numbers
 */

public class NumbersDrawableOld extends Drawable {
    /*private static Random random;
    private static int[] numbers;
    private static NumbersDrawableOld[] recent;
    public static final int NUMSIZE = 7;
    private Node myNode;
    private static String paintColor;*/

    /*private static Direction currDirec;
    private static boolean animateOnlyRound;

    //locations to draw
    private static float LEFT = scale(10);
    private static float RIGHT = scale(60);
    private static float TOP = scale(50);
    private static float BOTTOM = scale(100);

    //0 through 3 where 0 represents top left and 3 bottom right
    private static int activeLocation = 2;*/


//    static private Paint paint = new Paint();
//
//    static {
//        random = new Random();
//        numbers = new int[NUMSIZE];
//        recent = new NumbersDrawableOld[NUMSIZE];
//        for (int i = 0; i < numbers.length; i++) {
//            numbers[i] = random.nextInt(10);
//        }
//    }

//    public NumbersDrawableOld(Node node) {
//        paint.setColor(Color.GREEN);
//        paint.setTextSize(scale(40));
//        /*4circuit 4parts: scale(50)
//        * 16circuit 1part: scale(50)
//        * 16circuit 4parts: scale(?)*/
//        myNode = node;
//    }

    /* Set the text starting with the active location of the recent[0] node and working backwards
    * in the opposite direction of currDirec*/
//    @Override
//    public void draw(@NonNull Canvas canvas) {
//
//        for (int i = 0; i < NUMSIZE; i++) {
//            if (this == recent[i]) {
//                paint.setAlpha(255 - i * (210 / NUMSIZE));
//                canvas.drawText("" + numbers[i], scale(5), scale(35), paint);
//            }
//        }
//
//    }
//
//    public static int getCOlor() {
//        return paint.getColor();
//    }
//
//    public static void setCOlor(String color) {
//        paint.setColor(Color.parseColor(color));
//    }

//    public static void addDrawable(NumbersDrawableOld numbersDrawable) {
//        /*shift recent and rand num gen in opp order s.t.
//          recent[i] displaying numbers[i] will put oldest num in active node,
//          giving the perception that the numbers are moving in the direction
//          of the active node.
//          */
//        /*if (recent[recent.length - 1] != null)
//            recent[recent.length - 1].myNode.setImage(); //don't litter*/
//
//        for (int i = NUMSIZE - 1; i > 0; i--) {
//            recent[i] = recent[i - 1];
//            if (recent[i] != null) recent[i].myNode.setImage();
//        }
//        recent[0] = numbersDrawable;
//        recent[0].myNode.setImage();
//
//
//        //remove this for 4 part animation (only update numbers if !animateOnly)
//        for (int i = 0; i < NUMSIZE - 1; i++) numbers[i] = numbers[i + 1];
//        numbers[NUMSIZE - 1] = random.nextInt(10);
//    }

    //sets the locations (SWITCH SCALE FOR 16 part circuit)
    /*public static void setLocations() {
        LEFT = scale(10);
        RIGHT = scale(60);
        TOP = scale(50);
        BOTTOM = scale(100);
    }*/

    /*private void fourPartDraw(Canvas canvas) {
        //maybe try creating a buffer of locations to shift?

        if (this == recent[0]) { //active node
            drawAt(canvas, numbers[0], activeLocation, 255);

            if (animateOnlyRound) { //also need to animate previous spot in this node
                drawAt(canvas, numbers[1], nextLocation(false), 200);
            }

        } else if (this == recent[1]) {

            int first = animateOnlyRound ? numbers[2] : numbers[1];
            int second = animateOnlyRound ? numbers[3] : numbers[2];

            drawAt(canvas, first, activeLocation, 150);
            drawAt(canvas, second, nextLocation(false), 120);
        } else if (this == recent[2]) {

            int first = animateOnlyRound ? numbers[4] : numbers[3];
            int second = animateOnlyRound ? numbers[5] : numbers[4];

            //drawAt(canvas, first, activeLocation, 100);
            //drawAt(canvas, second, nextLocation(false), 70);
        }
    }*/

    //once we know where the drawing belongs, we just have to draw it
    /*private void drawAt(Canvas canvas, int display, int location, int intesity) {
        paint.setAlpha(intesity);

        switch (location) {
            case 0:
                canvas.drawText("" + display, LEFT, TOP, paint);
                break;
            case 1:
                canvas.drawText("" + display, RIGHT, TOP, paint);
                break;
            case 2:
                canvas.drawText("" + display, LEFT, BOTTOM, paint);
                break;
            case 3:
                canvas.drawText("" + display, RIGHT, BOTTOM, paint);
                break;
        }

    }*/

    /*
    * updates the positions of generated numbers
    * If we only need to reanimate, move the active location in the directions of currDirec
    * If we also need to update the node, we need to move the active location backwards in the
    * opposite direction of currDirec to essentially reset the animation for the new node
    * */
    /*public static void update(boolean animateOnly) {

        animateOnlyRound = animateOnly; //for drawing purposes

        System.out.println("active location: " + activeLocation);
        System.out.println("animate only: " + animateOnly);

        numbers[0] = numbers[1];
        numbers[1] = numbers[2];
        numbers[2] = numbers[3];
        numbers[3] = numbers[4];
        numbers[4] = numbers[5];
        numbers[5] = random.nextInt(10);

        if (animateOnly) {
            activeLocation = nextLocation(true);

        } else { //update the nodes

            //reset for the new node (opposite of before)
            activeLocation = nextLocation(false);
        }

        //reset the display on all recent non-null nodes
        for (int i = 0; i < recent.length; i++)
            if (recent[i] != null) recent[i].myNode.setImage();
    }*/

    //determine the next or previous location based on the active location and the current direction
    /*private static int nextLocation(boolean forward) {

        Direction direcA = forward ? Direction.DOWN : Direction.UP;
        Direction direcB = forward ? Direction.UP : Direction.DOWN;

        switch (activeLocation) {
            case 0: //can only move down or right without switching nodes
                return (currDirec == direcA) ? 2 : 1;
            case 1: //can only down or left
                return (currDirec == direcA) ? 3 : 0;
            case 2:
                return (currDirec == direcB) ? 0 : 3;
            default: //3
                return (currDirec == direcB) ? 1 : 2;
        }
    }*/

    //figure out whether we need to switch nodes on the first animation of a move
    //also sets the new direction for animation purposes
    /*public static boolean animateOnly(Direction direction) {

        currDirec = direction;
        switch (direction) {

            case UP:
                return activeLocation > 1; //2 or 3 on bottom
            case DOWN:
                return activeLocation < 2; //0 or 1 on top
            case RIGHT:
                return activeLocation % 2 == 0; //0 or 2 left
            default: //Left
                return activeLocation % 2 != 0; //1 or 3 right
        }

    }*/

    //scale for various device sizes. size(~100) is the bottom/right edge
//    private static float scale(float size) {
//        return size * GameActivity.scale;
//    }

    @Override
    public void draw(@NonNull Canvas canvas) {

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
