package com.hlops.mimas.plugins.foto.dto;

import com.hlops.mimas.plugins.foto.descriptor.AlbumDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 29.11.12
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class Album {

    private final long id;
    private final String name;
    private final transient String path;
    private final String description;
    private final List<Photo> items;
    private final List<Album> albums;
    private transient Boolean isLoaded = false;

    private static final String[] IMAGE_EXTENSIONS = new String[]{
            "jpg", "jpeg", "gif", "png"
    };

    public Album(File f) {
        this(f.getPath(), f.getName());
    }

    public Album(String path, String name) {
        this(path, name, null, new ArrayList<Photo>(), new ArrayList<Album>());
    }

    public Album(AlbumDescriptor descriptor) {
        this(descriptor.getPath(), "");
    }

    public Album(String path, String name, String description, List<Photo> items, List<Album> albums) {
        this.path = path;
        this.name = name;
        this.description = description;
        this.items = items;
        this.albums = albums;

        id = hashCode();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public List<Photo> getItems() {
        return items;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    /*
        public void loadItems() {
            File root = new File(path);
            Iterator<File> it = FileUtils.iterateFiles(root, new SuffixFileFilter(IMAGE_EXTENSIONS, IOCase.SYSTEM), FalseFileFilter.FALSE);
            this.items.clear();
            while (it.hasNext()) {
                File f = it.next();
                System.out.println(f.getName());
                this.items.add(new Photo(f));
            }
        }

    */
    public Photo getPhoto(String name) {
        File root = new File(path);
        return new Photo(new File(root, name));
    }

}
