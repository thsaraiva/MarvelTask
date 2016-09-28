package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Comics;

import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Common.MarvelTextObject;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Common.MarvelThumbnail;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Creators.MarvelCreator;
import com.example.thiago.saraiva.marvelcomics.Model.Marvel.Creators.MarvelCreatorSummary;

import java.util.Date;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelComic {

    int id; //The unique ID of the comic resource.
    int digitalId; //The ID of the digital comic representation of this comic. Will be 0 if not available digitally.
    int pageCount; //The number of story pages in the comic.,
    String title; //The canonical title of the comic.
    String description; //The preferred description of the comic.
    String format; //The publication format of the comic e.g. comic, hardcover, trade paperback.,
    String resourceURI; //The canonical URL identifier for this resource.,
    Date modified; //The date the resource was most recently modified.,
    MarvelThumbnail thumbnail; //The representative image for this comic.,
    MarvelTextObject[] textObjects; //A set of descriptive text blurbs for the comic.,
    MarvelComicPrice[] prices; //A list of prices for this comic.,
    MarvelCreatorSummary creators; //A resource list containing the creators associated with this comic.,
//    characters (CharacterList, optional): A resource list containing the characters which appear in this comic.,


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(int digitalId) {
        this.digitalId = digitalId;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public MarvelTextObject[] getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(MarvelTextObject[] textObjects) {
        this.textObjects = textObjects;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public MarvelComicPrice[] getPrices() {
        return prices;
    }

    public void setPrices(MarvelComicPrice[] prices) {
        this.prices = prices;
    }

    public MarvelThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MarvelThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public MarvelCreatorSummary getCreators() {
        return creators;
    }

    public void setCreators(MarvelCreatorSummary creators) {
        this.creators = creators;
    }
}
