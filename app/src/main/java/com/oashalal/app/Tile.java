package com.oashalal.app;

import androidx.appcompat.widget.AppCompatButton;
import android.content.Context;

public class Tile extends AppCompatButton{
    
    public int value;
    public boolean mined = false;
    public boolean flagged = false;
    public boolean opened = false;
    
    public Tile(int x, int y, int value, Context context){
        super(context);
        this.value = value;
    }
    
    @Override
    public String toString(){
        //if (mined) return "G";
        if (!opened){
            if (!flagged)    
                return "W";
            return "P";
        }
        return Integer.toString(value);
    }
}