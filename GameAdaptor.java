package com.example.gavri.node_hunter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gavri.node_hunter.Nodes.Node;

/**
 * Created by gavri on 6/27/2017.
 */

class GameAdaptor extends BaseAdapter {


    public GameAdaptor(Context context) {
    }

    @Override
    public int getCount() {
        return GameEngine.HEIGHT * GameEngine.WIDTH;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int row = position / GameEngine.WIDTH;
        int col = position % GameEngine.WIDTH;
        Node node = GameEngine.getInstance().getNode(row, col);
        node.setOnClickListener(node);
        node.setAdjustViewBounds(true);

        return node;
    }
}
