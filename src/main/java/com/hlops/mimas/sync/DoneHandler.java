package com.hlops.mimas.sync;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/30/13
 * Time: 6:17 PM
 */
public interface DoneHandler {

    void onDone(LockerFuture locker);
}
