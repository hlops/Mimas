package com.hlops.mimas.plugins.foto;

import com.hlops.mimas.plugins.foto.dto.Album;
import com.hlops.mimas.plugins.foto.dto.Photo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 28.11.12
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class PhotoManager {

    public static final String MIMAS_DIRECTORY_NAME = ".mimas";

    private static PhotoManager instance = new PhotoManager();
    private static final int THUMB_WIDTH = 200;
    private static final int THUMB_HEIGHT = 150;

    public static PhotoManager getInstance() {
        return instance;
    }

    private List<Album> albums = new ArrayList<Album>();
    private Map<Long, Album> albumsMap = new HashMap<Long, Album>();

    private PhotoManager() {
        albums = new ArrayList<Album>();
        albums.add(createAlbum("D:/Anton/Сканы"));
    }

    public List<Album> listAlbums() {
        return albums;
    }

    private Album createAlbum(String path) {
        Album album = new Album(new File(path));
        albumsMap.put(album.getId(), album);
        enumSubAlbums(album);
        return album;
    }

    private void enumSubAlbums(Album album) {
        File root = new File(album.getPath());
        for (File f : root.listFiles()) {
            if (f.isDirectory() && !f.isHidden()) {
                Album child = new Album(f);
                albumsMap.put(child.getId(), child);
                album.getAlbums().add(child);
                enumSubAlbums(child);
            }
        }
    }

    public Album getAlbum(long id) {
        Album album = albumsMap.get(id);
        album.loadItems();
        return album;
    }

    public void createThumbnails() {
        List<Album> albums = listAlbums();
        for (Album album : albums) {
            createThumbnail(album);
        }
    }

    public void createThumbnail(Album album) {
        album.loadItems();
        File mimas = new File(album.getPath(), PhotoManager.MIMAS_DIRECTORY_NAME);
        if (mimas.mkdir()) {
            try {
                Files.setAttribute(mimas.toPath(), "dos:hidden", true);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        for (Photo photo : album.getItems()) {
            System.out.println(photo.getThumbnail());
            try {
                BufferedImage originalImage = ImageIO.read(new File(album.getPath(), photo.getName()));
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                BufferedImage resizedImage = resizeImage(originalImage, type);

                ImageIO.write(resizedImage, "jpg", new File(album.getPath(), photo.getThumbnail()));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        for (Album child : album.getAlbums()) {
            createThumbnail(child);
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        int w = THUMB_WIDTH;
        int h = THUMB_HEIGHT;

        if (originalImage.getWidth() / originalImage.getHeight() > w / h) {
            h = w * originalImage.getHeight() / originalImage.getWidth();
        } else {
            w = h * originalImage.getWidth() / originalImage.getHeight();
        }

        BufferedImage resizedImage = new BufferedImage(w, h, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, w, h, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }
}
