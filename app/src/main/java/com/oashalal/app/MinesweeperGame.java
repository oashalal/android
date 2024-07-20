package com.oashalal.app;

import java.util.Random;
import java.util.Arrays;
import com.oashalal.app.Tile;
import android.content.Context;
import android.view.View;

public class MinesweeperGame {
    
    private Tile[][] board;
    private Random random = new Random();
    private static int[][] cells = {{-1, -1}, {-1, 0}, {-1, 1},
                                    {0, -1}, {0, 1},
                                    {1, -1}, {1, 0}, {1, 1}
    };
    
    private boolean firstClick = true;
    
    public MinesweeperGame(int width, int height, Context context){
        board = new Tile[width][height];
        
        for (int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                board[y][x] = new Tile(x, y, 0, context);
                board[y][x].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickTile(((Tile)v).tileX, ((Tile)v).tileY);
                    }
                });
            }
        }
    }
    
    public void init(int minesNumber, int clickX, int clickY) {
        
        int height = board.length;
        int width = board[0].length;
        
        for (int i=0; i<minesNumber; i++){
            int x = 0;
            int y = 0;
            int distance = -1;
            while (distance < 7){
                x = random.nextInt(width);
                y = random.nextInt(height);
                distance = (x - clickX) * (x - clickX) + (y - clickY) * (y - clickY);
            }
            Tile tile = board[y][x];
            if (!tile.mined)  
                tile.mined = true;
            else i--;
        }
        
        for (int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                if (board[y][x].mined) 
                    continue;
                int mineCount = getMineCount(x, y);
                board[y][x].value = mineCount;
            }
        }
        click(clickX, clickY);
    }
    
    public Tile[][] getBoard(){
        return board;
    }
    
    private int getMineCount(int x, int y){
        int mineCount = 0;
        for (int i=0; i<cells.length; i++){
            try {
                if (board[y + cells[i][0]][x + cells[i][1]].mined)
                    mineCount ++;
            } catch(ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        return mineCount;
    }
    
    public void click(int x, int y) {
        Tile tile = board[y][x];
        if (firstClick || (tile.opened && tile.value == getMineCount(x, y))){
            firstClick = false;
            for (int i=0; i<cells.length; i++){
                try {
                    tile = board[y + cells[i][0]][x + cells[i][1]];
                    if (!tile.opened)
                        open(x + cells[i][1], y + cells[i][0]);
                } catch(ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
            return;
        } else if(!tile.opened){
            tile.flagged = !tile.flagged;
            tile.update();
        }
    }
    
    private void open(int x, int y){
        Tile tile = board[y][x];
        if (!tile.flagged && tile.mined){
            tile.setText("GG");
            tile.opened = true;
            tile.update();
        }
        if (tile.value > 0 && tile.value < 9){
            tile.opened = true;
            tile.update();
            return;
        }
        tile.opened = true;
        tile.update();
        click(x, y);
    }
    
    public void clickTile(int x, int y) {
        if (firstClick){
            init(10, x, y);
            firstClick = false;
        } else {
            click(x, y);
        }
    }
}