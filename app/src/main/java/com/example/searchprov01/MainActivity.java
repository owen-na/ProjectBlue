package com.example.searchprov01;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agreeToToS();
        toLoginScreen();
    }

        private void agreeToToS () {
            TextView hyperLink = (TextView) findViewById(R.id.checkBox);
            hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
            hyperLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, Pop.class));
                }
            });
        }

        private void toLoginScreen() {
        TextView hyperLink = (TextView) findViewById(R.id.textView2);
        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
            hyperLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, Login_page.class));
                }
            });
        }
}



