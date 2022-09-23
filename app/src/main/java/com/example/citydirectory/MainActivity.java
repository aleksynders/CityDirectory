package com.example.citydirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connection;
    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void GetTextFromSQL(View v){
        TextView ID = findViewById(R.id.IDorg);
        TextView Name = findViewById(R.id.NAMEorg);
        TextView Place = findViewById(R.id.PLACEorg);
        TextView Number = findViewById(R.id.NUMBERorg);
        TextView Mail = findViewById(R.id.MAILorg);

        Button List = findViewById(R.id.buttonNext);
        Button Add = findViewById(R.id.buttonAdd);

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection!=null){
                String query = "Select * From Organizations";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    ID.setText(resultSet.getString(1));
                    Name.setText(resultSet.getString(2));
                    Place.setText(resultSet.getString(3));
                    Number.setText(resultSet.getString(4));
                    Mail.setText(resultSet.getString(5));
                }
                List.setVisibility(View.GONE);
                Add.setVisibility(View.VISIBLE);
                ID.setVisibility(View.VISIBLE);
                Name.setVisibility(View.VISIBLE);
                Place.setVisibility(View.VISIBLE);
                Number.setVisibility(View.VISIBLE);
                Mail.setVisibility(View.VISIBLE);
            }
            else{
                ConnectionResult = "Check Connection!";
            }
        }
        catch (Exception ex){

        }
    }
    public void GoAddOrgActivity(View v){
        Intent intent = new Intent(this, AddOrg.class);
        startActivity(intent);
    }
}