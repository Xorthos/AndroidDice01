package com.example.mads.androiddicegame01;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import BLL.HistoryHelper;
import Models.BEHistory;

public class MainActivity extends AppCompatActivity {

    LinearLayout listLayout;
    NumberPicker diceAmount;
    Button btnRoll;
    Button btnHistory;
    HistoryHelper hh = HistoryHelper.getInstance();
    int SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        listLayout = (LinearLayout) findViewById(R.id.layoutList);
        findViewById(R.id.activityLayout).setBackgroundResource(R.drawable.table);
        btnRoll = (Button) findViewById(R.id.btnRoll);
        btnHistory = (Button) findViewById(R.id.btnHist);
        initListeners();
        initNumberPicker();

    }

    private void roll() {
        ArrayList<Integer> result = new ArrayList<>();
        Date rollTime = new Date();
        Random r = new Random();
        listLayout.removeAllViews();

        for (int i = 0; i < (int) diceAmount.getValue(); i++) {
            int random = r.nextInt(6)+1;
            //TextView view = new TextView(this);
            //view.setText(Integer.toString(random) + "");
            result.add(random);
            ImageView iv= new ImageView(this);
            iv.setImageResource(GetImageResource.getResorce(random));
            iv.setMaxHeight(200);
            iv.setAdjustViewBounds(true);
            listLayout.addView(iv);
        }
        hh.add(new BEHistory(rollTime, result));

    }

    private void initListeners() {
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHistoryActivity();
            }
        });
    }

    private void initNumberPicker() {
        diceAmount=(NumberPicker) findViewById(R.id.npDiceAmount);
        diceAmount.setMinValue(1);
        diceAmount.setMaxValue(6);
        diceAmount.setWrapSelectorWheel(false);
    }

    private void startHistoryActivity() {
        Intent intent = new Intent();
        intent.setClass(this, HistoryActivity.class);

        startActivity(intent);
    }
}
