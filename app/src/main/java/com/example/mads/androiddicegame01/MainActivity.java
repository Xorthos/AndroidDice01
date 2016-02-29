package com.example.mads.androiddicegame01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import BLL.HistoryHelper;
import Models.BEHistory;

public class MainActivity extends AppCompatActivity {

    LinearLayout listLayout;
    Spinner diceAmount;
    Button btnRoll;
    Button btnHistory;
    HistoryHelper hh = HistoryHelper.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listLayout = (LinearLayout) findViewById(R.id.layoutList);
        btnRoll = (Button) findViewById(R.id.btnRoll);
        btnHistory = (Button) findViewById(R.id.btnHist);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll();
            }
        });
        initSpinner();

    }

    private void roll() {
        ArrayList<Integer> result = new ArrayList<>();
        Date rollTime = new Date();
        Random r = new Random();
        listLayout.removeAllViews();
        TextView stuff = new TextView(this);
        stuff.setText(Integer.toString(hh.getAll().size()) + "");
        listLayout.addView(stuff);

        for (int i = 0; i < (int) diceAmount.getSelectedItem(); i++) {
            int random = r.nextInt(6)+1;
            TextView view = new TextView(this);
            view.setText(Integer.toString(random) + "");
            result.add(random);
            listLayout.addView(view);
        }
        hh.add(new BEHistory(rollTime, result));
    }

    private void initSpinner() {
        diceAmount=(Spinner) findViewById(R.id.spnDiceAmount);
        Integer[] items = new Integer[]{1,2,3,4};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        diceAmount.setAdapter(adapter);
    }


}
