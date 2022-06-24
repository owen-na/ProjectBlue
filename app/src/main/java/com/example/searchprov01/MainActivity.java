package com.example.searchprov01;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    boolean progressToMain = false;
    boolean emailVerification = false;
    boolean passwordVerification = false;
    boolean termsOfServiceVerification = false;
    int[] userId = new int[50];
    String[] userEmail = new String[50];
    String[] userPassword = new String[50];
    int uniqueUserCount = 0;


    String emailInput;
    String passwordInput;
    private FirebaseAuth mAuth;

    Button signUp;
    EditText email,password,confirmPassword;
    TextView emailError,passwordError,confirmPassError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agreeToToS();
        toLoginScreen();

        mAuth = FirebaseAuth.getInstance();

        signUp=findViewById(R.id.button);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmpassword);

        emailInput = email.getText().toString().trim();
        passwordInput = password.getText().toString().trim();

        //sends alert to user if they have an invalid input
        emailError=findViewById(R.id.textView4);
        passwordError=findViewById(R.id.textView14);
        confirmPassError=findViewById(R.id.textView5);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();
                validatePassword();
                checkToS();
                verificationCheck();
                createUser(emailInput, passwordInput);
            }
        });

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
            finish();
        }
    }




    /**
     * Takes the input from the user in the email textbox and turns it into a string.
     * Sends error if the textfield is empty or if the email is not valid.
     * @return
     */
    private void validateEmail() {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            emailError.setText("Field can't be empty");
            return;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailError.setText("Invalid Email");
            return;
        } else {
            emailError.setText(""); // emailError.setError(null)
            // emailVerification = true;
            // userEmail[uniqueUserCount] = emailInput;
            return;
        }
    }


    /**
     * Takes the input of the user and turns it into a string.
     * Sends error if textfield is empty,if the password is less than 5 characters,
     * or if the passwords do not match.
     * @return
     */
    private void validatePassword() {
        String passwordInput = password.getText().toString().trim();
        String ConfitmpasswordInput = confirmPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwordError.setText("Field can't be empty");
            return;
        }  if (passwordInput.length()<5) {
            passwordError.setText("Password must be at least 5 characters");
            return;
        }
        if (!passwordInput.equals(ConfitmpasswordInput)) {
            confirmPassError.setText("Passwords do not match");
            return;
        } else {
            confirmPassError.setText("");
            passwordError.setText("");
            userPassword[uniqueUserCount] = ConfitmpasswordInput;
            passwordVerification = true;
            return;
        }
    }

    private void checkToS() {
        CheckBox check = findViewById(R.id.checkBox);
        if (check.isChecked()) {
            termsOfServiceVerification = false;
        } else {
            termsOfServiceVerification = true;
        }
        // check.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         if (check.isChecked()) {
        //             termsOfServiceVerification = false;
        //         } else {
        //             termsOfServiceVerification = true;
        //         }
        //     }
        // });
    }

    private void createUser(String email, String confirmPassword) {
        mAuth.createUserWithEmailAndPassword(email, confirmPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), MainScreen.class));
                } else {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void verificationCheck() {
        if (emailVerification == true && passwordVerification == true && termsOfServiceVerification == true) {
            progressToMain = true;
            userId[uniqueUserCount] = uniqueUserCount;
            uniqueUserCount++;
            // makeJsonObject(userId, userEmail, userPassword, uniqueUserCount);
        }
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


    // public void makeJsonObject(int[] userID, String[] email, String[] realPassword, int uniqueUsers) {
    //    JSONArray jsonArray = new JSONArray();
    //    JSONObject obj = new JSONObject();
    //        try {
    //            obj.put("userId", userID[uniqueUsers]);
    //            obj.put("email", email[uniqueUsers]);
    //            obj.put("realPassword", realPassword[uniqueUsers]);
    //        } catch (JSONException je) {
    //            je.printStackTrace();
    //        }
    //        jsonArray.put(obj);
    //    try (FileWriter file = new FileWriter("infoJson.json")) {
    //        file.write(jsonArray.toString());
    //   } catch (IOException ie) {
    //        ie.printStackTrace();
    //    }
    // }

}




