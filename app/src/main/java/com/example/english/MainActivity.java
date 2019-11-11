package com.example.english;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
                String[] vocab1 = new String[]{"Democracy = ประชาธิปไตย", "Dictator = เผด็จการ", "Constitution = รัฐธรรมนูญ", "Parliament = รัฐสภา", "Junta = รัฐบาลทหาร", "Liberate = ปลดปล่อย", "Generation = รุ่น", "Freedom = อิสรภาพ", "Obey = เชื่อฟัง", "Vengeance = การแก้แค้น", "Revolution = การปฏิวัติ", "Deviant = ผิดปกติ"};
                TextView vocab = findViewById(R.id.vocabulary);
                Random r = new Random();
                int random = r.nextInt(vocab1.length);
                vocab.setText((CharSequence)vocab1[random]);
                Button basic = findViewById(R.id.basic);
                Button adv = findViewById(R.id.adv);
                basic.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                    public final void onClick(View it) {
                        Intent i1 = new Intent((Context)MainActivity.this, Basic_Mode.class);
                        MainActivity.this.startActivity(i1);
                    }
                }));
                adv.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                    public final void onClick(View it) {
                        Intent i1 = new Intent((Context)MainActivity.this, AdvanceMode.class);
                        MainActivity.this.startActivity(i1);
                    }
                }));
    }




    }

