package com.example.hocam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItem {

    // BUNUN AYRI SINIFTA OLMASI DA ZORUNLU DEGIL KI EKLEDIGIM KISMINA ALIRIM BUNU WHERE DE NULL VERIRSE VEYA
    // EN AZIDAN DB KODU KISMINI ALIRIM

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    Ekledigim idAl = new Ekledigim();


 /*   public List<Map<String,String>> doInBackground() {

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
                String query = "select * from sorular where ekleyenID='" + idAl.id +"'";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                   // datanum.put("ID",rs.getString("aSk"));
                   // datanum.put("Country",rs.getString("bSk"));
                    datanum.put("Capital",rs.getString("soruMetni")+"\n"+rs.getString("cSk"));
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
    }*/


}



