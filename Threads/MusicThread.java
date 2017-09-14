package com.example.gavri.node_hunter.Threads;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by gavri on 8/15/2017.
 * Plays the background music
 */

public class MusicThread extends Thread {

    private static MediaPlayer player;

    public MusicThread(Context context, Integer music) {
        if (player == null) player = MediaPlayer.create(context, music);
    }

    @Override
    public void run() {

        player.setLooping(true);
        player.start();
    }

    public static void mute() {
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.start();
        }
    }


}
