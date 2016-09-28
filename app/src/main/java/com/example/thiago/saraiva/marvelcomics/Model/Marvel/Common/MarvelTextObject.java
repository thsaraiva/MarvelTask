package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Common;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelTextObject {
    String type; //The canonical type of the text object (e.g. solicit text, preview text, etc.).,
    String language; //The IETF language tag denoting the language the text object is written in.,
    String text; //The text.

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
