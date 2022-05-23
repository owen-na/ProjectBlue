package com.example.searchprov01;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean progressToMain;
    boolean validTos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agreeToToS();
        toLoginScreen();
        checkEmail();
        checkPassword();
    }

    private void agreeToToS() {
        TextView hyperLink = (TextView) findViewById(R.id.textView9);
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

    private void checkEmail() {
        TextView error = findViewById(R.id.textView4);
        EditText emailBox = (EditText) findViewById(R.id.email);
        String email = emailBox.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Button btn = findViewById(R.id.button);
        System.out.println("Hi");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.matches(emailPattern)) {
                    error.setText("");
                } else {
                    error.setText("Invalid Email");
                }

            }
        });
    }

    private void checkPassword() {
        TextView error = (TextView) findViewById(R.id.textView5);
        EditText initialPassword = (EditText) findViewById(R.id.password);
        EditText finalPassword = (EditText) findViewById(R.id.confirmpassword);
        String firstPassword = initialPassword.getText().toString().trim();
        String secondPassword = finalPassword.getText().toString().trim();
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstPassword.equals(secondPassword)) {
                    error.setText("");
                } else {
                    error.setText("Passwords do not match.");
                }
            }
        });

    }

}




