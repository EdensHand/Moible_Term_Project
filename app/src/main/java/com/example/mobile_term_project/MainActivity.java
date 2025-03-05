package com.example.mobile_term_project;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneOffset.UTC);
    private final ArrayList<String> swipeData = new ArrayList<>();
    private String timeStamp;
    private DatabaseReference swipeRef;
    private float x, y;
    private long startTime;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = getIntent().getStringExtra("USERNAME");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        swipeRef = database.getReference(userName);
        //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Get the root layout
        ConstraintLayout rootLayout = findViewById(R.id.main);

        // 1. Create the Rainbow Colors
        int[] rainbowColors = {
                Color.RED, Color.rgb(255, 165, 0), Color.YELLOW,
                Color.GREEN, Color.BLUE, Color.rgb(75, 0, 130),
                Color.rgb(238, 130, 238)
        };

        // 2. Create the Gradient
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.setIntrinsicHeight(2000);
        shapeDrawable.setIntrinsicWidth(2000);
        LinearGradient linearGradient = new LinearGradient(
                0, 0, 0, 2000, // Start and end points for the gradient
                rainbowColors,
                null, // No color positions, evenly distribute the colors
                Shader.TileMode.REPEAT // Repeat the gradient
        );
        shapeDrawable.getPaint().setShader(linearGradient);

        // 3. Animate the Gradient
        // Create a Matrix object
        Matrix matrix = new Matrix();
        ValueAnimator animator = ValueAnimator.ofFloat(0, 2000);
        animator.setDuration(3000); // Duration of one cycle (in milliseconds)
        animator.setRepeatCount(ValueAnimator.INFINITE); // Repeat indefinitely
        animator.setRepeatMode(ValueAnimator.RESTART); // Restart from the beginning
        animator.setInterpolator(new LinearInterpolator()); // Smooth animation
        animator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            matrix.setTranslate(0, animatedValue);
            linearGradient.setLocalMatrix(matrix);
            shapeDrawable.invalidateSelf();
        });
        animator.start();

        // 4. Set the Animated Drawable as the Background
        rootLayout.setBackground(shapeDrawable);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            v.setOnTouchListener(this);
            return insets;
        });
    }

    private Toast currentToast;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                x = event.getX();
                y = event.getY();
                timeStamp = formatter.format(Instant.now());
                startTime = System.currentTimeMillis();
                return true;

            case (MotionEvent.ACTION_MOVE):
                return true;

            case (MotionEvent.ACTION_UP):
                float newX = event.getX();
                float newY = event.getY();
                String swipeDirection;

                if (Math.abs(x - newX) > Math.abs(y - newY)) {
                    swipeDirection = (x - newX <= 0) ? "Right" : "Left";
                } else {
                    swipeDirection = (y - newY <= 0) ? "Down" : "Up";
                }

                long endTime = System.currentTimeMillis();
                String swipeDuration = String.valueOf(endTime - startTime);

                swipeData.clear();
                swipeData.add(swipeDirection);
                swipeData.add(swipeDuration);
                swipeData.add(timeStamp);

                if (currentToast != null) {
                    currentToast.cancel();
                }

                currentToast = Toast.makeText(this, swipeDirection, Toast.LENGTH_SHORT);
                currentToast.show();

                sendSwipeDataToFirebase(swipeData);
                return true;

            case (MotionEvent.ACTION_CANCEL):
                Log.d("DEBUG_TAG", "Action was CANCEL");
                return true;

            case (MotionEvent.ACTION_OUTSIDE):
                Log.d("DEBUG_TAG", "Movement occurred outside bounds of current screen element");
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }

    private void sendSwipeDataToFirebase(ArrayList<String> swipeData) {
        String swipeId = swipeRef.push().getKey();

        Map<String, Object> swipeMap = new HashMap<>();
        swipeMap.put("swipeDirection", swipeData.get(0));
        swipeMap.put("swipeDuration", swipeData.get(1));
        swipeMap.put("timeStamp", swipeData.get(2));

        assert swipeId != null;
        swipeRef.child(swipeId).setValue(swipeMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Firebase", "Swipe data saved successfully");
                    } else {
                        Log.e("Firebase", "Swipe data save failed", task.getException());
                    }
                });
    }
}