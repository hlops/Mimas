package com.hlops.mimas.data.descriptor.attributes;

import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public interface TypeConverter<T> {

    T fromString(@NotNull String value);

    String toString(@NotNull T data);
}
