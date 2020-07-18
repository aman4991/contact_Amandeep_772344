package com.example.contact_Amandeep_772344_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelperPerson mDataBase;

    EditText firstname , lastname , address , phonenumber,email;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.edittextfirstname);
        lastname = findViewById(R.id.edittextlastname);
        address = findViewById(R.id.edittextaddress);
        phonenumber = findViewById(R.id.edittextphone);
        email = findViewById(R.id.et_email);
        findViewById(R.id.btnaddperson).setOnClickListener(this);
        findViewById(R.id.tvviewperson).setOnClickListener(this);
        mDataBase = new DatabaseHelperPerson(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnaddperson:
                addData();
                break;

            case R.id.tvviewperson:
                Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(intent);
            break;
        }
    }

    private void addData() {
        String firstName = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();
        String phoneNumber = phonenumber.getText().toString().trim();
        String sAddress= address.getText().toString().trim();
        String sEmail = email.getText().toString().trim();

        if (mDataBase.addPerson(firstName,lastName,phoneNumber,sAddress,sEmail))
            Toast.makeText(this, " Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, " Error", Toast.LENGTH_SHORT).show();


        firstname.setText("");
        lastname.setText("");
        phonenumber.setText("");
        address.setText("");
        email.setText("");
    }

}
