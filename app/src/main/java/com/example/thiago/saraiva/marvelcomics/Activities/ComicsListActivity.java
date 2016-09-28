package com.example.thiago.saraiva.marvelcomics.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.thiago.saraiva.marvelcomics.R;

public class ComicsListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.comics_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter to the RecyclerView
        mAdapter = new ComicsListAdapter(myDataset, new MyAdapterClickListener() {

            @Override
            public void onButtonClick(View v, int position) {
                String orderSummary = myDataset.get(position);
                emailOrderSummary(orderSummary);
                removeListItem(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
