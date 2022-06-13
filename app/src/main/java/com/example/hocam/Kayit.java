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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Kayit extends AppCompatActivity {

    private Button kaydolButon;
    // bu button sınıflarını vs buraya tekrar import etmemek için mainactivitiy'i miras almak iyi fikir boş yere tekrar tekrar import etmez
    private Spinner hedefSinavSp, egitimDurumSp;
    private EditText yas, mail, kullaniciAdi2, sifreEdit, password;
    private TextView hedef, egitim, sifreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        kaydolButon = findViewById(R.id.kaydolButon);
        hedefSinavSp = findViewById(R.id.hedefSinavSp); egitimDurumSp = findViewById(R.id.egitimDurumSp);
        yas = findViewById(R.id.yas);
        mail = findViewById(R.id.mail); kullaniciAdi2 = findViewById(R.id.kullaniciAdi3);
        hedef = findViewById(R.id.hedefSinavText); egitim = findViewById(R.id.egitimText);
        sifreText = findViewById(R.id.sifreText);
        password = findViewById(R.id.sifreEdit);

        kaydolButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Baglanti newcon = new Baglanti();
                    Connection connection = newcon.con();

                    try {
                        if (connection != null) {

                                String giris = " insert into kullaniciBilgileri (nickname, sifre, mail, yas, hedefSinav, egitimDurum)"
                                        + " values (?,?,?,?,?,?)";
                                PreparedStatement preparedStmt = connection.prepareStatement(giris);
                                preparedStmt.setString(1, String.valueOf(kullaniciAdi2.getText()));
                                preparedStmt.setString(2, String.valueOf(password.getText()));
                                preparedStmt.setString(3, mail.getText().toString());
                                preparedStmt.setString(4, yas.getText().toString());
                                // buna int girmezse uyarı
                                preparedStmt.setString(5, hedefSinavSp.getSelectedItem().toString());
                                preparedStmt.setString(6, egitimDurumSp.getSelectedItem().toString());

                                /*   if (mail.getText().toString().equals(" ") || kullaniciAdi2.getText().toString().equals(" ") || sifreEdit.getText().toString().equals(" ")) {
                                    Toast.makeText(Kayit.this, "Kullanıcı Adı, şifre veya mail boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                                    } */

                                    preparedStmt.execute();
                                    Toast.makeText(Kayit.this, "Kayıt başarılı!", Toast.LENGTH_LONG).show();
                                    Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(MainActivity);
                                    connection.close();
                            }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("YKS");
        arrayList.add("ALES");
        arrayList.add("KPSS");
        arrayList.add("DGS");
        arrayList.add("YDS");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hedefSinavSp.setAdapter(arrayAdapter);
        hedefSinavSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Lise");
        arrayList2.add("Üniversite");
        arrayList2.add("Yüksek Lisans");
        arrayList2.add("Doktora");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        egitimDurumSp.setAdapter(arrayAdapter2);
        egitimDurumSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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