package com.example.citydirectory;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionHelper {
    String userName, userPassword, ip, port, dataBase;
@SuppressLint("NewApi")
    public Connection connectionClass()
    {
        ip = "ngknn.ru";
        dataBase = "CityDirectoryGromov";
        userPassword = "12357";
        userName = "31П";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dataBase + ";user=" + userName + ";password=" + userPassword + ";";
            connection = DriverManager.getConnection(connnectionURL);
        }
        catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}
