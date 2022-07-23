package com.example.searchprov01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;

import androidx.appcompat.app.AppCompatActivity;

import com.example.searchprov01.databinding.ActivityMainScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityMainScreenBinding binding;
    private LineChart linechart;

    Spinner spinner;
    Button button, logOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        button = findViewById(R.id.button4);
        logOutButton = findViewById(R.id.LogOutButton);

        toInventory();
        spinnerFunctionality();
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        linechart = findViewById(R.id.profit_chart);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user =  mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainScreen.this, Login_page.class));
            finish();
            return;
        }

        toLoginPage();

        // The comboBox (known as a Spinner)

        // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.time_amounts, android.R.layout.simple_spinner_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("1D")) {
                } else {
                    String option = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // linechart.setOnChartGestureListener(MainScreen.this);
        // linechart.setOnChartValueSelectedListener(MainScreen.this);

        linechart.setDragEnabled(true);
        linechart.setScaleEnabled(false);
    }

    private void toInventory() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, InventoryView.class));
            }
        });
    }

    private void spinnerFunctionality() {
        spinner = findViewById(R.id.time_intervals);
        String timeIntervals[] = {"1D" , "1W" , "1M"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timeIntervals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("1D")) {
                } else {
                    String option = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void toLoginPage() {
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainScreen.this, Login_page.class));
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}


