package com.hlops.mimas.plugins;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 5:20 PM
 */
public class MimasConfig extends AbstractConfig {

    private static final MimasConfig instance = new MimasConfig();

    public static MimasConfig getInstance() {
        return instance;
    }

    private final int executorSize;

    private MimasConfig() {
        super("mimasResources");
        executorSize = getInt("mimas.executors.sizes");
    }

    public int getExecutorSize() {
        return executorSize;
    }
}
