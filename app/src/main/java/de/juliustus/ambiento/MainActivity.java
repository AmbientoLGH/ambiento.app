package de.juliustus.ambiento;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //Button-Liste
    private ArrayList<ButtonProperty> modes = new ArrayList<ButtonProperty>(){{
        add(new ButtonProperty("#757575","heller"));
        add(new ButtonProperty("#424242", "dunkler"));
        add(new ButtonProperty("#000000", "off"));
        add(new ButtonProperty("#C62828","on"));

        add(new ButtonProperty("#E53935",""));
        add(new ButtonProperty("#7CB342",""));
        add(new ButtonProperty("#1976D2",""));
        add(new ButtonProperty("#37474F", "ambiento"));

        add(new ButtonProperty("#FB8C00",""));
        add(new ButtonProperty("#388E3C",""));
        add(new ButtonProperty("#03A9F4",""));
        add(new ButtonProperty("#F57F17", "chain-reaction"));

        add(new ButtonProperty("#FFEB3B",""));
        add(new ButtonProperty("#00897B",""));
        add(new ButtonProperty("#9C27B0",""));
        add(new ButtonProperty("#43A047", "blink"));

        add(new ButtonProperty("#ff4081","rainbow"));
    }};
    
    private class ButtonProperty {
        
        private String color;
        private String mode;
        
        ButtonProperty(String color, String mode) {
            this.color = color;
            this.mode = mode;
        }
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout rgbGrid = findViewById(R.id.rgb_grid);
        int dpi = (int) getResources().getDisplayMetrics().density;

        // Auflisten der Buttons
        for(ButtonProperty property : modes) {
            LinearLayout frameLayout = new LinearLayout(this);
            frameLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(dpi*10,dpi*10,dpi*10,dpi*10);
            frameLayout.setLayoutParams(layoutParams);

            String text = null;
            if(property.mode.length()>0) text = property.mode;

            frameLayout.addView(RGBButton.createButton(this,property.color,text).getView());

            rgbGrid.addView(frameLayout);
        }
    }
}
