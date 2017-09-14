package com.example.gavri.node_hunter.Nodes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;

import com.example.gavri.node_hunter.BackgroundGenerator;
import com.example.gavri.node_hunter.Enums.Direction;
import com.example.gavri.node_hunter.GameEngine;
import com.example.gavri.node_hunter.NumbersDrawable;

/**
 * Created by gavri on 6/27/2017.
 * This represents any single node in the grid which can be travelled.
 * It also displays the corresponding image for the slot in the grid.
 */

public class Node extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

    private Context context;
    private int col;
    private int row;
    private static int rowPos = 0;
    private static int colPos = 0;
    //    private Integer background;
    private boolean isActive;
    private NumbersDrawable upperLayer;

    public Node(Context context) {
        super(context);
        this.context = context;

        col = colPos++;
        row = rowPos;
        if (colPos == GameEngine.WIDTH) {
            colPos = 0;
            rowPos++;
        }
        if (rowPos == GameEngine.HEIGHT) rowPos = 0;

        //set the display layers
//        background = sixteenPartCircuit();
        upperLayer = new NumbersDrawable(this);

        setImage();
    }

    //copy constructor for child nodes
    Node(Node originalNode) {
        super(originalNode.context);

        col = originalNode.col;
        row = originalNode.row;

    }

    //copy constructor for child nodes when can't pass on same spot
    Node(Node originalNode, int row, int col) {
        super(originalNode.context);

        this.row = row;
        this.col = col;

    }

    Integer fourPartCircuit() {
        return BackgroundGenerator.fourPartCircuit(row, col);
    }

    Integer sixteenPartCircuit() {
        return BackgroundGenerator.sixteenPartCircuit(row, col);
    }

    //set this to be the active node
    public boolean activate() {
        Node prevActive = GameEngine.getInstance().getCurr();

        //check if the node is already active
        if (this == prevActive) return false;

        //check if the server is within range
        if (row != prevActive.row && col != prevActive.col) {
            System.out.println("The node at " + row + "," + col + " is not within range");
            return false;
        }

        //activate the newly active node
        setActive();

        //reset the previously active node
        prevActive.isActive = false;

        return true; //assumption right now is that activating this node is possible
    }

    //just activation without checking
    public boolean setActive() {
        isActive = true;
        NumbersDrawable.addDrawable(upperLayer);
        System.out.println("Activating " + row + "," + col);
        return true;
    }

    //change the row and col as nodes shift
    public void swapPos(Node other) {
        int rowTemp = row;
        int colTemp = col;
        row = other.row;
        col = other.col;
        other.row = rowTemp;
        other.col = colTemp;

        //reset the corresponding views
        /*background = sixteenPartCircuit();
        other.background = other.sixteenPartCircuit();*/
        setImage();
        other.setImage();
    }

    //visual layers for the Node
    public void setImage() {

        //if we get here there should be an upper layer so need to apply both
        Drawable[] layers = new Drawable[1];
//        layers[0] = r.getDrawable(background);
        layers[0] = upperLayer;

        setImageDrawable(new LayerDrawable(layers));

    }

    @Override
    public String toString() {
        return (isActive ? "1" : "0") + " "; //rep color by place in the enum list
    }

    public String toString(int numSpaces) {
        String str = isActive ? "1" : "0"; //rep color by place in the enum list
        for (int x = 0; x < numSpaces; x++) str += " "; //add extra spaces as needed
        return str;
    }

    //for debugging nodes
    public void printInfo() {
        System.out.println("Printing node:(" + row + "," + col + ") " + toString());
    }

    @Override
    public void onClick(View v) {
        System.out.println("moving: " + GameEngine.moving);

        int currRow = GameEngine.getInstance().getCurr().row;
        int currCol = GameEngine.getInstance().getCurr().col;

        if (Math.abs(row - currRow) == Math.abs(col - currCol)) {
            //same node or not in range
            System.out.println("Not in range or already active");

        } else { //move in a specific direction until no longer possible

            Direction toMove;
            if (Math.abs(row - currRow) < Math.abs(col - currCol)) { // movement right/left
                if (col > currCol) { //right
                    System.out.println("moving Right");
                    toMove = Direction.RIGHT;
                } else {//left
                    System.out.println("moving left");
                    toMove = Direction.LEFT;
                }
            } else {// movement up/down
                if (row > currRow) { //down
                    System.out.println("moving down");
                    toMove = Direction.DOWN;
                } else {//up
                    System.out.println("moving up");
                    toMove = Direction.UP;
                }
            }

            if (!GameEngine.moving) { //don't want simultaneous motion
                GameEngine.moving = true;
                //initialize delay speed at 180 ms
                GameEngine.getInstance().activate(toMove, GameEngine.startSpeed);
            } else {
                System.out.println("Already in motion");
            }

        }
//        swapNodes();
    }

    /*private void swapNodes() {
        //swap with curr
        GameEngine.getInstance().swapNodes(row, col);
        GameEngine.getInstance().printGrid();
    }*/
}
