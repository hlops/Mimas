package com.hlops.mimas.test.core;

import com.hlops.mimas.data.descriptor.MimasDescriptor;
import com.hlops.mimas.data.descriptor.storage.SimpleDescriptorReader;
import com.hlops.mimas.data.descriptor.storage.SimpleDescriptorWriter;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 03.01.13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class TestMimasDescriptor {

    @Test
    public void testDescriptor() throws Exception {
        MimasDescriptor descriptor = new MimasDescriptor();
        assertNotNull(descriptor.getId());
        assertNotNull(descriptor.getVersion());

        StringWriter sw = new StringWriter();
        SimpleDescriptorWriter writer = new SimpleDescriptorWriter(sw);
        descriptor.store(writer);
        writer.close();
        System.out.printf(sw.toString());
        MimasDescriptor descriptor1 = new MimasDescriptor(new SimpleDescriptorReader(new StringReader(sw.toString())));

        assertEquals(descriptor, descriptor1);
        assertEquals(descriptor.hashCode(), descriptor1.hashCode());
    }
}
