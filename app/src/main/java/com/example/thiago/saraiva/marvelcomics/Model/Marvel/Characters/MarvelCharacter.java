package com.example.thiago.saraiva.retrofithelloworld.Model.Marvel.Characters;

import com.example.thiago.saraiva.retrofithelloworld.Model.Marvel.Common.MarvelThumbnail;

/**
 * Created by thsaraiva on 21/09/2016.
 */
public class MarvelCharacter {

    int id; //The unique ID of the character resource.,
    String name; //The name of the character.
    String description; //A short bio or description of the character.
    String resourceURI; //The canonical URL identifier for this resource.
    MarvelThumbnail thumbnail; //The representative image for this character.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public MarvelThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MarvelThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return name;
    }
}
