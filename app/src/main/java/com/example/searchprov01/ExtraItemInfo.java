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

    Button button;

    JSONArray secretValues;

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

        button = findViewById(R.id.button7);

        idNumberInput = findViewById(R.id.editTextNumber4);
        weightInput = findViewById(R.id.editTextNumberDecimal2);
        thicknessInput = findViewById(R.id.editTextNumber2);
        profitInput = findViewById(R.id.editTextNumber3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item_info);

        toAddingItem();
        getIdNumber();
        getWeight();
        getThickness();
        getProfitRatio();
    }

    private void toAddingItem() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    makeJsonObject(idNumber, weight, thickness, profitRatio);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(ExtraItemInfo.this, AddingItem.class));
            }
        });
    }

    private void getIdNumber() {
        idNumberConverter = idNumberInput.getText().toString().trim();
        idNumber[lastCounter] = Integer.parseInt(idNumberConverter);
    }

    private void getWeight() {
        double weightChecker;
        weightConverter = weightInput.getText().toString().trim();
        weightChecker = Double.parseDouble(weightConverter);
        if (weightChecker <= 0.00) {
            Toast.makeText(ExtraItemInfo.this, "Invalid Weight, must be greater than 0.00", Toast.LENGTH_SHORT).show();
        } else {
            weight[lastCounter] = weightChecker;
        }
    }

    private void getThickness() {
        double thicknessChecker;
        thicknessConverter = thicknessInput.getText().toString().trim();
        thicknessChecker = Double.parseDouble(thicknessConverter);
        if (thicknessChecker <= 0.0) {
            Toast.makeText(ExtraItemInfo.this, "Invalid Thickness, must be greater than 0.0", Toast.LENGTH_SHORT).show();
        } else {
            thickness[lastCounter] = thicknessChecker;
        }
    }

    private void getProfitRatio() {
        double ratioChecker;
        profitConverter = profitInput.getText().toString().trim();
        ratioChecker = Double.parseDouble(profitConverter);
        if (ratioChecker <= 0.000) {
            Toast.makeText(ExtraItemInfo.this, "Invaild Profit Ratio, must be greater than 0.000", Toast.LENGTH_SHORT).show();
        } else {
            profitRatio[lastCounter] = ratioChecker;
        }
    }

    private void makeJsonObject(int[] idNumber, double[] weight, double[] thickness, double[] profitRatio) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("IDNumber", idNumber[lastCounter]);
            obj.put("Weight", weight[lastCounter]);
            obj.put("Thickness", thickness[lastCounter]);
            obj.put("ProfitRatio", profitRatio[lastCounter]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        secretValues.put(obj);
        try (FileWriter file = new FileWriter("SecretValues.json")) {
            file.write(secretValues.toString());
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}