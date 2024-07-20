package com.oashalal.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import com.oashalal.app.*;

public class MainActivity extends AppCompatActivity {
    
    GridLayout grid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        grid = findViewById(R.id.grid);
        
        MinesweeperGame game = new MinesweeperGame(8, 8, this);
        game.init(10, 4, 4);
        Tile[][] board = game.getBoard();
        
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                Tile tile = board[i][j];
                tile.setText(tile.toString());
                tile.setBackground(getResources().getDrawable(R.drawable.button));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(i, 1f), GridLayout.spec(j, 1f));
                params.width = 0;
                params.height = 0;
                grid.addView(tile, params);
            }
        }
    }
}