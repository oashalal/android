package com.oashalal.app;

import androidx.appcompat.widget.AppCompatButton;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.graphics.drawable.Drawable;

public class Tile extends AppCompatButton{
    
    public int value;
    public boolean mined = false;
    public boolean flagged = false;
    public boolean opened = false;
    
    public int tileX;
    public int tileY;
    
    private static ArrayList<Drawable> colors = new ArrayList<Drawable>();
    
    public Tile(int x, int y, int value, Context context){
        super(context);
        this.value = value;
        tileX = x;
        tileY = y;
        if (colors.size() == 0)
            addColors(context);
    }
    
    @Override
    public String toString(){
        if (value > 0)
            return Integer.toString(value);
        return "";
    }
    
    public void update() {
        if (flagged) {
            setBackground(colors.get(10));
            setText("");
        } else if (!opened) {
            setBackground(colors.get(9));
            setText("");
        } else if (value == 0) {
            setBackground(colors.get(0));
        } else {
            setBackground(colors.get(value));
            setText(Integer.toString(value));
        }
    }
    
    private static void addColors(Context context) {
        colors.add(context.getResources().getDrawable(R.drawable.empty_tile));
        colors.add(context.getResources().getDrawable(R.drawable.tile1));
        colors.add(context.getResources().getDrawable(R.drawable.tile2));
        colors.add(context.getResources().getDrawable(R.drawable.tile3));
        colors.add(context.getResources().getDrawable(R.drawable.tile4));
        colors.add(context.getResources().getDrawable(R.drawable.tile5));
        colors.add(context.getResources().getDrawable(R.drawable.tile6));
        colors.add(context.getResources().getDrawable(R.drawable.tile7));
        colors.add(context.getResources().getDrawable(R.drawable.tile8));
        colors.add(context.getResources().getDrawable(R.drawable.closed_tile));
        colors.add(context.getResources().getDrawable(R.drawable.flag));
    }
}