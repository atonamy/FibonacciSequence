package com.viki.fibonaccilist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FibonacciActivity extends AppCompatActivity {

    private static final int PAGE_LENGTH = 20;
    private ListView fibonacciList;
    private ArrayAdapter<String> currentAdapter;
    private boolean updated;
    private BigInteger prev1;
    private BigInteger prev2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        fibonacciList = (ListView)findViewById(R.id.listViewFibonacci);

        updated = false;
        currentAdapter = null;
        prev1 = BigInteger.ZERO;
        prev2 = BigInteger.ONE;

        populateFibonacci();
        fibonacciList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) >= totalItemCount && !updated) {
                    updated = true;
                    populateFibonacci();
                }
            }
        });

    }

    public void populateFibonacci() {

        if(currentAdapter == null) {
            List<String> result = new ArrayList<>();
            result.add("0");
            result.add("1");
            currentAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, result);

            fibonacciList.setAdapter(currentAdapter);
        }

        for(int i = 0; i < PAGE_LENGTH; i++) {
            BigInteger savePrev1 = prev1;
            prev1 = prev2;
            prev2 = savePrev1.add(prev2);
            currentAdapter.add(prev2.toString());
        }

        updated = false;
    }

}
