package com.example.searchprov01;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class ItemBox extends AppCompatActivity {

    TextView replacingItemName, replacingPrice, replacingLength, replacingAmountInStock;

    int serialNumber;

    private FirebaseRecyclerOptions<ItemInfo> options;
    private FirebaseRecyclerAdapter<ItemInfo, ItemAdapter> adapter;

    private ItemAdapter recycleView;

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
        serialNumber++;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("Items").child(String.valueOf(serialNumber)).child("Public Values");
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
