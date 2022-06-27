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

import java.util.ArrayList;
import java.util.List;

public class AddingItem extends AppCompatActivity{

    Button extraButton;
    Button exitButton;

    EditText itemNameInput;
    EditText priceInput;
    EditText amountInStockInput;
    EditText lengthInput;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String itemName;
    double price;
    int amountInStock, length;

//    List<String> itemNames = new ArrayList<>();
//    List<Double> prices = new ArrayList<>();
//    List<Integer> stockAmounts = new ArrayList<>();
//    List<Integer> lengths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        extraButton = findViewById(R.id.button6);
        exitButton = findViewById(R.id.button5);

        itemNameInput = findViewById(R.id.editTextTextPersonName);
        priceInput = findViewById(R.id.editTextNumberDecimal);
        amountInStockInput = findViewById(R.id.editTextNumber);
        lengthInput = findViewById(R.id.editTextTextPersonName2);

        itemName = itemNameInput.getText().toString().trim();
        price = Double.parseDouble(priceInput.getText().toString().trim());
        amountInStock = Integer.parseInt(amountInStockInput.getText().toString().trim());
        length = Integer.parseInt(lengthInput.getText().toString().trim());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_item);

//        toExtra();
        exitOut();

        extraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setItemName();
                setPrice();
                setAmountInStock();
                setLength();
                createItem();
                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
            }
        });
    }

    private void setItemName() {
        if (itemName.isEmpty()) {
            itemNameInput.setError("Cannot blank");
        }
    }

    private void setPrice() {
        try {
            if (price <= 0.00) {
                priceInput.setError("Cannot be less than or equal to 0");
            }
        } catch (NumberFormatException nfe) {
            priceInput.setError("Invalid Input. Must be Numeric");
        }
    }

    private void setAmountInStock() {
        try {
            if (amountInStock <= 0) {
                amountInStockInput.setError("Cannot be less than or equal to 0");
            }
        } catch (NumberFormatException nfe) {
            amountInStockInput.setError("Invalid Input. Must be a Numeric Whole Number");
        }
    }

    private void setLength() {
        try {
            if (length <= 0) {
                lengthInput.setError("Cannot be less than or equal to 0");
            }
        } catch (NumberFormatException nfe) {
            lengthInput.setError("Invalid Input. Must be a Numeric Whole Number");
        }
    }

//    private void toExtra() {
//        extraButton.setOnClickListener(v -> {
//            if (allValid()) {
//                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
//            } else {
//                createToast("All inputs are not filled");
//            }
//        });
//    }

//    @Override
//    protected boolean allValid() {
//        String itemName = stringify(itemNameInput);
//        String price = stringify(priceInput);
//        String stockAmount = stringify(amountInStockInput);
//        String length = stringify(lengthInput);
//
//        if (stringValid(itemName, "Item Name cannot be left bank") &&
//                doubleValid(price, "Raw Price must be greater than 0", "Raw Price must be a number") &&
//                intValid(stockAmount, "There must be at least one stock", "Stock Amount must be a whole number") &&
//                intValid(length, "Length must be longer than 0 inches", "Length must be a whole number")) {
//            itemNames.add(itemName);
//            prices.add(Double.parseDouble(price));
//            stockAmounts.add(Integer.parseInt(stockAmount));
//            lengths.add(Integer.parseInt(length));
//            return true;
//        }
//        return false;
//    }

    private void createItem() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Item");
        ItemInfo item = new ItemInfo(itemName, price, amountInStock, length);

        reference.child("Item").child("Public-Values").setValue(item);
    }

    private void exitOut() {
        exitButton.setOnClickListener(v -> startActivity(new Intent(AddingItem.this, InventoryView.class)));
    }

}