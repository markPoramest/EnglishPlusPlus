package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        int score = getIntent().getExtras().getInt("score");
        final String mode = getIntent().getExtras().getString("mode");
        TextView showScore = findViewById(R.id.score);
        showScore.setText(score+"/10");
        Button playAgain = findViewById(R.id.play);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equals("basic")) {
                    Intent i1 = new Intent(result.this, Basic_Mode.class);
                    startActivity(i1);
                    finish();
                }
                else
                {
                    Intent i1 = new Intent(result.this, AdvanceMode.class);
                    startActivity(i1);
                    finish();
                }


            }
        });
        Button menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(result.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        });
    }
}
