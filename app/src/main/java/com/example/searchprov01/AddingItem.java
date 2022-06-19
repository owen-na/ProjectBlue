package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddingItem extends InputChecker {

    Button extraButton;
    Button exitButton;

    EditText itemNameInput;
    EditText priceInput;
    EditText amountInStockInput;
    EditText lengthInput;

    List<String> itemNames = new ArrayList<>();
    List<Double> prices = new ArrayList<>();
    List<Integer> stockAmounts = new ArrayList<>();
    List<Integer> lengths = new ArrayList<>();

    ItemInfo item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        extraButton = findViewById(R.id.button6);
        exitButton = findViewById(R.id.button5);
        itemNameInput = findViewById(R.id.editTextTextPersonName);
        priceInput = findViewById(R.id.editTextNumberDecimal);
        amountInStockInput = findViewById(R.id.editTextNumber);
        lengthInput = findViewById(R.id.editTextTextPersonName2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_item);

        toExtra();
        exitOut();
    }

    private void toExtra() {
        extraButton.setOnClickListener(v -> {
            if (allValid()) {
                createCustomItem();
                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
            } else {
                createToast("All inputs are not filled");
            }
        });
    }

    private void allValid() {
        String itemName = stringify(itemNameInput);
        String price = stringify(priceInput);
        String stockAmount = stringify(amountInStockInput);
        String length = stringify(lengthInput);

        if (stringValid(itemName, "Item Name cannot be left bank") &&
                doubleValid(price, "Raw Price must be greater than 0", "Raw Price must be a number") &&
                intValid(stockAmount, "There must be at least one stock", "Stock Amount must be a whole number") &&
                intValid(length, "Length must be longer than 0 inches", "Length must be a whole number")) {
            itemNames.add(itemName);
            prices.add(Double.parseDouble(price));
            stockAmounts.add(Integer.parseInt(stockAmount));
            lengths.add(Integer.parseInt(lengths));
        }
    }

    private void exitOut() {
        exitButton.setOnClickListener(v -> startActivity(new Intent(AddingItem.this, inventoryView.class)));
    }

    public void createVisibleItem() {
        item = new ItemInfo(itemName[counter], price[counter], amountInStock[counter], length[counter]);
    }

}