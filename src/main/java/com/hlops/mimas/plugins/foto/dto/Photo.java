package com.hlops.mimas.plugins.foto.dto;

import com.hlops.mimas.plugins.foto.PhotoManager;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.11.12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class Photo {

    private String name;
    private String description;
    private String thumbnail;

    public Photo(String name, String description) {
        this.name = name;
        this.description = description;
        this.thumbnail = PhotoManager.MIMAS_DIRECTORY_NAME + "/s_" + name;
        if (!this.thumbnail.toLowerCase().endsWith(".jpg") && !this.thumbnail.toLowerCase().endsWith(".jpeg")) {
            this.thumbnail += ".jpg";
        }
    }

    public Photo(File f) {
        this(f.getName(), "");
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
