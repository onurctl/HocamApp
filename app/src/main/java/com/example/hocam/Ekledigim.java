package com.example.hocam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.graphics.Typeface;
//import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Ekledigim extends AppCompatActivity {

   /* private Button silButon;
    private EditText idEdit;
    private TextView soruText3, soruText4;*/
    public String id;

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
        setContentView(R.layout.activity_ekledigim);

        //silButon = findViewById(R.id.kayitSilButon3);
        //idEdit = findViewById(R.id.kayitlarIDedit3);
        // soruText3 = findViewById(R.id.kayitSoruText3);  soruText4 = findViewById(R.id.kayitSoruText4);


     if (getIntent().getStringExtra("id") != null) {
          id = getIntent().getStringExtra("id");
        }

        TV_Header=(TextView) findViewById(R.id.TV_Header3);
        LV_Country=(ListView)findViewById(R.id.LV_Country3);
        btn_Get=(Button)findViewById(R.id.btn_Get3);

        btn_Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Map<String,String>> MyData = null;
              //  ListItem mydata =new ListItem();
                MyData= doInBackground();
                String[] fromwhere = { "Capital" };

                int[] viewswhere = {R.id.lblCapitalCity3};

                ADAhere = new SimpleAdapter(Ekledigim.this, MyData,R.layout.listtemplate, fromwhere, viewswhere);

                LV_Country.setAdapter(ADAhere);

            }
        });


        LV_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> obj=(HashMap<String,Object>)ADAhere.getItem(position);
                String ID=(String)obj.get("A");
                Toast.makeText(Ekledigim.this, ID, Toast.LENGTH_SHORT).show();

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
                String query = "select * from sorular where ekleyenID='" + id +"'";
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

}





