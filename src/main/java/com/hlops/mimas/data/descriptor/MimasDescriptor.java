package com.hlops.mimas.data.descriptor;

import com.hlops.mimas.data.GlobalProperties;
import com.hlops.mimas.data.descriptor.attributes.Attribute;
import com.hlops.mimas.data.descriptor.attributes.UUIDConverter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.01.13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class MimasDescriptor extends BaseDescriptor {

    private static final Attribute<UUID> ATTR_ID = new Attribute<UUID>("id", new UUIDConverter());
    private static final Attribute<String> ATTR_VERSION = new Attribute<String>("version");

    private static final Attribute[] attributeNames = new Attribute[]{
            ATTR_ID, ATTR_VERSION
    };

    public MimasDescriptor() {
        setValue(ATTR_ID, UUID.randomUUID());
        setValue(ATTR_VERSION, GlobalProperties.getInstance().getCurrentVersion());
    }

    public MimasDescriptor(DescriptorReader reader) {
        super(reader);
        if (getValue(ATTR_ID) == null) {
            setValue(ATTR_ID, UUID.randomUUID());
        }
        if (getValue(ATTR_VERSION) == null) {
            setValue(ATTR_VERSION, GlobalProperties.getInstance().getCurrentVersion());
        }
    }

    @NotNull
    public UUID getId() {
        return getValue(ATTR_ID);
    }

    @NotNull
    public String getVersion() {
        return getValue(ATTR_VERSION);
    }

    @Override
    protected Attribute<Object>[] getAttributeNames() {
        //noinspection unchecked
        return attributeNames;
    }
}
