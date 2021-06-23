package com.example.receitabolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BoloMilho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolo_milho);

        View VoltarMil = findViewById(R.id.VoltarMil);

        VoltarMil.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), Segundatela.class);
            startActivity(intent);
        });
    }
}