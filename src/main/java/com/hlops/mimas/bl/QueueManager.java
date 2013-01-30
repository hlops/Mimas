package com.hlops.mimas.bl;

import java.util.concurrent.SynchronousQueue;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.01.13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class QueueManager {

    private static QueueManager queueManager = new QueueManager();

    private QueueManager() {
        queue = new SynchronousQueue();
    }

    private final SynchronousQueue queue;

    public static QueueManager getQueueManager() {
        return queueManager;
    }

    public SynchronousQueue getQueue() {
        return queue;
    }

}
