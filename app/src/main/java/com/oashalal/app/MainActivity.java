package com.oashalal.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    
    EditText editA;
    EditText editB;
    EditText editC;
    
    Button button;

    TextView resultText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //get elements
        editA = findViewById(R.id.edit_a);
        editB = findViewById(R.id.edit_b);
        editC = findViewById(R.id.edit_c);
        button = findViewById(R.id.button);
        resultText = findViewById(R.id.result);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double a = getValue(editA);
                    double b = getValue(editB);
                    double c = getValue(editC);
                    
                    double d = b*b-4*a*c;
                    
                    if (d >= 0){
                        double x1 = (-b + Math.sqrt(d))/(2*a);
                        double x2 = (-b - Math.sqrt(d))/(2*a);
                        
                        resultText.setText("Корни: " + x1 + "; " + x2);
                    } else {
                        resultText.setText("Нет корней.");
                    }
                    
                } catch (IllegalArgumentException ecs){
                    resultText.setText("Ошибка");
                }
            }
        });
    }
    
    public static double getValue(EditText edit) throws IllegalArgumentException {
        try{
            return Double.parseDouble(edit.getText().toString());
        } catch(Exception ec){
            throw new IllegalArgumentException("error");
        }    
    }
}