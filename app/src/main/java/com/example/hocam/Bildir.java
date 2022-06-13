package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Bildir extends AppCompatActivity {

    private Button gonder;
    private EditText aciklama;
    private TextView sebepText;
    private Spinner sebepSP;
    public String sid2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildir);

        sebepSP = findViewById(R.id.bildirimSpinner);
        aciklama = findViewById(R.id.aciklamaEdit);
        gonder = findViewById(R.id.gonderButon);

       // if (getIntent().getStringExtra("veri") != null && getIntent().getStringExtra("veri2") != null )  {
        //    String al = getIntent().getStringExtra("veri") + getIntent().getStringExtra("veri2");
         //   aciklama.setText(al);
       // }

        if (getIntent().getStringExtra("soruidsi") != null
        )  {

            sid2 = getIntent().getStringExtra("soruidsi");


        }

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Baglanti newcon = new Baglanti();
                Connection connection = newcon.con();

                try {
                    if (connection != null) {

                        String giris = " insert into sorular (bildirimVar, bildirimNeden) WHERE soruid = '" + sid2 +"'"
                                + " values (?,?)";
                        PreparedStatement preparedStmt = connection.prepareStatement(giris);
                        preparedStmt.setString(1, "var");
                        preparedStmt.setString(2, sebepSP.getSelectedItem().toString());
                        // buna int girmezse uyarı

                        preparedStmt.execute();
                        Toast.makeText(Bildir.this, "Bildirim başarıyla gerçekleşti!", Toast.LENGTH_LONG).show();
                        connection.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("İntihal");
        arrayList2.add("Hatalı-Eksik");
        arrayList2.add("Müfredat Dışı");
        arrayList2.add("Ders-Soru Uyumsuz");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sebepSP.setAdapter(arrayAdapter2);
        sebepSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName2 = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }
}