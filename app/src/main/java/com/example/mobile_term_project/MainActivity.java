package com.example.mobile_term_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements
        View.OnTouchListener
    {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            v.setOnTouchListener(this);
            return insets;
        });
    }
    public float x,y;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case (MotionEvent.ACTION_DOWN) :
                //Log.d("DEBUG_TAG","Action was DOWN");
                x = event.getX();
                y = event.getY();
                return true;
            case (MotionEvent.ACTION_MOVE) :
                //Log.d("DEBUG_TAG","Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                float newX = event.getX();
                float newY = event.getY();
                if(Math.abs(x-newX) > Math.abs(y-newY)){
                    if(x-newX <= 0){
                        Toast.makeText(this,"Right", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Left", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(y-newY <= 0){
                        Toast.makeText(this,"Down", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"Up", Toast.LENGTH_SHORT).show();
                    }
                }
                //Log.d("DEBUG_TAG","Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("DEBUG_TAG","Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("DEBUG_TAG","Movement occurred outside bounds of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}