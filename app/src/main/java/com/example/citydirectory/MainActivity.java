package com.example.citydirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.RowSet;

public class MainActivity extends AppCompatActivity {
    Connection connection;
    String ConnectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void GetTextFromSQL(View v){
        TableLayout listen = findViewById(R.id.listen);
        listen.removeAllViews();
        Button Add = findViewById(R.id.buttonAdd);

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection!=null){
                String query = "Select * From Organizations";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    TableRow dbOutputRow = new TableRow(this);
                    dbOutputRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT);

                    TextView NAME_ORG = new TextView(this);
                    params.weight = 1.0f;
                    NAME_ORG.setLayoutParams(params);
                    NAME_ORG.setText(resultSet.getString(2));
                    NAME_ORG.setTextSize(12);
                    dbOutputRow.addView(NAME_ORG);

                    TextView NUMBER_ORG = new TextView(this);
                    params.weight = 1.0f;
                    NUMBER_ORG.setLayoutParams(params);
                    NUMBER_ORG.setText(resultSet.getString(4));
                    NUMBER_ORG.setTextSize(12);
                    dbOutputRow.addView(NUMBER_ORG);

                    TextView MAIL_ORG = new TextView(this);
                    params.weight = 1.0f;
                    MAIL_ORG.setLayoutParams(params);
                    MAIL_ORG.setText(resultSet.getString(5));
                    MAIL_ORG.setTextSize(12);
                    dbOutputRow.addView(MAIL_ORG);

                    listen.addView(dbOutputRow);
                }
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