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
            return insets;
        });
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return false;
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