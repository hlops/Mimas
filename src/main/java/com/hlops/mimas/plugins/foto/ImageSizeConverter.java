package com.hlops.mimas.plugins.foto;

import com.hlops.mimas.data.descriptor.attributes.TypeConverter;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class ImageSizeConverter implements TypeConverter<ImageSize> {

    public ImageSize fromString(@NotNull String value) {
        return new ImageSize(value);
    }

    public String toString(@NotNull ImageSize data) {
        return data.toString();
    }
}
