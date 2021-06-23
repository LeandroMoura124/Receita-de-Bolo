package com.example.receitabolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View segundatel = findViewById(R.id.segundatel);

        segundatel.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), Segundatela.class);
            startActivity(intent);
        });

        View imgSair = findViewById(R.id.imgSair);
        imgSair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getBaseContext(), "Volte sempre...",Toast.LENGTH_LONG).show();
                finish();
                System.exit(0);
            }
        });
    }
}