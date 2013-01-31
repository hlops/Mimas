package com.hlops.mimas.plugins.foto.descriptor;

import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/30/13
 * Time: 3:47 PM
 */
public class AlbumDescriptor {

    private final String path;

    public AlbumDescriptor(@NotNull String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumDescriptor)) return false;

        AlbumDescriptor that = (AlbumDescriptor) o;

        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }
}
