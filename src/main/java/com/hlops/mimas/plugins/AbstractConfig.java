package com.hlops.mimas.plugins;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 5:20 PM
 */
public class AbstractConfig {

    private final ResourceBundle bundle;

    protected AbstractConfig(String name) {
        this.bundle = ResourceBundle.getBundle(name);
    }

    protected String getString(String key) {
        String s = bundle.getString(key);
        if (s == null) {
            Logger.getLogger(this.getClass().getSimpleName()).warning("no key in bandle: " + key);
        }
        return s;
    }

    protected int getInt(String key) {
        String value = getString(key);
        int n;
        try {
            n = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            Logger.getLogger(this.getClass().getSimpleName()).severe("Can't parse to int: " + key + "=" + value);
            throw e;
        }
        return n;
    }

}
