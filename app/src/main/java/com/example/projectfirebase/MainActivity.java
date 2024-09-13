package com.example.projectfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonSchedule, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonSchedule = findViewById(R.id.buttonSchedule);
        buttonLogout = findViewById(R.id.buttonLogout);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            textViewWelcome.setText("Bem-vindo, " + username + "!");
        }

        buttonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Logout Button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logout functionality here (if needed)
                // For simplicity, let's just go back to LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close MainActivity
            }
        });
    }
}
