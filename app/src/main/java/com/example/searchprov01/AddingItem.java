package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddingItem extends AppCompatActivity {

    Button button;
    Button button2;
    public int counter = 0;

    EditText itemNameInput;
    EditText priceInput;
    EditText amountInStockInput;
    EditText lengthInput;

    String[] itemName = new String[50];
    int[] price = new int[50];
    int[] amountInStock = new int[50];
    int[] length = new int[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        button = findViewById(R.id.button6);
        button2 = findViewById(R.id.button5);
        itemNameInput = findViewById(R.id.editTextTextPersonName);
        priceInput = findViewById(R.id.editTextNumberDecimal);
        amountInStockInput = findViewById(R.id.editTextNumber);
        lengthInput = findViewById(R.id.editTextTextPersonName2);

        itemName[counter] = itemNameInput.getText().toString().trim();
        // ?? price[counter] = priceInput.getText().toString().trim();
        // ?? amountInStock[counter] = amountInStockInput.getText().toString().trim();
        // ?? length[counter] = lengthInput.getText().toString().trim();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_item);
        toExtra();
        submitItem();
    }

    private void toExtra() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
            }
        });
    }

    private void submitItem() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingItem.this, inventoryView.class));
                makeJsonObject(itemName, price, amountInStock, length);
            }
        });
    }

    public void makeJsonObject(String[] itemName, int[] price, int[] amountInStock, int[] length) {

    }
    // NEED JSON METHOD

}