package com.oashalal.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    
    GridLayout grid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        grid = findViewById(R.id.grid);
        
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                Button button = new Button(this);
                button.setText(Integer.toString(i*8+j));
                button.setBackground(getResources().getDrawable(R.drawable.button));
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(j), GridLayout.spec(i));
                grid.addView(button, params);
            }
        }
    }
}