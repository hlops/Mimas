package com.hlops.mimas.sync;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/30/13
 * Time: 4:43 PM
 */
public interface LockerFuture<T1, T2> extends RunnableFuture<T1> {

    T2 getLock();

    LockPolicy getLockPolicy();

    void setDoneHandler(DoneHandler handler);
}
