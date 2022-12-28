package com.example.searchprov01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InventoryView extends AppCompatActivity {

    private FirebaseRecyclerOptions<ItemInfo> options;
    private FirebaseRecyclerAdapter<ItemInfo, ItemAdapter> adapter;

    private RecyclerView recycleView;

    Button toAddItem;

    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        toAddItem = findViewById(R.id.AddItem);

        recycleView = findViewById(R.id.inventoryShow);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        toAddingItem();

        options = new FirebaseRecyclerOptions.Builder<ItemInfo>().setQuery(reference, ItemInfo.class).build();
        adapter = new FirebaseRecyclerAdapter<ItemInfo, ItemAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemAdapter holder, int position, @NonNull ItemInfo model) {
                // fix connection between adding item and the recycleview
            }

            @NonNull
            @Override
            public ItemAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
                return new ItemAdapter(v);
            }
        };
    }

    private void toAddingItem() {
        toAddItem.setOnClickListener(v -> startActivity(new Intent(InventoryView.this, AddingItem.class)));
    }


}
