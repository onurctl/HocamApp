package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EkleDersKonuSec extends AppCompatActivity {

    private Spinner dersSP, konuSP;
    private TextView dersT, konuT;
    private Button devamB;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle_ders_konu_sec);

        dersSP = findViewById(R.id.dersSpinner2);
        dersT = findViewById(R.id.dersText2);
        devamB = findViewById(R.id.devamButon);

        if (
                getIntent().getStringExtra("id") != null )  {

            id = getIntent().getStringExtra("id");

        }

        devamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SoruEkle.class);
                intent.putExtra("id", id);
                intent.putExtra("ders", dersSP.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Coğrafya");
        arrayList.add("Tarih");
        arrayList.add("Felsefe");
        arrayList.add("Güncel Dünya");
        arrayList.add("Türk Dili");
        arrayList.add("Fen Bilimleri");
        arrayList.add("İngilizce");
        arrayList.add("Vatandaşlık Bilgisi");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dersSP.setAdapter(arrayAdapter);
        dersSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }
}