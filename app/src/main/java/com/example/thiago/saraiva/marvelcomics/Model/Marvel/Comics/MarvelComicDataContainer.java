package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelComicDataContainer {
    int offset; //The requested offset (number of skipped results) of the call.

    int limit; //The requested result limit.

    int total;//The total number of resources available given the current filter set.

    int count; //The total number of results returned by this call.

    MarvelComic[] results; //The list of characters returned by the call.

    private int resultsInList;

    private String listPrice;

    private int totalNumberOfPagesInList;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MarvelComic[] getResults() {
        return results;
    }

    public void setResults(MarvelComic[] results) {
        this.results = results;
    }

    public int getTotalNumberOfPagesInList() {
        return totalNumberOfPagesInList;
    }

    public void setTotalNumberOfPagesInList(int totalNumberOfPagesInList) {
        this.totalNumberOfPagesInList = totalNumberOfPagesInList;
    }

    public int getResultsInList() {
        return resultsInList;
    }

    public void setResultsInList(int resultsInList) {
        this.resultsInList = resultsInList;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }
}
