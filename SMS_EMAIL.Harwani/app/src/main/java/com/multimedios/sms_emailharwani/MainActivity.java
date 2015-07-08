package com.multimedios.sms_emailharwani;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ImageButton imbSMS, imbEMAIL, imbGhost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_dialog,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView textToast = (TextView) layout.findViewById(R.id.text_toast);
        textToast.setText(text);

        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();



        imbSMS=(ImageButton)findViewById(R.id.imbSMS);
        imbEMAIL=(ImageButton)findViewById(R.id.imbEMAIL);
        imbGhost=(ImageButton)findViewById(R.id.imbGhost);

        imbSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarSMS("84907258","El mensaje");
            }
        });

        imbEMAIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnviarEmail.class);
                startActivity(intent);
            }
        });

        imbGhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarGhostSms();
            }
        });
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enviarSMS(String tel, String sms){
        Uri uri = Uri.parse("smsto:"+tel);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", sms);
        startActivity(it);
    }

    public void enviarGhostSms(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("84907258",null,"Soy un fantasma :D",null,null);
        Toast.makeText(getApplicationContext(), "Boo! :D", Toast.LENGTH_SHORT).show();
    }
}
