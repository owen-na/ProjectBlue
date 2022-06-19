package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class ExtraItemInfo extends AppCompatActivity {

    Button submitButton;

    boolean verification = false;

    int lastCounter;

    int[] idNumber = new int[50];
    double[] weight = new double[50];
    double[] thickness = new double[50];
    double[] profitRatio = new double[50];

    EditText idNumberInput;
    EditText weightInput;
    EditText thicknessInput;
    EditText profitInput;

    String idNumberConverter;
    String weightConverter;
    String thicknessConverter;
    String profitConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        submitButton = findViewById(R.id.button7);

        idNumberInput = findViewById(R.id.editTextNumber4);
        weightInput = findViewById(R.id.editTextNumberDecimal2);
        thicknessInput = findViewById(R.id.editTextNumber2);
        profitInput = findViewById(R.id.editTextNumber3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item_info);

        checkVerification();
        getIdNumber();
        getWeight();
        getThickness();
        getProfitRatio();
    }

    private void toAddingItem() {
        submitButton.setOnClickListener(v -> {
            toInventory();
        });
    }


    private void getIdNumber() {
        idNumberConverter = idNumberInput.getText().toString().trim();
        if (idNumberConverter.isEmpty()) {
            Toast.makeText(ExtraItemInfo.this, "Invalid ID, cannot be blank", Toast.LENGTH_SHORT).show();
            verification = false;
        } else {
            idNumber[lastCounter] = Integer.parseInt(idNumberConverter);
            verification = true;
        }
    }

    private void getWeight() {
        double weightChecker;
        weightConverter = weightInput.getText().toString().trim();
        weightChecker = Double.parseDouble(weightConverter);
        if (weightChecker <= 0.00) {
            Toast.makeText(ExtraItemInfo.this, "Invalid Weight, must be greater than 0.00", Toast.LENGTH_SHORT).show();
            verification = false;
        } else {
            weight[lastCounter] = weightChecker;
            verification = true;
        }
    }

    private void getThickness() {
        double thicknessChecker;
        thicknessConverter = thicknessInput.getText().toString().trim();
        thicknessChecker = Double.parseDouble(thicknessConverter);
        if (thicknessChecker <= 0.0) {
            Toast.makeText(ExtraItemInfo.this, "Invalid Thickness, must be greater than 0.0", Toast.LENGTH_SHORT).show();
            verification = false;
        } else {
            thickness[lastCounter] = thicknessChecker;
            verification = true;
        }
    }

    private void getProfitRatio() {
        double ratioChecker;
        profitConverter = profitInput.getText().toString().trim();
        ratioChecker = Double.parseDouble(profitConverter);
        if (ratioChecker <= 0.000) {
            Toast.makeText(ExtraItemInfo.this, "Invaild Profit Ratio, must be greater than 0.000", Toast.LENGTH_SHORT).show();
            verification = false;
        } else {
            profitRatio[lastCounter] = ratioChecker;
            verification = true;
        }
    }

    private void checkVerification () {
        if (verification) {
            toAddingItem();
            createCustomItem();
            lastCounter++;
        }
    }

    private void toInventory() {
        startActivity(new Intent(ExtraItemInfo.this, inventoryView.class));
    }

    private void createCustomItem() {
        PrivateInfo itemSecret = new PrivateInfo(idNumber[lastCounter], weight[lastCounter], thickness[lastCounter], profitRatio[lastCounter]);

    }

    // 2 methods, one to store into the custom component, another to add it into the scrollView.

}