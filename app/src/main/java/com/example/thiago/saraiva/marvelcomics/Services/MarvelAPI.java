package com.example.thiago.saraiva.marvelcomics.Services;

import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Characters.MarvelCharacterDataWrapper;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics.MarvelComicDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by thsaraiva on 21/09/2016.
 */
public interface MarvelAPI {

    @GET("/v1/public/characters")
    Call<MarvelCharacterDataWrapper> getCharacters(@Query("limit") String limit);

    @GET("/v1/public/comics")
    Call<MarvelComicDataWrapper> getComics(@Query("limit") String limit);
}
