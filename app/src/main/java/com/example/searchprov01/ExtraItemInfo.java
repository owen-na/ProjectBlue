package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        weightConverter = weightInput.getText().toString().trim();
        weight[lastCounter] = Integer.parseInt(weightConverter);
    }

    private void getThickness() {
        thicknessConverter = thicknessInput.getText().toString().trim();
        thickness[lastCounter] = Integer.parseInt(thicknessConverter);
    }

    private void getProfitRatio() {
        profitConverter = profitInput.getText().toString().trim();
        profitRatio[lastCounter] = Integer.parseInt(profitConverter);
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