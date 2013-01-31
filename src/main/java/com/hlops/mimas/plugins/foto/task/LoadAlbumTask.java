package com.hlops.mimas.plugins.foto.task;

import com.hlops.mimas.plugins.foto.descriptor.AlbumDescriptor;
import com.hlops.mimas.plugins.foto.dto.Album;
import com.hlops.mimas.sync.DoneHandler;
import com.hlops.mimas.sync.LockPolicy;
import com.hlops.mimas.sync.LockerFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 12:06 PM
 */
public class LoadAlbumTask extends FutureTask<Album> implements LockerFuture<Album, AlbumDescriptor> {

    private final AlbumDescriptor descriptor;
    private DoneHandler doneHandler;

    public LoadAlbumTask(final AlbumDescriptor descriptor) {
        super(new Callable<Album>() {
            public Album call() throws Exception {
                Album album = new Album(descriptor);
                //album.loadItems();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                }
                System.out.println(descriptor.getPath());
                return album;
            }
        });
        this.descriptor = descriptor;
    }

    public AlbumDescriptor getLock() {
        return descriptor;
    }

    public LockPolicy getLockPolicy() {
        return null;
    }

    public void setDoneHandler(DoneHandler handler) {
        this.doneHandler = handler;
    }

    @Override
    protected void done() {
        super.done();
        if (this.doneHandler != null) {
            this.doneHandler.onDone(this);
        }
    }
}
