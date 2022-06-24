package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

public class ExtraItemInfo extends InputChecker {

    Button submitButton;

    List<Integer> ids = new ArrayList<>();
    List<Double> weights = new ArrayList<>();
    List<Double> thicknesses = new ArrayList<>();
    List<Double> profitRatios = new ArrayList<>();

    EditText idNumberInput;
    EditText weightInput;
    EditText thicknessInput;
    EditText profitInput;
    EditText lengthInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        submitButton = findViewById(R.id.button7);

        idNumberInput = findViewById(R.id.editTextNumber4);
        weightInput = findViewById(R.id.editTextNumberDecimal2);
        thicknessInput = findViewById(R.id.editTextNumber2);
        profitInput = findViewById(R.id.editTextNumber3);
        lengthInput = findViewById(R.id.editTextTextPersonName2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item_info);

        toInventory();
    }

    private void toAddingItem() {
        submitButton.setOnClickListener(v -> {
            toInventory();
        });
    }

    private void toInventory() {
        submitButton.setOnClickListener(v -> {
            if (allValid()) {
                createCustomItem();
                startActivity(new Intent(ExtraItemInfo.this, InventoryView.class));
            } else {
                createToast("All inputs are not filled");
            }
        });
    }

    @Override
    protected boolean allValid() {
        String id = stringify(idNumberInput);
        String weight = stringify(weightInput);
        String thickness = stringify(thicknessInput);
        String profitRatio = stringify(profitInput);

        if (intValid(id, "ID cannot be blank", "ID must be a whole number") &&
                doubleValid(weight, "Weight must be greater than 0", "Weight must be a number") &&
                doubleValid(thickness, "Thickness must be greater than 0", "Thickness must be a number") &&
                doubleValid(profitRatio, "Profit Ratio must be greater than 0", "Profit Ratio must be a number")) {
            ids.add(Integer.parseInt(id));
            weights.add(Double.parseDouble(weight));
            thicknesses.add(Double.parseDouble(thickness));
            profitRatios.add(Double.parseDouble(profitRatio));
            return true;
        }
        return false;
    }

    private void createCustomItem() {
        View inventory, item;
        item = getLayoutInflater().inflate(R.layout.item_info, null);
        inventory = findViewById(R.id.inventoryShow);
        inventory.addView(item);
        // View customComponent = itembox.inflate(R.layout.item_info, null);
        // PrivateInfo itemSecret = new PrivateInfo(idNumber[lastCounter], weight[lastCounter], thickness[lastCounter], profitRatio[lastCounter]);

    }

    // 2 methods, one to store into the custom component, another to add it into the
    // scrollView.

}