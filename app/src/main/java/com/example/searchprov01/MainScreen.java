package com.example.searchprov01;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.searchprov01.databinding.ActivityMainScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityMainScreenBinding binding;
    private LineChart linechart;

    Spinner spinner;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        button = findViewById(R.id.button4);

        toInventory();
        spinnerFunctionailty();
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

    private void spinnerFunctionailty() {
        spinner = findViewById(R.id.time_intervals);
        List<String> timeIntervals = new ArrayList<>();
        timeIntervals.add(0, "1D");
        timeIntervals.add("1W");
        timeIntervals.add("1M");
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
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, Login_page.class));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}


