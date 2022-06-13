package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestEkrani extends AppCompatActivity {

    private TextView soru1, soru2;
    private EditText id;
    private Button kaydetBTN, bildirBTN;
    public String idm, ders;

    TextView TV_Header2;
    Typeface font;
    Button btn_Get2;
    ListView LV_Country2;
    SimpleAdapter ADAhere;

    private Button kayd, bild;
    private EditText sidm;

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ekrani);

        sidm = findViewById(R.id.soruidm); kayd = findViewById(R.id.kaydButon); bild = findViewById(R.id.bildButon);

        //soru1 = findViewById(R.id.kayitSoruText5); soru2 = findViewById(R.id.kayitSoruText6);
        //kaydetBTN = findViewById(R.id.kaydetButton); bildirBTN = findViewById(R.id.bildirButton);
        //id = findViewById(R.id.soruidedit);

        if (getIntent().getStringExtra("id") != null && getIntent().getStringExtra("ders") != null
               )  {

            ders = getIntent().getStringExtra("ders");
            idm = getIntent().getStringExtra("id");

        }

        bild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bildir.class);
                intent.putExtra("soruidsi", sidm.getText().toString());
                startActivity(intent);


            }
        });


        //


        kayd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sid = sidm.getText().toString();


                try
                {
                    Baglanti conStr=new Baglanti();
                    connect =conStr.con();        // Connect to database
                    if (connect == null)
                    {
                        ConnectionResult = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                      String query = "select * from sorular where soruid='" + sid +"'";

                       // System.out.println(sid);

                     //   String query = "INSERT INTO soruKayit (soruid2, soruMetin, secA, secB, secC, secD, cevap, dersAd) "
                      //          + "SELECT soruid, soruMetni, aSk, bSk, cSk, dSk, cevapSk, dersAdi "
                       //         + "FROM sorular "
                        //        + "where soruid=?";

                        Statement stmt = connect.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                     //   PreparedStatement preparedStmt = connect.prepareStatement(query);
                        //preparedStmt.setString(1, sid);


                   while (rs.next()){

                      String sorID = rs.getString("soruid");
                            String ekleyenID = rs.getString("ekleyenID");
                            String kaydedenID = idm;
                            String soru = rs.getString("soruMetni");
                            String as = rs.getString("aSk");
                            String bs = rs.getString("bSk");
                            String cs = rs.getString("cSk");
                            String ds = rs.getString("dSk");
                            String cvp = rs.getString("cevapSk");
                            String ders_ad = rs.getString("dersAdi");


                            //System.out.println(sorID+ kaydedenID+ekleyenID+soru+as+bs+cs+ds+cvp+ders_ad);

                           kaydet(sorID, kaydedenID,ekleyenID,soru, as, bs, cs, ds,cvp,ders_ad);


                     }


                        //ConnectionResult = " successful";
                        //isSuccess=true;
                        //connect.close();
                    }
                }
                catch (Exception ex)
                {
                   // isSuccess = false;
                   // ConnectionResult = ex.getMessage();
                }









            }
        });



        TV_Header2=(TextView) findViewById(R.id.TV_Header3);
        LV_Country2=(ListView)findViewById(R.id.LV_Country3);
        btn_Get2=(Button)findViewById(R.id.btn_Get3);

        btn_Get2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map<String,String>> MyData = null;
                //  ListItem mydata =new ListItem();
                MyData= doInBackground();
                String[] fromwhere = { "Capital" };

                int[] viewswhere = {R.id.lblCapitalCity3};

                ADAhere = new SimpleAdapter(TestEkrani.this, MyData,R.layout.listtemplate2, fromwhere, viewswhere);

                LV_Country2.setAdapter(ADAhere);

            }
        });


        LV_Country2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> obj=(HashMap<String,Object>)ADAhere.getItem(position);
                String ID=(String)obj.get("A");
                Toast.makeText(TestEkrani.this, ID, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public List<Map<String,String>> doInBackground() {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try
        {
            Baglanti conStr=new Baglanti();
            connect =conStr.con();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else
            {
                // Change below query according to your own database.
                String query = "select * from sorular where dersAdi='" + ders +"'";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    // datanum.put("ID",rs.getString("aSk"));
                    // datanum.put("Country",rs.getString("bSk"));
                    datanum.put("Capital","Soru No: "+rs.getString("soruid")+".\n"+rs.getString("soruMetni")+"\na) "+rs.getString("aSk")
                            +"\nb) "+    rs.getString("bSk")         +"\nc) "+  rs.getString("cSk")    +"\nd) "+
                            rs.getString("dSk"));
                    data.add(datanum);
                }


                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }

   void kaydet(String sorID, String kaydedenID,String ekleyenID, String soru, String as, String bs, String cs,
                       String ds, String cvp, String ders_ad)
    {
        Baglanti newcon = new Baglanti();
        Connection connection = newcon.con();

        try {
            if (connection != null) {

                String giris = " insert into soruKayit (soruid2, keydedenid, ekleyenid2, soruMetin, " +
                        "secA, secB, secC, secD, cevap, dersAd)"
                        + " values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(giris);
                preparedStmt.setString(1, sorID);
                preparedStmt.setString(2, kaydedenID);
                preparedStmt.setString(3, ekleyenID);
                preparedStmt.setString(4, soru);
                preparedStmt.setString(5, as);
                preparedStmt.setString(6, bs);
                preparedStmt.setString(7, cs);
                preparedStmt.setString(8, ds);
                preparedStmt.setString(9, cvp);
                preparedStmt.setString(10, ders_ad);

                preparedStmt.execute();
                Toast.makeText(TestEkrani.this, "Kayıt başarılı!", Toast.LENGTH_LONG).show();
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}