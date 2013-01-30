package com.hlops.mimas.data.descriptor.attributes;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */
public class Attribute<T> {

    private String name;
    private TypeConverter<T> converter;
    private boolean required;

    public Attribute(String name) {
        this(name, null);
    }

    public Attribute(@NotNull String name, @Nullable TypeConverter<T> converter) {
        this.name = name;
        this.converter = converter;
    }

    public String getName() {
        return name;
    }

    public String toString(T value) {
        return converter != null ? converter.toString(value) : value != null ? String.valueOf(value) : "";
    }

    public T fromString(String value) {
        //noinspection unchecked
        return converter != null ? converter.fromString(value) : (T) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;
        return name.equals(attribute.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
