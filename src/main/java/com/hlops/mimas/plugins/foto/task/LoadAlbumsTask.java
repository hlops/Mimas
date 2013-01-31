package com.hlops.mimas.plugins.foto.task;

import com.hlops.mimas.plugins.foto.dto.Album;

import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 12:06 PM
 */
public class LoadAlbumsTask extends FutureTask<List<Album>> {

    public LoadAlbumsTask() {
        super(null);
    }
}
