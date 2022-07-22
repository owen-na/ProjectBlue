package com.example.searchprov01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                // createItem();
                startActivity(new Intent(AddingItem.this, ExtraItemInfo.class));
                addItem();
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

    private void addItem() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users").child("Items").child("Public Values");
        ItemInfo itemInfo = new ItemInfo(itemName, price, amountInStock, length);
        reference.setValue(itemInfo);
    }

//    private void createItem() {
//        rootNode = FirebaseDatabase.getInstance();
//        reference = rootNode.getReference(ItemInfo.class.getSimpleName());
//        ItemInfo item = new ItemInfo(itemName, price, amountInStock, length);

//        reference.child("Item").child("Public-Values").setValue(item);
//
//        reference.child("Item").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//
//            }
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                item = snapshot.getValue(ItemInfo.class);
//                  showData(dataSnapshot, item);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

//    public Task<Void> add(ItemInfo item) {
//        if (item == null) {
//            reference.push().setValue(item);
//        }
//        return;
//    }

//    private void showData(DataSnapshot dataSnapshot, ItemInfo item) {
//        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//            item = ds.getValue(ItemInfo.class);
//        }
//    }

    private void exitOut() {
        exitButton.setOnClickListener(v -> startActivity(new Intent(AddingItem.this, InventoryView.class)));
    }

}