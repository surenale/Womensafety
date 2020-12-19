package com.alethesuren.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ShakeDetector shakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShakeOptions options = new ShakeOptions()
                .background(true)
                .interval(2000)
                .shakeCount(6)
                .sensibility(1.2f);

        this.shakeDetector = new ShakeDetector(options).start(this, new ShakeCallback() {


            @Override
            public void onShake() {
                try {
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage("9847387976", null, "https://www.google.com/maps/preview/@(latitude),(longitude),(zoom level)z", null, null);
                    Toast.makeText(getBaseContext(), "Shake Detected", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            });
    }

    public void register(View v) {
        Intent i_register=new Intent(MainActivity.this,Register.class);
        startActivity(i_register);

    }

    public void display_no(View v) {
        Intent i_view=new Intent(MainActivity.this,Display.class);
        startActivity(i_view);

    }

    public void instruct(View v) {
        Intent i_help=new Intent(MainActivity.this,Instructions.class);
        startActivity(i_help);
    }

    public void verify(View v) {
        Intent i_verify=new Intent(MainActivity.this,Verify.class);
        startActivity(i_verify);
    }

}