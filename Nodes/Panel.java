package com.example.gavri.node_hunter.Nodes;


import com.example.gavri.node_hunter.GameEngine;

/**
 * Created by gavri on 7/25/2017.
 */

public class Panel extends Node {

    public Panel(Node originalNode) {
        super(originalNode);
//        setImage();
    }

    public Panel(Node originalNode, int row, int col) {
        super(originalNode, row, col);
    }

    public void setImage() {
    }  //display nothing

    @Override
    public boolean activate() { //trigger restart level

        System.out.println("You Lost");
        GameEngine.getInstance().loseGame();
        return false;
    }

    public String toString() {
        return "X ";
    }

}
