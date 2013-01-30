package com.hlops.mimas.data.descriptor.attributes;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class UUIDConverter implements TypeConverter<UUID> {

    public UUID fromString(@NotNull String value) {
        return UUID.fromString(value);
    }

    public String toString(@NotNull UUID data) {
        return data.toString();
    }
}
