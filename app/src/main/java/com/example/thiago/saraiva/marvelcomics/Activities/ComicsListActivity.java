package com.example.thiago.saraiva.marvelcomics.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thiago.saraiva.marvelcomics.Adapters.ComicsListAdapter;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicDataContainer;
import com.example.thiago.saraiva.marvelcomics.R;
import com.example.thiago.saraiva.marvelcomics.Services.ServiceGenerator;

public class ComicsListActivity extends AppCompatActivity implements ComicListActivityDataSender {

    private RecyclerView mRecyclerView;
    private ComicsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ServiceGenerator serviceGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comics_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.comics_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter to the RecyclerView
        mAdapter = new ComicsListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        serviceGenerator = new ServiceGenerator();
        getMarvelComicsList();
    }

    public void getMarvelComicsList() {
        if (!checkNetworkState()) {
            Toast.makeText(ComicsListActivity.this, "No network available at the moment. Please turn on wi-fi or enable data network.", Toast.LENGTH_LONG).show();
        } else {
            serviceGenerator.getMarvelComicsList(mAdapter);
        }
    }

    public boolean checkNetworkState() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }

    public void filterResultsOnBudget(View view) {
        String budgetValue = ((EditText) findViewById(R.id.budget)).getText().toString();
        if (budgetValue == null || budgetValue.equalsIgnoreCase("")) {
            Toast.makeText(this, getResources().getString(R.string.please_provide_budget), Toast.LENGTH_LONG).show();
        } else {
            mAdapter.filterComicListOnBudget(budgetValue);
        }
    }

    @Override
    public void updateResultsLabel(MarvelComicDataContainer dataContainer) {
        TextView resultLabel = (TextView) findViewById(R.id.results_label);
        String listPrice = dataContainer.getListPrice();
        if (listPrice.equalsIgnoreCase(null) || listPrice.equalsIgnoreCase("")) {
            resultLabel.setText(getResources().getString(R.string.results_no_filter, dataContainer.getResultsInList()));
        } else {
            resultLabel.setText(getResources().getString(R.string.results_budget_filtered, dataContainer.getResultsInList(), listPrice));
        }
        resultLabel.setVisibility(View.VISIBLE);

        TextView totalPagesLabel = (TextView) findViewById(R.id.total_pages_label);
        totalPagesLabel.setText(getResources().getString(R.string.total_pages_default_value, dataContainer.getTotalNumberOfPagesInList()));
        totalPagesLabel.setVisibility(View.VISIBLE);
    }

}
