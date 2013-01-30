package com.hlops.mimas.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.01.13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class GlobalProperties {
    private static GlobalProperties instance = new GlobalProperties();

    private final ResourceBundle bundle;
    private DateFormat dateFormat;

    public GlobalProperties() {
        bundle = ResourceBundle.getBundle("global");
    }

    public static GlobalProperties getInstance() {
        return instance;
    }

    public String getCurrentVersion() {
        return bundle.getString("version");
    }

    public DateFormat getDateFormat() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(bundle.getString("dateFormat"));
        }
        return dateFormat;
    }

}
