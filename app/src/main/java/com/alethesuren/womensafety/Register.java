package com.alethesuren.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;

public class Register extends AppCompatActivity {
    //variables
    EditText regName,regNumber;
    Button regBtn;
    DatabaseReference reference;
    UserHelperClass helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }//onCreate Method End



    public void display(View v) {
        Intent i_view = new Intent(Register.this, Display.class);
        startActivity(i_view);

    }

    public void instructions(View v) {
        Intent i_help = new Intent(Register.this, Instructions.class);
        startActivity(i_help);

    }

    public void addAnother(View view) {
        Intent contact = new Intent(Register.this,Register.class);
        startActivity(contact);
    }

    public void contact(View view) {
    }

    public void storeInDB(View view) {
        //Hooks to all xml elements in activity_register_xml
        regName=(EditText)findViewById(R.id.editText1);
        regNumber=(EditText)findViewById(R.id.editText2);
        regBtn= (Button)findViewById(R.id.buttond);
        //helper = new UserHelperClass();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get all the value from the EditText field
                String name = regName.getText().toString().trim();
                String phn=regNumber.getText().toString().trim();
                if(name.isEmpty()){
                    Toast.makeText(Register.this,"No Name Entered",Toast.LENGTH_SHORT).show();
                }
                else if(phn.isEmpty()){
                    Toast.makeText(Register.this,"No number Entered",Toast.LENGTH_SHORT).show();
                }
                //pushing the data to the firebase
                else {
                    helper = new UserHelperClass(name, phn);
                    reference.push().setValue(helper);
                    Toast.makeText(Register.this, "Sucessfully saved", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void nextToContinue(View view) {
    }
}