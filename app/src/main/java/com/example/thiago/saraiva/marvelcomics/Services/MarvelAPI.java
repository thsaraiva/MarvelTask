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

    String MARVEL_BASE_URL = "https://gateway.marvel.com:443";
//    TODO: colocar chaves publicas em um arquivo de recurso
    String MARVEL_PUBLIC_KEY = "c5eea1883b9cf528794d17658686df6a";
    String MARVEL_PRIVATE_KEY = "379872e536a35257df69f89269b3daaeae776e48";
    String TIMESTAMP = "1";

    @GET("/v1/public/characters")
    Call<MarvelCharacterDataWrapper> getCharacters(@Query("limit") String limit);

    @GET("/v1/public/comics")
    Call<MarvelComicDataWrapper> getComics(@Query("limit") String limit);
}
