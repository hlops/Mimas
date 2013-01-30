package com.hlops.mimas.plugins.foto;

import com.hlops.mimas.data.descriptor.BaseDescriptor;
import com.hlops.mimas.data.descriptor.DescriptorReader;
import com.hlops.mimas.data.descriptor.DescriptorWriter;
import com.hlops.mimas.data.descriptor.MimasDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.01.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class FotoAlbumDescriptor extends MimasDescriptor {

    private List<BaseDescriptor> items = new ArrayList<BaseDescriptor>();

    public FotoAlbumDescriptor() {
    }

    public FotoAlbumDescriptor(DescriptorReader reader) {
        super(reader);
    }

    public List<BaseDescriptor> getItems() {
        return items;
    }

    @Override
    public void store(DescriptorWriter writer) {
        super.store(writer);
        for (BaseDescriptor item : items) {
            try {
                writer.openGroup("item");
                item.store(writer);
            } finally {
                writer.closeGroup();
            }
        }
    }
}
