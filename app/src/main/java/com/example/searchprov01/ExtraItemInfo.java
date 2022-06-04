package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExtraItemInfo extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        button = findViewById(R.id.button7);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_item_info);
        toAddingItem();
    }

    private void toAddingItem() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExtraItemInfo.this, AddingItem.class));
            }
        });
    }

    //  NEED JSON METHOD

}