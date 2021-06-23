package com.example.receitabolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Segundatela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundatela);

        View seta1 = findViewById(R.id.seta1);

        seta1.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        View ImgCenoura = findViewById(R.id.ImgCenoura);

        ImgCenoura.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), BoloCenoura.class);
            startActivity(intent);
        });

        View ImgMil = findViewById(R.id.ImgMil);

        ImgMil.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), BoloMilho.class);
            startActivity(intent);
        });

        View imgFuba = findViewById(R.id.imgFuba);

        imgFuba.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), BoloFuba.class);
            startActivity(intent);
        });
        View Informacoes = findViewById(R.id.Informacoes);

        Informacoes.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), InformacoesCakes.class);
            startActivity(intent);
        });
    }
}