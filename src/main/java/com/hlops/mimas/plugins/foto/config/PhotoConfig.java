package com.hlops.mimas.plugins.foto.config;

import com.hlops.mimas.plugins.AbstractConfig;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 5:46 PM
 */
public class PhotoConfig extends AbstractConfig {

    private static final PhotoConfig instance = new PhotoConfig();

    public static PhotoConfig getInstance() {
        return instance;
    }

    protected PhotoConfig() {
        super("photoConfig");
    }
}
