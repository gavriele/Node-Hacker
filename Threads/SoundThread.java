package com.example.gavri.node_hunter.Threads;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.gavri.node_hunter.R;

import java.util.Random;

/**
 * Created by gavri on 8/15/2017.
 */

public class SoundThread extends Thread {

    private MediaPlayer player;
    private static Random random = new Random();
    private static SoundThread[] computerBleeps;
    private static boolean isMuted;

    public SoundThread(Context context, Integer music) {
        player = MediaPlayer.create(context, music);
    }

    public SoundThread(SoundThread other) {
        player = other.player;
    }

    @Override
    public void run() {
        if (!isMuted) player.start();
    }

    //for server bleeps:
    public void initializeBleeps(Context context) {
        computerBleeps = new SoundThread[5];
        computerBleeps[0] = this; //save space by make the first one computer_bleeps0
        computerBleeps[1] = new SoundThread(context, R.raw.computer_bleeps1);
        computerBleeps[2] = new SoundThread(context, R.raw.computer_bleeps2);
        computerBleeps[3] = new SoundThread(context, R.raw.computer_bleeps3);
        computerBleeps[4] = new SoundThread(context, R.raw.computer_bleeps4);
    }

    public SoundThread randomBleep() {
        if (computerBleeps == null) {
            System.out.println("Attempting to play a null computer bleep");
            return this;
        }
        int choser = random.nextInt(5);
        return computerBleeps[choser];
    }

    public static void mute() {
        isMuted = !isMuted;
    }

}
