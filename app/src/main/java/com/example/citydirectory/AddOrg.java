package com.example.citydirectory;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.sql.Connection;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Statement;

public class AddOrg extends AppCompatActivity {


    Connection connection;
    String errorMessage = "";

    EditText ID;
    EditText Name;
    EditText Place;
    EditText Number;
    EditText Mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_org);

        ID = findViewById(R.id.IdOrgAdd);
        Name = findViewById(R.id.nameOrgAdd);
        Place = findViewById(R.id.placeOrgAdd);
        Number = findViewById(R.id.numberOrgAdd);
        Mail = findViewById(R.id.mailOrgAdd);
    }

    public void inputSQL(View v) {
        TextView ID = findViewById(R.id.IdOrgAdd);
        TextView Name = findViewById(R.id.nameOrgAdd);
        TextView Place = findViewById(R.id.placeOrgAdd);
        TextView Number = findViewById(R.id.numberOrgAdd);
        TextView Mail = findViewById(R.id.mailOrgAdd);
        try {

            ConnectionHelper ch = new ConnectionHelper();
            connection = ch.connectionClass();
            if (connection != null) {
                String query = "Insert into Organizations(ID, Name, Place, Number, Mail)" +
                        " Values('" + ID.getText() + "', '" + Name.getText() + "', '" + Place.getText() + "', '" + Number.getText() + "', '" + Mail.getText() + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                Toast.makeText(this, "Организация добавлена!", Toast.LENGTH_LONG).show();

            } else {
                errorMessage = "Попробуйте еще раз.";
            }
        } catch (Exception ex) {
            errorMessage = "Check Connection!";
        }
    }
}