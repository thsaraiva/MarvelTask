package com.example.thiago.saraiva.marvelcomics.Model.Marvel.Common;

/**
 * Created by thsaraiva on 21/09/2016.
 */
public class MarvelThumbnail {
    String path;
    String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return path + extension;
    }
}
