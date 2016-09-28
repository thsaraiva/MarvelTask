package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelComicDataWrapper {
    int code; //The HTTP status code of the returned result.,

    String status; //A string description of the call status.,

    String copyright; //The copyright notice for the returned result.,

    String attributionText; //The attribution notice for this result. Please display either this notice or the contents of the attributionHTML field on all screens which contain data from the Marvel Comics API.

    String attributionHTML; //An HTML representation of the attribution notice for this result. Please display either this notice or the contents of the attributionText field on all screens which contain data from the Marvel Comics API.,

    MarvelComicDataContainer data; //The results returned by the call.

    String etag; //A digest value of the content returned by the call.

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public MarvelComicDataContainer getData() {
        return data;
    }

    public void setData(MarvelComicDataContainer data) {
        this.data = data;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}

