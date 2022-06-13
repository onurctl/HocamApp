package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private Button girisButon, kayitButon;
    public EditText sifrem, kullaniciAdim;
    private TextView hocamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girisButon = findViewById(R.id.girisButon); kayitButon = findViewById(R.id.kayitButon);
        sifrem = findViewById(R.id.kullanSifre); kullaniciAdim = findViewById(R.id.kullanAd);
        hocamText = findViewById(R.id.hocamText);

        kayitButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Kayit = new Intent(getApplicationContext(),Kayit.class);
                startActivity(Kayit);
            }
        });

        // ana sayfaya bir de çıkış butonu ekle çıkış yap

        girisButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //gecis();

               if(sifrem.getText().toString().equals("") || kullaniciAdim.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Kullanıcı Adı veya şifre boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }

                else {

                    Baglanti newcon = new Baglanti();
                    Connection connection = newcon.con();

                    try{
                        if (connection!=null){

                            String giris = "SELECT * FROM kullaniciBilgileri WHERE nickname= '"+kullaniciAdim.getText().toString()+"'";
                            Statement stmt = connection.createStatement();
                            ResultSet rs = stmt.executeQuery(giris);

                            while(rs.next()){

                                if (kullaniciAdim.getText().toString().equals(rs.getString(2))
                                        && sifrem.getText().toString().equals(rs.getString(3)))
                                {
                                   // Bilgilerim bilg = new Bilgilerim(); // bunu constructor olarak da yollayabilirdik buradan parantez içine yazıp
                                    //String abcd = rs.getString(3);
                                    //System.out.println(abcd);
                                    //bilg.bilgiAl(abcd);
                                    Intent intent = new Intent(getApplicationContext(),AnaSayfa.class);
                                    intent.putExtra("id", rs.getString(1));
                                    intent.putExtra("sifre", sifrem.getText().toString());
                                    intent.putExtra("kuladi", kullaniciAdim.getText().toString());
                                    intent.putExtra("yas", rs.getString(5));
                                    intent.putExtra("mail", rs.getString(4));
                                    startActivity(intent);
                                    //SoruEkle idyolla = new SoruEkle();
                                    //idyolla.soruEkleBilgi(rs.getString(0));

                                    //gecis();

                                    //bunları tek tek olusturacagina buradan getter setter ile oluştur o sayfa açılınca buradan alsın hepsini full al
                                }
                                else
                                    Toast.makeText(MainActivity.this, "Kullanıcı Adı veya şifre hatalı!", Toast.LENGTH_SHORT).show();
                                }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

        public void gecis(){
        //Intent AnaSayfa = new Intent(getApplicationContext(),AnaSayfa.class);
        //startActivity(AnaSayfa);
            Intent AnaSayfa = new Intent(getApplicationContext(),Bilgilerim.class);
            startActivity(AnaSayfa);
        }

}

