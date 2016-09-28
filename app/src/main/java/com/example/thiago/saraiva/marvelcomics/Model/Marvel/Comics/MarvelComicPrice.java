package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelComicPrice {
    String type; //A description of the price (e.g. print price, digital price).,
    float price; //The price (all prices in USD).

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
