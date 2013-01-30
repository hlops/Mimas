package com.hlops.mimas.plugins.foto;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class ImageSize {

    private static final Logger logger = Logger.getLogger(ImageSize.class.getName());

    private int X, Y;

    public ImageSize(int x, int y) {
        X = x;
        Y = y;
    }

    public ImageSize(@NotNull String size) {
        String[] arr = size.split("[\\D]+");
        try {
            if (arr.length > 1) {
                X = Integer.parseInt(arr[0]);
                Y = Integer.parseInt(arr[1]);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            X = Y = 0;
            logger.log(Level.WARNING, "Can not parse image size: " + size);
        }
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return X + "x" + Y;
    }

}
