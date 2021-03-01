package com.spin_wheel.spinwheelgame;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        findViewById(R.id.btn_spin_wheel).setOnClickListener(view -> {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        });
    }
}
