package com.example.gavri.node_hunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.gavri.node_hunter.Threads.MusicThread;

public class GameActivity extends AppCompatActivity {
    public static float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        GameEngine.getInstance().createGrid(this, gridView);
        gridView.setNumColumns(GameEngine.WIDTH);
        scale = getResources().getDisplayMetrics().density;
        System.out.println("Scale: " + scale);
        GameEngine.getInstance().restartLevel();

        //music player
        MusicThread musicThread = new MusicThread(this, R.raw.cyberpunk);
        musicThread.start();

    }
}
