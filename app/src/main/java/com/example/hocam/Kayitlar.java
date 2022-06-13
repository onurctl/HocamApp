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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kayitlar extends AppCompatActivity {

    private Button cevapB, silB;
    private EditText soruedit;
    private TextView soruText, soruText2, t;

    public String idKayit;

    TextView TV_Header;
    Typeface font;
    Button btn_Get;
    ListView LV_Country;
    SimpleAdapter ADAhere;

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitlar);

       cevapB = findViewById(R.id.cvpgorBtn);
       silB = findViewById(R.id.silbtn);
       soruedit = findViewById(R.id.snoedit);

        TV_Header=(TextView) findViewById(R.id.TV_Header3);
        LV_Country=(ListView)findViewById(R.id.LV_Country3);
        btn_Get=(Button)findViewById(R.id.btn_Get3);

        if (getIntent().getStringExtra("id") != null )  {

            idKayit = getIntent().getStringExtra("id");

        }

        cevapB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsoru = soruedit.getText().toString();
                Baglanti newcon = new Baglanti();
                Connection connection = newcon.con();

                try{
                    if (connection!=null){

                        String giris = "SELECT * FROM soruKayit WHERE kayitid= '"+idsoru+"'";
                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(giris);

                        while(rs.next()){
                            Toast.makeText(Kayitlar.this, "Cevap: "+rs.getString("cevap"), Toast.LENGTH_SHORT).show();


                            }

                        }
                    } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        silB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idsoru = soruedit.getText().toString();

                Baglanti newcon = new Baglanti();
                Connection connection = newcon.con();
                try{
                    if (connection!=null){
                        Statement stmt = connection.createStatement();
                        stmt.execute("DELETE FROM soruKayit WHERE kayitid= '" + idsoru +"'");

                                Toast.makeText(Kayitlar.this, "Soru kayıtlardan silindi!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        });

        btn_Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map<String,String>> MyData = null;
                //  ListItem mydata =new ListItem();
                MyData= doInBackground();
                String[] fromwhere = { "Capital" };

                int[] viewswhere = {R.id.lblCapitalCity3};

                ADAhere = new SimpleAdapter(Kayitlar.this, MyData,R.layout.listtemplate3, fromwhere, viewswhere);

                LV_Country.setAdapter(ADAhere);

            }
        });


        LV_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> obj=(HashMap<String,Object>)ADAhere.getItem(position);
                String ID=(String)obj.get("A");
                Toast.makeText(Kayitlar.this, ID, Toast.LENGTH_SHORT).show();

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
                String query = "select * from soruKayit where keydedenid='" + idKayit +"'";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    // datanum.put("ID",rs.getString("aSk"));
                    // datanum.put("Country",rs.getString("bSk"));
                    datanum.put("Capital","Kayıt No: "+rs.getString("kayitid")+".\n"+rs.getString("soruMetin")+"\na) "+rs.getString("secA")
                            +"\nb) "+    rs.getString("secB")         +"\nc) "+  rs.getString("secC")    +"\nd) "+
                            rs.getString("secD"));
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

    }
