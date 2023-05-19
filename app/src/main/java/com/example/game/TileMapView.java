package com.example.game;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class TileMapView extends View {
    private Bitmap[] tileset;
    private int[][] tilemap;
    private int tileWidth;
    private int tileHeight;

    public TileMapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Load tile images from drawable resources
        tileset = new Bitmap[6];
        tileset[0] = BitmapFactory.decodeResource(getResources(), R.drawable.safe);
        tileset[1] = BitmapFactory.decodeResource(getResources(), R.drawable.road);
        tileset[2] = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        tileset[3] = BitmapFactory.decodeResource(getResources(), R.drawable.end);
        tileset[4] = BitmapFactory.decodeResource(getResources(), R.drawable.water);

        // Initialize tilemap
        tilemap = new int[][]{
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {4, 3, 4, 3, 4, 3, 4},
                {4, 4, 4, 4, 4, 4, 4},
                {4, 4, 4, 4, 4, 4, 4},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };

        // Get tile size
        tileWidth = tileset[1].getWidth(); // width = 168
        tileHeight = tileset[1].getHeight(); // height = 168
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Loop through tilemap and draw each tile
        for (int row = 0; row < tilemap.length; row++) {
            for (int col = 0; col < tilemap[row].length; col++) {
                int tileIndex = tilemap[row][col];
                Bitmap tile = tileset[tileIndex];
                int x = col * tileWidth - 45; // -45 to center the screen
                int y = row * tileHeight;
                canvas.drawBitmap(tile, x, y, null);
            }
        }
    }
}
