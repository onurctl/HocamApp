package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Diger extends AppCompatActivity {

    private Button bilgiB, kayitlarB, eklediklerimB;
    public String sifre, kuladi, yas, mail, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diger);

        bilgiB = findViewById(R.id.bilgiB); kayitlarB = findViewById(R.id.kayitlarB); eklediklerimB = findViewById(R.id.eklediklerimB);

        if (getIntent().getStringExtra("sifre") != null && getIntent().getStringExtra("kuladi") != null
                && getIntent().getStringExtra("yas") != null  && getIntent().getStringExtra("mail") != null &&
                getIntent().getStringExtra("id") != null )  {
            sifre = getIntent().getStringExtra("sifre");
            kuladi = getIntent().getStringExtra("kuladi");
            yas = getIntent().getStringExtra("yas");
            mail = getIntent().getStringExtra("mail");
            id = getIntent().getStringExtra("id");

        }


        bilgiB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bilgilerim.class);
                intent.putExtra("id", id);
                intent.putExtra("sifre", sifre);
                intent.putExtra("kuladi", kuladi);
                intent.putExtra("yas", yas);
                intent.putExtra("mail", mail);
                startActivity(intent);
            }
        });

        kayitlarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AnaSayfa = new Intent(getApplicationContext(),Kayitlar.class);
                AnaSayfa.putExtra("id", id);
                startActivity(AnaSayfa);
            }
        });

        eklediklerimB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ekledigim.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
}