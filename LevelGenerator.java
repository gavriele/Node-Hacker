package com.example.gavri.node_hunter;

import android.content.Context;


import android.graphics.Color;

import com.example.gavri.node_hunter.Enums.Direction;
import com.example.gavri.node_hunter.Exceptions.InvalidColorException;
import com.example.gavri.node_hunter.Nodes.*;

/**
 * Created by gavri on 7/23/2017.
 * This is a repository of grids representing all the levels
 */

public class LevelGenerator {
    private static final LevelGenerator ourInstance = new LevelGenerator();

    private Node[][] levelMap;

    public static LevelGenerator getInstance() {
        return ourInstance;
    }

    public final static int TOTLVLS = 12;

    private LevelGenerator() {
    }

    public Node[][] getLevelMap(int lvlNum, Context context) {

        switch (lvlNum) {
            case 1: //intro level (difficulty easy)
                System.out.println("Commencing Level 1");
                createBlank(10, 10, context);
                NumbersDrawable.setMaxTurns(2);
                createServer(2, 3, Color.GREEN);
                createLock(3, 8, Color.RED);
                break;
            case 2: //first challenge (difficulty easy)
                System.out.println("Commencing Level 2");
                createBlank(10, 10, context);
                NumbersDrawable.setMaxTurns(10);
                createServer(0, 2, Color.RED);
                createServer(9, 2, Color.CYAN);
                createServer(2, 3, Color.RED);
                createServer(3, 0, Color.CYAN);
                createServer(3, 8, Color.GREEN);
                createLock(2, 4, Color.RED);
                createLock(8, 7, Color.GREEN);
                createServer(4, 5, Color.GREEN);
                createServer(5, 6, Color.GREEN);
                createServer(1, 6, Color.RED);
                createServer(5, 1, Color.CYAN);
                createServer(6, 0, Color.CYAN);
                break;
            case 3: //portals (difficulty medium)
                System.out.println("Commencing Level 3");
                createBlank(10, 10, context);
                createLock(0, 3, Color.RED);
                createServer(0, 4, Color.RED);
                createServer(1, 8, Color.GREEN);
                createServer(2, 0, Color.RED);
                createServer(3, 2, Color.RED);
                createPortal(3, 8, 5, 4);
                createServer(4, 3, Color.RED);
                createServer(4, 6, Color.GREEN);
                createServer(4, 7, Color.GREEN);
                createLock(5, 1, Color.CYAN);
                createServer(6, 0, Color.CYAN);
                createServer(6, 7, Color.GREEN);
                createServer(7, 3, Color.CYAN);
                createServer(7, 7, Color.GREEN);
                createServer(8, 9, Color.GREEN);
                createServer(8, 1, Color.CYAN);
                createServer(9, 2, Color.CYAN);
                createServer(9, 5, Color.CYAN);
                createServer(9, 6, Color.CYAN);

                break;
            case 4: //portals 2 (difficulty medium)
                System.out.println("Commencing Level 4");
                NumbersDrawable.setMaxTurns(7);
                createBlank(10, 10, context);
                createServer(0, 4, Color.RED);
                createServer(1, 2, Color.RED);
                createServer(2, 1, Color.RED);
                createServer(1, 7, Color.GREEN);
                createLock(2, 5, Color.RED);
                createServer(3, 4, Color.RED);
                createServer(3, 3, Color.RED);
                createServer(3, 5, Color.RED);
                createLock(4, 8, Color.GREEN);
                createServer(5, 5, Color.GREEN);
                createServer(5, 6, Color.GREEN);
                createServer(6, 3, Color.CYAN);
                createPortal(6, 8, 8, 1);
                createServer(7, 3, Color.CYAN);
                createServer(7, 4, Color.CYAN);
                createLock(8, 3, Color.CYAN);
                createServer(8, 9, Color.CYAN);
                createServer(9, 7, Color.CYAN);
                break;
            case 5: //arrows (difficulty medium)
                System.out.println("Commencing Level 5");
                NumbersDrawable.setMaxTurns(10);
                createBlank(10, 10, context);
                createServer(0, 7, Color.GREEN);
                createControl(1, 3, Direction.DOWN);
                createServer(2, 1, Color.RED);
                createLock(3, 0, Color.RED);
                createControl(3, 4, Direction.RIGHT);
                createServer(3, 8, Color.GREEN);
                createServer(4, 3, Color.RED);
                createServer(5, 2, Color.RED);
                createServer(5, 7, Color.GREEN);
                createServer(6, 9, Color.CYAN);
                createServer(7, 0, Color.CYAN);
                createControl(7, 3, Direction.RIGHT);
                createLock(9, 7, Color.CYAN);
                createServer(7, 8, Color.CYAN);
                createServer(8, 4, Color.CYAN);
                createServer(9, 1, Color.CYAN);
                createServer(9, 6, Color.CYAN);
                break;
            case 6: //arrows 2 (difficulty medium)
                System.out.println("Commencing Level 6");
                NumbersDrawable.setMaxTurns(10);
                createBlank(10, 10, context);
                createServer(0, 1, Color.RED);
                createServer(0, 2, Color.RED);
                createControl(1, 5, Direction.DOWN);
                createServer(1, 6, Color.GREEN);
                createLock(2, 0, Color.RED);
                createControl(2, 4, Direction.LEFT);
                createLock(2, 8, Color.GREEN);
                createServer(3, 1, Color.RED);
                createLock(3, 3, Color.RED);
                createControl(3, 5, Direction.LEFT);
                createServer(3, 8, Color.GREEN);
                createServer(4, 1, Color.RED);
                createServer(4, 5, Color.GREEN);
                createControl(5, 2, Direction.UP);
                createServer(5, 9, Color.GREEN);
                createServer(6, 7, Color.CYAN);
                createLock(7, 0, Color.CYAN);
                createServer(8, 1, Color.CYAN);
                createControl(7, 8, Direction.LEFT);
                createServer(9, 4, Color.CYAN);
                break;
            case 7: //portals and arrows (difficulty medium)
                System.out.println("Commencing Level 7");
                createBlank(10, 10, context);
                NumbersDrawable.setMaxTurns(8);
                createServer(0, 2, Color.RED);
                createServer(1, 1, Color.RED);
                createServer(2, 0, Color.RED);
                createServer(0, 5, Color.GREEN);
                createServer(0, 8, Color.GREEN);
                createServer(1, 9, Color.GREEN);
                createPortal(2, 5, 5, 7);
                createLock(2, 7, Color.GREEN);
                createServer(3, 9, Color.GREEN);
                createControl(5, 2, Direction.RIGHT);
                createServer(5, 6, Color.CYAN);
                createLock(6, 0, Color.CYAN);
                createServer(6, 8, Color.CYAN);
                createServer(7, 5, Color.CYAN);
                createServer(8, 8, Color.CYAN);
                createServer(9, 1, Color.CYAN);
                createServer(9, 7, Color.CYAN);
                createServer(8, 0, Color.CYAN);
                break;
            case 8: //portals and arrows 2 (difficulty hard)
                System.out.println("Commencing Level 8");
                createBlank(11, 10, context);
                NumbersDrawable.setMaxTurns(13);
                createServer(0, 2, Color.RED);
                createServer(0, 3, Color.RED);
                createServer(2, 0, Color.RED);
                createServer(1, 1, Color.RED);
                createControl(1, 6, Direction.DOWN);
                createPortal(2, 3, 8, 1);
                createServer(2, 5, Color.GREEN);
                createServer(2, 8, Color.GREEN);
                createServer(3, 2, Color.RED);
                createControl(3, 5, Direction.UP);
                createControl(4, 5, Direction.DOWN);
                createServer(4, 0, Color.RED);
                createControl(4, 3, Direction.LEFT);
                createLock(4, 6, Color.GREEN);
                createServer(5, 2, Color.RED);
                createServer(8, 9, Color.CYAN);
                createControl(6, 1, Direction.RIGHT);
                createLock(5, 4, Color.RED);
                createLock(4, 8, Color.GREEN);
                createControl(6, 8, Direction.UP);
                createServer(7, 4, Color.CYAN);
                createServer(7, 5, Color.CYAN);
                createServer(7, 7, Color.CYAN);
                createServer(9, 0, Color.CYAN);
                createServer(10, 8, Color.CYAN);
                break;
            case 9: //shield intro (difficulty medium)
                System.out.println("Commencing Level 9");
                createBlank(10, 10, context);
                NumbersDrawable.setColor("#00FFFF"); //start cyan
                NumbersDrawable.setMaxTurns(9);
                createServer(0, 3, Color.RED);
                createServer(0, 4, Color.RED);
                createServer(1, 6, Color.RED);
                createServer(2, 0, Color.RED);
                createServer(0, 1, Color.RED);
                createServer(3, 9, Color.RED);
                createServer(1, 8, Color.RED);
                createShield(2, 5, Color.RED);
                createLock(2, 7, Color.RED);
                createServer(3, 0, Color.CYAN);
                createShield(4, 3, Color.CYAN);
                createShield(4, 6, Color.RED);
                createServer(5, 0, Color.CYAN);
                createLock(7, 3, Color.CYAN);
                createShield(7, 7, Color.GREEN);
                createServer(7, 8, Color.GREEN);
                createShield(8, 1, Color.CYAN);
                createServer(8, 5, Color.GREEN);
                createServer(9, 2, Color.CYAN);

                break;
            case 10: //shield (difficulty medium)
                System.out.println("Commencing Level 10");
                createBlank(10, 10, context);
                NumbersDrawable.setColor("#00FF00"); //start green
                NumbersDrawable.setMaxTurns(23);
                createServer(0, 1, Color.GREEN);
                createServer(0, 4, Color.GREEN);
                createLock(1, 2, Color.GREEN);
                createServer(1, 3, Color.GREEN);
                createServer(1, 8, Color.GREEN);
                createShield(2, 3, Color.CYAN);
                createServer(2, 9, Color.GREEN);
                createShield(3, 4, Color.CYAN);
                createLock(3, 7, Color.CYAN);
                createShield(4, 1, Color.RED);
                createShield(4, 6, Color.RED);
                createServer(4, 9, Color.GREEN);
                createServer(5, 0, Color.RED);
                createServer(5, 5, Color.GREEN);
                createShield(5, 7, Color.CYAN);
                createServer(5, 8, Color.RED);
                createServer(6, 4, Color.CYAN);
                createShield(7, 5, Color.CYAN);
                createServer(7, 8, Color.GREEN);
                createServer(8, 0, Color.CYAN);
                createServer(9, 7, Color.GREEN);

                break;
            case 11: //shield, portals, arrows (difficulty hard)
                System.out.println("Commencing Level 11");
                createBlank(11, 10, context);
                NumbersDrawable.setColor("#00FF00"); //start green
                NumbersDrawable.setMaxTurns(21);
                createServer(0, 1, Color.RED);
                createServer(0, 2, Color.RED);
                createServer(0, 3, Color.CYAN);
                createServer(0, 8, Color.GREEN);
                createServer(1, 0, Color.RED);
                createServer(1, 9, Color.GREEN);
                createShield(2, 2, Color.RED);
                createControl(2, 4, Direction.RIGHT);
                createServer(3, 5, Color.CYAN);
                createServer(2, 9, Color.CYAN);
                createServer(3, 0, Color.RED);
                createServer(3, 6, Color.CYAN);
                createServer(3, 9, Color.GREEN);
                createServer(4, 0, Color.RED);
                createPortal(4, 2, 6, 5);
                createServer(4, 4, Color.GREEN);
                createShield(4, 8, Color.GREEN);
                createControl(5, 1, Direction.RIGHT);
                createControl(5, 6, Direction.DOWN);
                createServer(5, 7, Color.GREEN);
                createLock(6, 1, Color.RED);
                createServer(7, 0, Color.GREEN);
                createShield(7, 2, Color.CYAN);
                createServer(7, 6, Color.CYAN);
                createLock(7, 8, Color.GREEN);
                createServer(8, 0, Color.GREEN);
                createControl(8, 3, Direction.RIGHT);
                createShield(8, 6, Color.GREEN);
                createServer(8, 9, Color.GREEN);
                createServer(9, 1, Color.GREEN);
                createControl(9, 4, Direction.LEFT);
                createLock(9, 5, Color.RED);
                createControl(9, 7, Direction.UP);
                createServer(10, 2, Color.GREEN);
                createServer(10, 8, Color.GREEN);
                createServer(10, 9, Color.GREEN);

                break;
            case TOTLVLS: //(difficulty easy)
                System.out.println("Commencing Final Level");
                createBlank(10, 10, context);
                //T
                for (int i = 0; i < 3; i++) createServer(0, i, Color.CYAN);
                for (int i = 1; i < 5; i++) createServer(i, 1, Color.CYAN);

                //h
                for (int i = 0; i < 5; i++) createServer(i, 4, Color.RED);
                for (int i = 2; i < 5; i++) createServer(i, 6, Color.RED);
                createServer(2, 5, Color.RED);

                //E
                for (int i = 0; i < 5; i++) createServer(i, 7, Color.GREEN);
                for (int i = 8; i < 10; i++) createServer(0, i, Color.GREEN);
                for (int i = 8; i < 10; i++) createServer(4, i, Color.GREEN);
                createServer(2, 8, Color.GREEN);

                //E
                for (int i = 5; i < 10; i++) createServer(i, 0, Color.GREEN);
                for (int i = 1; i < 3; i++) createServer(5, i, Color.GREEN);
                for (int i = 1; i < 3; i++) createServer(9, i, Color.GREEN);
                createServer(7, 1, Color.GREEN);

                //N
                for (int i = 5; i < 10; i++) createServer(i, 3, Color.CYAN);
                for (int i = 5; i < 10; i++) createServer(i, 6, Color.CYAN);
                for (int i = 6; i < 8; i++) createServer(i, 4, Color.CYAN);
                for (int i = 7; i < 9; i++) createServer(i, 5, Color.CYAN);

                //d
                for (int i = 5; i < 10; i++) createServer(i, 9, Color.RED);
                createServer(9, 8, Color.RED);

                createPortal(3, 3, 8, 7);
                createLock(8, 8, Color.RED);

                break;


            default:
                //no more levels so win the game

        }

        return levelMap;

    }

    public int[] startingPost(int lvlNum) {

        switch (lvlNum) {
            case 1:
                return new int[]{8, 3};
            case 2:
                return new int[]{1, 2};
            case 3:
                return new int[]{7, 6};
            case 4:
                return new int[]{8, 5};
            case 5:
                return new int[]{5, 4};
            case 6:
                return new int[]{6, 1};
            case 7:
                return new int[]{2, 2};
            case 8:
                return new int[]{3, 7};
            case 9:
                return new int[]{2, 3};
            case 10:
                return new int[]{7, 2};
            case 11:
                return new int[]{7, 4};
            case TOTLVLS:
                return new int[]{1, 3};
            default:
                return new int[]{0, 0};

        }

    }

    private void createServer(int row, int col, int color) {
        try {
            levelMap[row][col] = new Server(levelMap[row][col], color);
        } catch (InvalidColorException e) {
            System.out.println(color + " is not a valid server color!");
            e.printStackTrace();
        }
    }

    private void createLock(int row, int col, int color) {
        try {
            levelMap[row][col] = new Lock(levelMap[row][col], color);
        } catch (InvalidColorException e) {
            System.out.println(color + " is not a valid lock color!");
            e.printStackTrace();
        }
    }

    private void createShield(int row, int col, int color) {
        try {
            levelMap[row][col] = new Shield(levelMap[row][col], color);
        } catch (InvalidColorException e) {
            System.out.println(color + " is not a valid shield color!");
            e.printStackTrace();
        }
    }

    private void createPortal(int row, int col, int row2, int col2) {
        levelMap[row][col] = new Portal(levelMap[row][col], levelMap[row2][col2]);
        levelMap[row2][col2] = ((Portal) levelMap[row][col]).otherGate;

    }

    private void createControl(int row, int col, Direction direction) {
        levelMap[row][col] = new ControlNode(levelMap[row][col], direction);
    }

    //base map for every level
    private void createBlank(int rows, int cols, Context context) {

        //set all the old nodes to null to save memory
        if (levelMap != null) {
            for (int i = 0; i < levelMap.length; i++) {
                if (levelMap[i] != null) {
                    for (int j = 0; j < levelMap[i].length; j++) {
                        if (levelMap[i][j] != null) levelMap[i][j] = null;
                    }
                }
            }
        }

        //create a new blank map

        GameEngine.getInstance().WIDTH = cols;
        GameEngine.getInstance().HEIGHT = rows + 1;

        levelMap = new Node[rows + 1][cols];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < cols; j++) {
                levelMap[i][j] = new Node(context);

                if (i == rows) {
                    levelMap[i][j] = new Panel(levelMap[i][j]);
                }
            }
        }

        levelMap[rows][0] = new Backward(levelMap[0][0], rows, 0);
        levelMap[rows][3] = new Restart(levelMap[0][0], rows, 3);
        levelMap[rows][6] = new Mute(levelMap[0][0], rows, 6);
        levelMap[rows][9] = new Forward(levelMap[0][0], rows, 9);

    }
}
