package com.example.searchprov01;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ItemBox extends AppCompatActivity {

    TextView replacingItemName, replacingPrice, replacingLength, replacingAmountInStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info);

        replacingItemName = findViewById(R.id.textView18);
        replacingPrice = findViewById(R.id.textView19);
        replacingLength = findViewById(R.id.textView21);
        replacingAmountInStock = findViewById(R.id.textView24);

        getData();
        addToScrollView();
    }

    private void getData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users/Items/Public Values");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String itemName = snapshot.child("itemName").getValue().toString();
                String price = Double.toString((Double) snapshot.child("price").getValue());
                int length = Integer.parseInt(snapshot.child("length").getValue().toString());
                int amountInStock = Integer.parseInt(snapshot.child("amountInStock").getValue().toString());

                replacingItemName.setText(itemName);
                replacingPrice.setText(price);
                replacingLength.setText(length);
                replacingAmountInStock.setText(amountInStock);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addToScrollView() {

    }

}
