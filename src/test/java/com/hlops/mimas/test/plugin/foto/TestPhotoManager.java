package com.hlops.mimas.test.plugin.foto;

import com.hlops.mimas.plugins.foto.descriptor.AlbumDescriptor;
import com.hlops.mimas.plugins.foto.dto.Album;
import com.hlops.mimas.plugins.foto.task.LoadAlbumTask;
import com.hlops.mimas.sync.MimasQueue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 5:01 PM
 */
public class TestPhotoManager {

    @Test
    public void testLoadAlbum() throws Exception {

        List<RunnableFuture> runnables = new ArrayList<RunnableFuture>();
        int total = 5;

        for (int i = 0; i < total; i++) {
            RunnableFuture album = MimasQueue.getInstance().execute(new LoadAlbumTask(new AlbumDescriptor("album" + i)));
            runnables.add(album);
            MimasQueue.getInstance().execute(album);
        }

        for (int i = 0; i < total; i++) {
            runnables.get(i).get();
        }

        System.out.println("Hi!");

        for (int i = 0; i < total; i++) {
            Album a = (Album) MimasQueue.getInstance().execute(new LoadAlbumTask(new AlbumDescriptor("album" + i))).get();
            System.out.println("path=" + a.getPath());
        }

        System.out.println("Bye!");
    }
}
