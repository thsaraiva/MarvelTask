package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Creators;

/**
 * Created by thsaraiva on 23/09/2016.
 */
public class MarvelCreator {
//    resourceURI (string, optional): The path to the individual creator resource.,
    String name; //The full name of the creator.,
    String role; //The role of the creator in the parent entity.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
