package com.hlops.mimas.plugins.foto;

import com.hlops.mimas.data.descriptor.BaseDescriptor;
import com.hlops.mimas.data.descriptor.attributes.Attribute;
import com.hlops.mimas.data.descriptor.attributes.DateConverter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class FotoItemDescriptor extends BaseDescriptor {

    private static final Attribute<String> ATTR_NAME = new Attribute<String>("name");
    private static final Attribute<ImageSize> ATTR_SIZE = new Attribute<ImageSize>("size", new ImageSizeConverter());
    private static final Attribute<String> ATTR_THUMB_NAME = new Attribute<String>("thumbName");
    private static final Attribute<String> ATTR_THUMB_SIZE = new Attribute<String>("thumbSize");
    private static final Attribute<Date> ATTR_DATE = new Attribute<Date>("date", new DateConverter());
    private static final Attribute<String> ATTR_COMMENTS = new Attribute<String>("comments");

    private static final Attribute[] attributeNames = new Attribute[]{
            ATTR_NAME, ATTR_SIZE,
            ATTR_THUMB_NAME, ATTR_THUMB_SIZE,
            ATTR_DATE, ATTR_COMMENTS
    };

    @Override
    protected Attribute<Object>[] getAttributeNames() {
        //noinspection unchecked
        return attributeNames;
    }

    public String getName() {
        return getValue(ATTR_NAME);
    }

    public void setName(String name) {
        setValue(ATTR_NAME, name);
    }

    public String getThumbName() {
        return getValue(ATTR_THUMB_NAME);
    }
}

