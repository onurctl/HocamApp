package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

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

public class SoruEkle extends AppCompatActivity {

    private EditText soruEdit, a, b, c, d;
    private Spinner dersSP, konuSP, cevapSP;
    private TextView dersT, konuT, cevapT;
    private String kullID;
    private Button yollaBtn;
    public String id, ders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_ekle);

        soruEdit = findViewById(R.id.editMetin);
        a = findViewById(R.id.editA); b = findViewById(R.id.editB); c = findViewById(R.id.editC); d = findViewById(R.id.editD);
        cevapSP = findViewById(R.id.cevapSpinner);
      cevapT = findViewById(R.id.cevapText);
      yollaBtn = findViewById(R.id.yollaButon);

        if ( getIntent().getStringExtra("ders") != null&&
        getIntent().getStringExtra("id") != null )  {

            id = getIntent().getStringExtra("id");
            ders = getIntent().getStringExtra("ders");

        }

        // buraya buton ekle konu getir dersin once konulari yukler sonra tekrar sayfayi intent eder kendi içinde

        yollaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Baglanti newcon = new Baglanti();
                Connection connection = newcon.con();

                try {
                    if (connection != null) {

                        String giris = " insert into sorular (ekleyenID, soruMetni, aSk, bSk, cSk, dSk, cevapSk, dersAdi)"
                                + " values (?,?,?,?,?,?, ?, ?)";
                    PreparedStatement preparedStmt = connection.prepareStatement(giris);
                        preparedStmt.setString(1, id);
                        preparedStmt.setString(2, soruEdit.getText().toString());
                        preparedStmt.setString(3, a.getText().toString());
                        preparedStmt.setString(4, b.getText().toString());
                        preparedStmt.setString(5, c.getText().toString());
                        preparedStmt.setString(6, d.getText().toString());
                        preparedStmt.setString(7, cevapSP.getSelectedItem().toString());
                        preparedStmt.setString(8, ders);
                        // buna int girmezse uyarı

                        preparedStmt.execute();
                        Toast.makeText(SoruEkle.this, "Sorunuz başarıyla yollandı!", Toast.LENGTH_LONG).show();
                        connection.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        ArrayList<String> arrayList3 = new ArrayList<>();
        arrayList3.add("A");
        arrayList3.add("B");
        arrayList3.add("C");
        arrayList3.add("D");
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList3);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cevapSP.setAdapter(arrayAdapter3);
        cevapSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName2 = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

    public void soruEkleBilgi(String kullaniciID){

        kullID = kullaniciID;


    }


}