package com.example.thiago.saraiva.marvelcomics.Services;

import android.util.Log;

import com.example.thiago.saraiva.marvelcomics.Adapters.ComicsListAdapter;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComic;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicDataContainer;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicDataWrapper;

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

/**
 * Created by thsaraiva on 28/09/2016.
 */
public class ServiceGenerator {

    final String MARVEL_BASE_URL = "https://gateway.marvel.com:443";
    //    TODO: colocar chaves publicas em um arquivo de recurso
    final String MARVEL_PUBLIC_KEY = "c5eea1883b9cf528794d17658686df6a";
    final String MARVEL_PRIVATE_KEY = "379872e536a35257df69f89269b3daaeae776e48";
    private final String hash = "dd1dddde173297c2063adab02cd53403";
    final String TIMESTAMP = "1";

    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;

    public ServiceGenerator() {
        httpClientBuilder = new OkHttpClient.Builder();
        //generates an interceptor responsible for including authentication parameters in every request
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();
                //TODO: esta funcionando o encryptador MD5?
//            final String hash = MD5Encryptor.crypt("1" + MarvelAPI.MARVEL_PRIVATE_KEY + MarvelAPI.MARVEL_PRIVATE_KEY);
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", MARVEL_PUBLIC_KEY)
                        .addQueryParameter("hash", hash)
                        .addQueryParameter("ts", "1")
                        .build();
                Request newRequest = originalRequest.newBuilder()
                        .url(url).build();
                return chain.proceed(newRequest);
            }
        });

        //Add interceptor responsible for logging network communication for debug purposes
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient OkHttpClient = httpClientBuilder.build();

        retrofit = new Retrofit.Builder().client(OkHttpClient).addConverterFactory(GsonConverterFactory.create()).baseUrl(MARVEL_BASE_URL).build();

    }

    public void getMarvelComicsList(final ComicsListAdapter adapter) {
        MarvelAPI marvelAPI = retrofit.create(MarvelAPI.class);
        Call<MarvelComicDataWrapper> marvelComicsListCall = marvelAPI.getComics("" + 20);

        //makes an asynchronous call to obtain the list of comics.
        marvelComicsListCall.enqueue(new Callback<MarvelComicDataWrapper>() {
            @Override
            public void onResponse(Call<MarvelComicDataWrapper> call, Response<MarvelComicDataWrapper> response) {
                int code = response.code();
                if (code == 200) {
                    MarvelComicDataWrapper cdw = response.body();
                    MarvelComicDataContainer dataContainer = cdw.getData();
                    ArrayList<MarvelComic> comicsList = new ArrayList(Arrays.asList(dataContainer.getResults()));
                    adapter.setmDataset(comicsList);
                    Log.d("ComicsListActivity", "Request successful!!");
                } else {
                    Log.d("ComicsListActivity", "Request successful but something went wrong parsing!!");
                }
            }

            @Override
            public void onFailure(Call<MarvelComicDataWrapper> call, Throwable t) {
                Log.d("MARVEL_API", "Request failed! onFailure: stacktrace: " + t.getLocalizedMessage());
            }
        });
    }


}
