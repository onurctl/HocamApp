package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnaSayfa extends AppCompatActivity {

    private Button soruEkleButon, testeGirButon, digerButon;
    private TextView hocamText2, tKA;
    public String id, sifre, kuladi, yas, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        soruEkleButon = findViewById(R.id.soruEkleButon); testeGirButon = findViewById(R.id.testeGirButon);
        hocamText2 = findViewById(R.id.hocamText2);
        digerButon = findViewById(R.id.digerButon);
        tKA = findViewById(R.id.textViewKA);

        if (getIntent().getStringExtra("sifre") != null && getIntent().getStringExtra("kuladi") != null
                && getIntent().getStringExtra("yas") != null  && getIntent().getStringExtra("mail") != null &&
                getIntent().getStringExtra("id") != null )  {
            sifre = getIntent().getStringExtra("sifre");
             kuladi = getIntent().getStringExtra("kuladi");
             yas = getIntent().getStringExtra("yas");
             mail = getIntent().getStringExtra("mail");
            id = getIntent().getStringExtra("id");

        }

        tKA.setText(kuladi);

        soruEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EkleDersKonuSec.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        digerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Diger.class);
                intent.putExtra("id", id);
                intent.putExtra("sifre", sifre);
                intent.putExtra("kuladi", kuladi);
                intent.putExtra("yas", yas);
                intent.putExtra("mail", mail);
                startActivity(intent);



            }
        });

        testeGirButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TestKonuSec.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
}