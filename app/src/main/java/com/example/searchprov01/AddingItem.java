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

public class AddingItem extends AppCompatActivity {

    Button button;
    Button button2;
    int counter;
    String priceConverter;
    String stockConverter;
    String lengthConverter;

    EditText itemNameInput;
    EditText priceInput;
    EditText amountInStockInput;
    EditText lengthInput;

    String[] itemName = new String[50];
    int[] price = new int[50];
    int[] amountInStock = new int[50];
    int[] length = new int[50];

    JSONArray inventory = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        button = findViewById(R.id.button6);
        button2 = findViewById(R.id.button5);
        itemNameInput = findViewById(R.id.editTextTextPersonName);
        priceInput = findViewById(R.id.editTextNumberDecimal);
        amountInStockInput = findViewById(R.id.editTextNumber);
        lengthInput = findViewById(R.id.editTextTextPersonName2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_item);
        toExtra();
        getItemName();
        getRawPrice();
        getStockAmount();
        getLength();
        exitOut();
    }

    private void toExtra() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
                makeJsonObject(itemName, price, amountInStock, length);
            }
        });
    }

    private void getItemName() {
        itemName[counter] = itemNameInput.getText().toString().trim();
    }

    private void getRawPrice() {
        priceConverter = priceInput.getText().toString().trim();
        price[counter] = Integer.parseInt(priceConverter);
    }

    private void getStockAmount() {
        stockConverter = amountInStockInput.getText().toString().trim();
        amountInStock[counter] = Integer.parseInt(stockConverter);
    }

    private void getLength() {
        lengthConverter = lengthInput.getText().toString().trim();
        length[counter] = Integer.parseInt(lengthConverter);
    }

    private void exitOut() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddingItem.this, inventoryView.class));
            }
        });
    }

    public void makeJsonObject(String[] itemName, int[] price, int[] amountInStock, int[] length) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("itemName", itemName[counter]);
            obj.put("price", price[counter]);
            obj.put("amountInStock", amountInStock[counter]);
            obj.put("length", length[counter]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        inventory.put(obj);
        counter++;
        try (FileWriter file = new FileWriter("inventoryJson.json")) {
            file.write(inventory.toString());
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}