package com.example.mads.androiddicegame01;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import BLL.HistoryHelper;
import Models.BEHistory;

/**
 * Created by mads on 2/29/16.
 */
public class HistoryActivity extends Activity {

    HistoryHelper hh = HistoryHelper.getInstance();
    HistoryAdapter ha;
    ListView lstHist;
    Button btnBack;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Awesome songs");
        setContentView(R.layout.activity_history);
        lstHist = (ListView) findViewById(R.id.lvHistory);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnClear = (Button) findViewById(R.id.btnClear);
        initListeners();

        ha = new HistoryAdapter(this, R.layout.cell_history, hh.getAll());
        lstHist.setAdapter(ha);
    }

    private void initListeners() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });
    }

    private void clearHistory() {
        hh.clearAll();
        ha  = new HistoryAdapter(this, R.layout.cell_history, hh.getAll());
        lstHist.setAdapter(ha);
    }
}

class HistoryAdapter extends ArrayAdapter<BEHistory> {

    ArrayList<BEHistory> history;

    private final int[] colours = {
            Color.parseColor("#AAAAAA"),
            Color.parseColor("#EEEEEE")
    };

    public HistoryAdapter(Context context, int textViewResourceId, ArrayList<BEHistory> history) {
        super(context, textViewResourceId, history);
        this.history = history;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.cell_history, null);
            Log.d("LIST", "Position: " + position + " View created");
        } else
            Log.d("LIST", "Position: " + position + " View Reused");


        v.setBackgroundColor(colours[position % colours.length]);

        BEHistory hist = history.get(position);
        ArrayList<Integer> result = hist.getResult();

        TextView date = (TextView) v.findViewById(R.id.txtDate);
        ImageView res1 = (ImageView) v.findViewById(R.id.imgResult1);
        ImageView res2 = (ImageView) v.findViewById(R.id.imgResult2);
        ImageView res3 = (ImageView) v.findViewById(R.id.imgResult3);
        ImageView res4 = (ImageView) v.findViewById(R.id.imgResult4);

        date.setText(hist.getTime().toString() + "");
        if(hist.getResult().size() > 0){
            res1.setImageResource(GetImageResource.getResorce(hist.getResult().get(0)));
        }
        if(hist.getResult().size() > 1){
            res2.setImageResource(GetImageResource.getResorce(hist.getResult().get(1)));
            //res2.setText(Integer.toString(hist.getResult().get(1)));
        }
        if(hist.getResult().size() > 2){
            res3.setImageResource(GetImageResource.getResorce(hist.getResult().get(2)));
            //res3.setText("3: " + Integer.toString(hist.getResult().get(2)));
        }
        if(hist.getResult().size() > 3){
            //res4.setText("4: " + Integer.toString(hist.getResult().get(3)));
            res4.setImageResource(GetImageResource.getResorce(hist.getResult().get(3)));
        }

        return v;

    }

}
