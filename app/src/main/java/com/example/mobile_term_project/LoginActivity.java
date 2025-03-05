package com.example.mobile_term_project;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get the root layout
        ConstraintLayout rootLayout = findViewById(R.id.constraintLayout);

        // 1. Create the Rainbow Colors
        int[] rainbowColors = {
                Color.RED, Color.rgb(255, 165, 0), Color.YELLOW,
                Color.GREEN, Color.BLUE, Color.rgb(75, 0, 130), Color.rgb(238, 130, 238)
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

        // Existing Login Logic
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();

            // Create an Intent to start MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            // Pass the username as an extra
            intent.putExtra("USERNAME", username);

            // Start the MainActivity
            startActivity(intent);
            usernameEditText.getText().clear();
        });
    }
}