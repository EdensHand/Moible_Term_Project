package com.example.mobile_term_project;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this,this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            v.setOnTouchListener(this);
            return insets;
        });
    }
    public float x,y;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
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
                        Log.d("DEBUG_TAG","Action was moving right");
                    }else{
                        Log.d("DEBUG_TAG","Action was moving left");
                    }
                }else{
                    if(y-newY <= 0){
                        Log.d("DEBUG_TAG","Action was moving down");
                    }else{
                        Log.d("DEBUG_TAG","Action was moving up");
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
    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        Log.d("IDK", "onDown: called");
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        Log.d("IDK", "onShowPress: called");

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        Log.d("IDK", "onSingleTapUp: called");
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        Log.d("IDK", "onScroll: called");
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        Log.d("IDK", "onLongPress: called");

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        Log.d("IDK", "onFling: called");
        return false;
    }
}