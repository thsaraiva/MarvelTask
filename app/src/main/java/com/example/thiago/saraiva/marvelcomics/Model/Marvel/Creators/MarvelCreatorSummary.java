package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Creators;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelCreatorSummary {
    int available; //Available creators in this list. Always greater than or equal than "returned" value.,
    int returned; //The number of creators returned in this collection (up to 20).,
//    collectionURI (string, optional): The path to the full list of creators in this collection.,
    MarvelCreator[]items; //The list of returned creators in this collection.

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public MarvelCreator[] getItems() {
        return items;
    }

    public void setItems(MarvelCreator[] items) {
        this.items = items;
    }
}
