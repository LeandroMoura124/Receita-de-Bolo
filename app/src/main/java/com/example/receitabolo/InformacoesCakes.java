package com.example.receitabolo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InformacoesCakes extends AppCompatActivity {

    private ImageView imgWeb;
    private Button btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_cakes);

        View setaInfo = findViewById(R.id.setaInfo);

        setaInfo.setOnClickListener((View.OnClickListener) v -> {
            Intent intent = new Intent(getApplicationContext(), Segundatela.class);
            startActivity(intent);
        });

        imgWeb = (ImageView) findViewById(R.id.IcoFace);
        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/tudogostoso")));
            }
        });

        imgWeb = (ImageView) findViewById(R.id.IcoInsta);
        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/tudogostosooficial/?hl=pt-br")));
            }
        });

        imgWeb = (ImageView) findViewById(R.id.IcoTwitter);
        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Tudo_Gostoso?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor")));
            }
        });

        btnWeb = (Button) findViewById(R.id.botaoSite);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tudogostoso.com.br/")));
            }
        });







    }
}