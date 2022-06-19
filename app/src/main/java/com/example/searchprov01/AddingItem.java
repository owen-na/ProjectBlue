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

import java.util.ArrayList;
import java.util.List;

public class AddingItem extends AppCompatActivity {

    Button extraButton;
    Button exitButton;
    // int counter = 0;
    // boolean globalVerification;
    // String priceConverter;
    // String stockConverter;
    // String lengthConverter;

    EditText itemNameInput;
    EditText priceInput;
    EditText amountInStockInput;
    EditText lengthInput;

    // String[] itemName = new String[50];
    // double[] price = new double[50];
    // int[] amountInStock = new int[50];
    // int[] length = new int[50];

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
        // checkGlobalVerification();
        // getItemName();
        // getRawPrice();
        // getStockAmount();
        // getLength();
        exitOut();
        toExtra();
    }

    private void toExtra() {
        extraButton.setOnClickListener(v -> {
            if (allValid()) {
                createCustomItem();
                toExtraItemInfo();
            } else {
                Toast.makeText(AddingItem.this, "All inputs are not filled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toExtraItemInfo() {
        startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
    }

    private String toString(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void allValid() {
        String itemName = toString(itemNameInput);
        String price = toString(priceInput);
        String stockAmount = toString(amountInStockInput);
        String length = toString(lengthInput);

        if (itemNameValid(itemName) && priceValid(price) && stockAmountValid(stockAmount) && lengthValid(length)) {
            itemNames.add(itemName);
            prices.add(Double.parseDouble(price));
            stockAmounts.add(Integer.parseInt(stockAmount));
            lengths.add(Integer.parseInt(lengths));
        }
    }

    private <T extends String> boolean isValid(T name, String error) {
        boolean isEmpty = name.isEmpty();
        if (isEmpty) {
            Toast.makeText(AddingItem.this, "Invalid Name, cannot be left blank", Toast.LENGTH_SHORT).show();
       }
        return isEmpty;
    }

    private boolean itemNameValid(String itemName) {
        boolean isEmpty = itemName.isEmpty();
        if (isEmpty) {
            Toast.makeText(AddingItem.this, "Invalid Name, cannot be left blank", Toast.LENGTH_SHORT).show();
       }
        return isEmpty;
    }

    private boolean priceValid(String priceStr) {
        try {
            double price = Double.parseDouble(priceStr);
            if (price <= 0) {
                Toast.makeText(AddingItem.this, "Raw Price must be greater than or equal to 0.00", Toast.LENGTH_SHORT).show();
            }
            return price > 0;
        } catch (NumberFormatException e) {
            Toast.makeText(AddingItem.this, "Raw Price is not a number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean stockAmountValid(String stockAmountStr) {
        try {
            int stockAmount = Integer.parseInt(stockAmountStr);
            if (stockAmount <= 0) {
                Toast.makeText(AddingItem.this, "There must be more than one in stock", Toast.LENGTH_SHORT).show();
            }
            return stockAmount > 0;
        } catch (NumberFormatException e) {
            Toast.makeText(AddingItem.this, "Stock Amount is not a number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean lengthValid(String lengthStr) {
        try {
            int length = Integer.parseInt(lengthStr);
            if (length <= 0) {
                Toast.makeText(AddingItem.this, "Invalid Length, must be longer than 0 inches", Toast.LENGTH_SHORT).show();
            }
            return length > 0;
        } catch (NumberFormatException e) {
            Toast.makeText(AddingItem.this, "Length is not a number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // private void getItemName() {
    //     String nameChecker;
    //     nameChecker = itemNameInput.getText().toString().trim();
    //     if (nameChecker.isEmpty()) {
    //         Toast.makeText(AddingItem.this, "Invalid Name, cannot be left blank", Toast.LENGTH_SHORT).show();
    //         globalVerification = false;
    //     } else {
    //         itemName[counter] = nameChecker;
    //         globalVerification = true;
    //     }
    // }

    // private void getRawPrice() {
    //     double priceChecker;
    //     priceConverter = priceInput.getText().toString().trim();
    //     priceChecker = Double.parseDouble(priceConverter);
    //     if (priceChecker <= 0) {
    //         Toast.makeText(AddingItem.this, "Raw Price must be greater than or equal to 0.00", Toast.LENGTH_SHORT).show();
    //         globalVerification = false;
    //     } else {
    //         price[counter] = priceChecker;
    //         globalVerification = true;
    //     }
    // }

    // private void getStockAmount() {
    //     int stockChecker;
    //     stockConverter = amountInStockInput.getText().toString().trim();
    //     stockChecker = Integer.parseInt(stockConverter);
    //     if (stockChecker <= 0) {
    //         Toast.makeText(AddingItem.this, "There must be more than one in stock", Toast.LENGTH_SHORT).show();
    //         globalVerification = false;
    //     } else {
    //         amountInStock[counter] = stockChecker;
    //         globalVerification = true;
    //     }
    // }

    // private void getLength() {
    //     int lengthChecker;
    //     lengthConverter = lengthInput.getText().toString().trim();
    //     lengthChecker = Integer.parseInt(lengthConverter);
    //     if (lengthChecker <= 0) {
    //         Toast.makeText(AddingItem.this, "Invalid Length, must be longer than 0 inches", Toast.LENGTH_SHORT).show();
    //         globalVerification = false;
    //     } else {
    //         length[counter] = lengthChecker;
    //         globalVerification = true;
    //     }
    // }

    // private void checkGlobalVerification() {
    //     if (globalVerification) {
    //         toExtra();
    //         createVisibleItem();
    //         counter++;
    //     }
    // }

    private void exitOut() {
        exitButton.setOnClickListener(v -> startActivity(new Intent(AddingItem.this, inventoryView.class)));
    }

    public void createVisibleItem() {
        item = new ItemInfo(itemName[counter], price[counter], amountInStock[counter], length[counter]);
    }

}