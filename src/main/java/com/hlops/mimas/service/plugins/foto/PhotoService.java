package com.hlops.mimas.service.plugins.foto;

import com.google.gson.Gson;
import com.hlops.mimas.plugins.foto.PhotoManager;
import com.hlops.mimas.plugins.foto.dto.Album;
import com.hlops.mimas.plugins.foto.dto.Photo;
import com.sun.jersey.api.JResponse;
import org.apache.commons.lang.time.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 28.11.12
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */

@Path("/foto")
public class PhotoService {

    private static final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    private static String getVersion() {
        return "1.0";
    }

    @GET
    @Path("/about")
    /**
     * Get "about" information
     */
    public JResponse about() {
        return JResponse.ok("foto plugin, version " + getVersion()).build();
    }

    @GET
    @Path("/albums")
    /**
     * Get list of albums
     */
    public JResponse getAlbums() {
        List<Album> albums = PhotoManager.getInstance().listAlbums();
        return JResponse.ok(gson.toJson(albums)).build();
    }

    @GET
    @Path("/album/{id}")
    /**
     * Get list of albums
     */
    public JResponse getAlbum(@PathParam("id") long id) {
        Album album = PhotoManager.getInstance().getAlbum(id);
        return JResponse.ok(gson.toJson(album)).build();
    }

    @GET
    @Path("/view/{id}/{name}")
    /**
     * Show image
     */
    public JResponse showImage(@PathParam("id") long id, @PathParam("name") String name) throws IOException {
        Album album = PhotoManager.getInstance().getAlbum(id);
        File f = new File(album.getPath(), name);
        if (ifNotModified(f)) {
            return JResponse.notModified().build();
        }
        return setModified(JResponse.ok(f, "image/jpeg"), f.lastModified()).build();
    }

    @GET
    @Path("/thumb/{id}/{name}")
    /**
     * Show image thumbnail
     */
    public JResponse showThumbnail(@PathParam("id") long id, @PathParam("name") String name) throws IOException {
        Album album = PhotoManager.getInstance().getAlbum(id);
        File f = new File(album.getPath(), name);
        if (ifNotModified(f)) {
            return JResponse.notModified().build();
        }
        Photo photo = new Photo(f);
        File thumb = new File(album.getPath(), photo.getThumbnail());
        return setModified(JResponse.ok(thumb, "image/jpeg"), thumb.lastModified()).build();
    }

    private boolean ifNotModified(File f) {
        try {
            long lastModified = request.getDateHeader("If-Modified-Since");
            if (f.lastModified() - lastModified < DateUtils.MILLIS_PER_SECOND) {
                return true;
            }
        } catch (Exception e) {
            // do nothing
        }
        return false;
    }

    private JResponse.JResponseBuilder<File> setModified(JResponse.JResponseBuilder<File> response, long lastModified) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return setModified(response, lastModified, cal.getTimeInMillis());
    }

    private JResponse.JResponseBuilder<File> setModified(JResponse.JResponseBuilder<File> response, long lastModified, long expires) {
        response.lastModified(new Date(lastModified)).expires(new Date(expires));
        return response;
    }

}
