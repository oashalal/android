package com.oashalal.app;

import java.util.Random;
import java.util.Arrays;
import com.oashalal.app.Tile;
import android.content.Context;

public class MinesweeperGame {
    
    private Tile[][] board;
    private Random random = new Random();
    private static int[][] cells = {{-1, -1}, {-1, 0}, {-1, 1},
                                    {0, -1}, {0, 1},
                                    {1, -1}, {1, 0}, {1, 1}
    };
    
    private boolean first = true;
    
    public MinesweeperGame(int width, int height, Context context){
        board = new Tile[width][height];
        
        for (int y=0; y<8; y++){
            for (int x=0; x<8; x++){
                board[y][x] = new Tile(x, y, 0, context);
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
    
    public void click(int x, int y){
        Tile tile = board[y][x];
        if (first || (tile.opened && tile.value == getMineCount(x, y))){
            first = false;
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
        }
    }
    
    private void open(int x, int y){
        Tile tile = board[y][x];
        if (!tile.flagged && tile.mined){
            tile.value = 9;
        }
        if (tile.value > 0 && tile.value < 9){
            tile.opened = true;
            return;
        }
        tile.opened = true;
        click(x, y);
    }
}