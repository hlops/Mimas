package com.hlops.mimas.data.descriptor;

import com.hlops.mimas.data.descriptor.attributes.Attribute;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.01.13
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseDescriptor {

    private final Map<String, Object> attributes = new LinkedHashMap<String, Object>();

    public BaseDescriptor() {
    }

    public BaseDescriptor(DescriptorReader reader) {
        for (Attribute<Object> attr : getAttributeNames()) {
            attributes.put(attr.getName(), attr.fromString(reader.get(attr.getName())));
        }
    }

    protected abstract Attribute<Object>[] getAttributeNames();

    protected final Map<String, Object> getAttributes() {
        return attributes;
    }

    public void store(DescriptorWriter writer) {
        for (Attribute<Object> attr : getAttributeNames()) {
            writer.save(attr.getName(), attr.toString(attributes.get(attr.getName())));
        }
    }

    protected <T> T getValue(Attribute<T> attr) {
        //noinspection unchecked
        return (T) getAttributes().get(attr.getName());
    }

    protected <T> T setValue(Attribute<T> attr, T value) {
        //noinspection unchecked
        return (T) getAttributes().put(attr.getName(), value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDescriptor)) return false;

        BaseDescriptor that = (BaseDescriptor) o;

        if (!attributes.equals(that.attributes)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return attributes.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + listAttributes() + '}';
    }

    protected String listAttributes() {
        StringBuilder sb = new StringBuilder();
        for (Attribute<Object> attr : getAttributeNames()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(attr.getName()).append("=").append(attr.toString(attributes.get(attr.getName())));
        }
        return sb.toString();
    }
}
