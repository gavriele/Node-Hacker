package com.example.gavri.node_hunter.Nodes;

import android.view.View;

import com.example.gavri.node_hunter.R;
import com.example.gavri.node_hunter.Threads.MusicThread;
import com.example.gavri.node_hunter.Threads.SoundThread;

/**
 * Created by gavri on 8/21/2017.
 * Press this button to mute all audio
 */

public class Mute extends Panel {
    private static boolean isMuted;

    public Mute(Node originalNode, int row, int col) {
        super(originalNode, row, col);
        setImage();
    }

    //visual layers for the Node
    public void setImage() {
        setImageResource(isMuted ? R.drawable.mute_active : R.drawable.mute_inactive);
    }

    @Override
    public void onClick(View v) {
        isMuted = !isMuted; //mute or unmute
        System.out.println((isMuted ? "" : "un") + "muting all audio");
        setImage();
        MusicThread.mute();
        SoundThread.mute();
    }

    public String toString() {
        return "XM";
    }
}
