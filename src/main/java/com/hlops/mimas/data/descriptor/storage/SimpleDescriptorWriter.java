package com.hlops.mimas.data.descriptor.storage;

import com.hlops.mimas.data.descriptor.DescriptorWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDescriptorWriter implements DescriptorWriter {

    private final Properties properties = new Properties();
    private OutputStream outputStream;
    private Writer writer;

    public SimpleDescriptorWriter(@NotNull OutputStream outputStream) throws IOException {
        this.outputStream = outputStream;
    }

    public SimpleDescriptorWriter(@NotNull Writer writer) throws IOException {
        this.writer = writer;
    }

    public void save(String key, String value) {
        properties.put(key, value);
    }

    public void openGroup(String group) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void closeGroup() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void close() throws IOException {
        if (outputStream != null) {
            properties.store(outputStream, null);
            outputStream.close();
        }
        if (writer != null) {
            properties.store(writer, null);
            writer.close();
        }
    }

}
