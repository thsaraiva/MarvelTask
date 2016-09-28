package com.example.thiago.saraiva.marvelcomics.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.thiago.saraiva.marvelcomics.Adapters.ComicsListAdapter;
import com.example.thiago.saraiva.marvelcomics.Listeners.ComicsListItemClickListener;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Characters.MarvelCharacter;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Characters.MarvelCharacterDataContainer;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Characters.MarvelCharacterDataWrapper;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComic;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicDataContainer;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicDataWrapper;
import com.example.thiago.saraiva.marvelcomics.R;
import com.example.thiago.saraiva.marvelcomics.Services.MarvelAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicsListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ComicsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.comics_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter to the RecyclerView
        mAdapter = new ComicsListAdapter(new ComicsListItemClickListener() {

            @Override
            public void onButtonClick(View v, int position) {
                //TODO:decide what to do when user clicks an item on the list.
//                String item = myDataset.get(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        getMarvelComicsList(mAdapter);
    }

    public void getMarvelComicsList(final ComicsListAdapter adapter) {
        if (checkNetworkState()) {
            //TODO: esta funcionando o encryptador MD5?
//            final String hash = MD5Encryptor.crypt("1" + MarvelAPI.MARVEL_PRIVATE_KEY + MarvelAPI.MARVEL_PRIVATE_KEY);
            final String hash = "dd1dddde173297c2063adab02cd53403";
            OkHttpClient.Builder okHttp = new OkHttpClient.Builder();
            okHttp.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    HttpUrl originalHttpUrl = originalRequest.url();
                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("apikey", MarvelAPI.MARVEL_PUBLIC_KEY)
                            .addQueryParameter("hash", hash)
                            .addQueryParameter("ts", "1")
                            .build();

                    // Request customization: add request headers
                    Request newRequest = originalRequest.newBuilder()
                            .url(url).build();

                    return chain.proceed(newRequest);
                }
            });

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttp.addInterceptor(httpLoggingInterceptor);
            OkHttpClient httpClient = okHttp.build();

            Retrofit.Builder builder = new Retrofit.Builder();
            Retrofit retrofit = builder.client(httpClient).addConverterFactory(GsonConverterFactory.create()).baseUrl(MarvelAPI.MARVEL_BASE_URL).build();
            MarvelAPI marvelAPI = retrofit.create(MarvelAPI.class);
            Call<MarvelComicDataWrapper> marvelComicsListCall = marvelAPI.getComics("" + 20);
            marvelComicsListCall.enqueue(new Callback<MarvelComicDataWrapper>() {
                @Override
                public void onResponse(Call<MarvelComicDataWrapper> call, Response<MarvelComicDataWrapper> response) {
                    int code = response.code();
                    if (code == 200) {
                        MarvelComicDataWrapper cdw = response.body();
                        MarvelComicDataContainer dataContainer = cdw.getData();
                        ArrayList<MarvelComic> comicsList = new ArrayList(Arrays.asList(dataContainer.getResults()));
                        adapter.setmDataset(comicsList);
//                        Iterator<MarvelCharacter> iterator = charactersList.iterator();
//                        while (iterator.hasNext()) {
//                            MarvelCharacter mc = iterator.next();
//                            int id = mc.getId();
//                            String name = mc.getName();
//                            String description = mc.getDescription();
//                            MarvelThumbnail thumbnail = mc.getThumbnail();
//                            String msg = "Marvel Character: +" + name + ",\nId: " + id + ",\nDescription: " + description + ",\nThumbnail: " + thumbnail.getPath() + "." + thumbnail.getExtension();
//                            Log.d("MARVEL_API", msg);
//                        }
//                        Toast.makeText(ComicsListActivity.this, "Successfully received data. ", Toast.LENGTH_LONG).show();
                        Log.d("ComicsListActivity", "Request feito com sucesso!!");
                    } else {
                        Log.d("ComicsListActivity", "Request falhou!!");
                    }
                }

                @Override
                public void onFailure(Call<MarvelComicDataWrapper> call, Throwable t) {
                    Log.d("MARVEL_API", "Algo deu errado! onFailure: stacktrace: " + t.getLocalizedMessage());
                }
            });


        } else {
            Toast.makeText(ComicsListActivity.this, "No network available at the moment. Please turn on wi-fi or enable data network.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkNetworkState() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }
}
