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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bilgilerim extends AppCompatActivity {

    private String kulAdi, sif, yasi, maili;
    private Button guncelleButon;
    // bu button sınıflarını vs buraya tekrar import etmemek için mainactivitiy'i miras almak iyi fikir boş yere tekrar tekrar import etmez
    private Spinner hedefSinavSp, egitimDurumSp;
    public EditText yas, mail, kullaniciAdi2, passwordM;
    private TextView hedef, egitim, sifreText;
    public String kulAd, id, sifrem, yasim, mailm, kuladim;


    public void bilgiAl(String sifreAl) {

       // kullaniciAdi2.setText(kullaniciAdi);
      //  passwordM.setText(sifreAl);
       // mail.setText(mailg);
       // yas.setText(yasg);

        //kulAd = kullaniciAdi;
                //bunları this ile yaz setter ile ayrica

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgilerim);


        guncelleButon = findViewById(R.id.kaydolButon2);
        hedefSinavSp = findViewById(R.id.hedefSinavSp2);
        egitimDurumSp = findViewById(R.id.egitimDurumSp2);
       yas = findViewById(R.id.yas2);
       mail = findViewById(R.id.mail2);
       kullaniciAdi2 = findViewById(R.id.kullaniciAdi2);
        hedef = findViewById(R.id.hedefSinavText2);
        egitim = findViewById(R.id.egitimText2);
        sifreText = findViewById(R.id.sifreText2);
       passwordM = findViewById(R.id.sifremEdit2);

       /* if (getIntent().getStringExtra("sifre") != null && getIntent().getStringExtra("kuladi") != null
        && getIntent().getStringExtra("yas") != null  && getIntent().getStringExtra("mail") != null &&
        getIntent().getStringExtra("id") != null )  {
            String al = getIntent().getStringExtra("sifre");
            String al2 = getIntent().getStringExtra("kuladi");
            String al3 = getIntent().getStringExtra("yas");
            String al4 = getIntent().getStringExtra("mail");
            id = getIntent().getStringExtra("id");

        }*/

        if (getIntent().getStringExtra("sifre") != null && getIntent().getStringExtra("kuladi") != null
                && getIntent().getStringExtra("yas") != null  && getIntent().getStringExtra("mail") != null &&
                getIntent().getStringExtra("id") != null )  {
            sifrem = getIntent().getStringExtra("sifre");
            kuladim = getIntent().getStringExtra("kuladi");
            yasim = getIntent().getStringExtra("yas");
            mailm = getIntent().getStringExtra("mail");
            id = getIntent().getStringExtra("id");
            passwordM.setText(sifrem);
            yas.setText(yasim);
            kullaniciAdi2.setText(kuladim);
            mail.setText(mailm);

        }

        guncelleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Baglanti newcon = new Baglanti();
                Connection connection = newcon.con();

                try
                {
                    if (connection!=null) {
                        // create our java preparedstatement using a sql update query
                        PreparedStatement ps = connection.prepareStatement(
                                "UPDATE kullaniciBilgileri SET sifre = ?, mail = ?, yas = ? WHERE kulid = ?");
                        // iki where ile .... "UPDATE kullaniciBilgileri SET nickname = ?, sifre = ?, mail = ?, yas = ? WHERE nickname = ? AND seq_num = ?");
                        // set the preparedstatement parameters
                        ps.setString(1, passwordM.getText().toString());
                        ps.setString(2, mail.getText().toString());
                        ps.setString(3, yas.getText().toString());
                        ps.setString(4, id);

                        // call executeUpdate to execute our sql update statement
                        ps.executeUpdate();
                        ps.close();
                        Toast.makeText(Bilgilerim.this, "Bilgileriniz güncellendi!", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (SQLException se)
                {
                    // log the exception
                  //  throw se;
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

        //sayfa açıldığında select ile bilgileri alsın buraya ekle



        // update yazacağız




      /*  try {
            if (connection != null) {

                //update için de parametreli yolla
               // String giris = "UPDATE kullaniciBilgileri SET WHERE nickname= '"+kullaniciAdi.getText().toString()+"'";
                //Statement stmt = connection.createStatement();
                //ResultSet rs = stmt.executeQuery(giris);
                Statement stmt = connection.createStatement();
                String sql = "UPDATE CUSTOMERS SET FNAME = ?, LNAME = ? WHERE ID = ?";


           //     stmt.setString(1, customer.getFirstName());
            //    stmt.setString(2, customer.getLastName());
             //   stmt.setInt(3, customer.getId());


               // stmt.executeUpdate(sql);

            }
            //KULLANICI ADI AYNI OLABILIR ID YAPMAK LAZIM O NEDENLE WHERE İ ŞİMDİLİK GEÇ AMA BUNU TESTTE YAPIYORUZ DERIZ

            //guncelle butonu ile update sorgusunu yolla

        } catch (Exception e) {
            e.printStackTrace();
        } */
    }

}

