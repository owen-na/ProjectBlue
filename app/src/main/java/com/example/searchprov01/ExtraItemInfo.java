package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class ExtraItemInfo extends AppCompatActivity {

    Button button;

    boolean verification = false;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    int idNumber;
    double weight, thickness, profitRatio;

    EditText idNumberInput;
    EditText weightInput;
    EditText thicknessInput;
    EditText profitInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        button = findViewById(R.id.button7);

        idNumberInput = findViewById(R.id.editTextNumber4);
        weightInput = findViewById(R.id.editTextNumberDecimal2);
        thicknessInput = findViewById(R.id.editTextNumber2);
        profitInput = findViewById(R.id.editTextNumber3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item_info);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIdNumber();
                setWeight();
                setThickness();
                setProfitRatio();
                finailizeCreation();
                startActivity(new Intent(ExtraItemInfo.this, InventoryView.class));
            }
        });
    }


    private void setIdNumber() {
        try {
            idNumber = Integer.parseInt(idNumberInput.getText().toString().trim());
        } catch (NumberFormatException nfe) {
            idNumberInput.setError("Invalid Input. Must be a Numeric Whole Number");
        }
    }

    private void setWeight() {
        try {
            weight = Double.parseDouble(weightInput.getText().toString().trim());
            if (weight <= 0.00) {
                weightInput.setError("Cannot be less than or equal to 0.00");
            }
        } catch (NumberFormatException nfe) {
            weightInput.setError("Invalid Input. Must be a Numeric Number");
        }
    }

    private void setThickness() {
        try {
            thickness = Double.parseDouble(thicknessInput.getText().toString().trim());
            if (thickness <= 0.00) {
                thicknessInput.setError("Cannot be less than or equal to 0.00");
            }
        } catch (NumberFormatException nfe) {
            thicknessInput.setError("Invalid Input. Must be a Numeric Number");
        }
    }

    private void setProfitRatio() {
        try {
            profitRatio = Double.parseDouble(profitInput.getText().toString().trim());
            if (profitRatio <= 0.00) {
                profitInput.setError("Cannot be less or equal of 0.00");
            }
        } catch (NumberFormatException nfe) {
            profitInput.setError("Invalid Input. Must be a Numeric Number");
        }

    }


//    private void makeJsonObject(int[] idNumber, double[] weight, double[] thickness, double[] profitRatio) {
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("iDNumber", idNumber[lastCounter]);
//            obj.put("weight", weight[lastCounter]);
//            obj.put("thickness", thickness[lastCounter]);
//            obj.put("profitRatio", profitRatio[lastCounter]);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        secretValues.put(obj);
//        try (FileWriter file = new FileWriter("SecretValues.json")) {
//            file.write(secretValues.toString());
//        } catch (IOException ie) {
//            ie.printStackTrace();
//        }
//    }

    private void finailizeCreation() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Item");

        PrivateInfo item = new PrivateInfo(idNumber, weight, thickness, profitRatio);

        reference.child("testHolder").setValue(item);
    }

    private void toInventory() {
        startActivity(new Intent(ExtraItemInfo.this, InventoryView.class));
    }

}