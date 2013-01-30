package com.hlops.mimas.data.descriptor.storage;

import com.hlops.mimas.data.descriptor.DescriptorReader;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDescriptorReader implements DescriptorReader {

    private final Properties properties = new Properties();
    private InputStream inputStream;
    private Reader reader;

    public SimpleDescriptorReader(@NotNull InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        properties.load(inputStream);
    }

    public SimpleDescriptorReader(@NotNull Reader reader) throws IOException {
        this.reader = reader;
        properties.load(reader);
    }

    public String get(String key) {
        return String.valueOf(properties.get(key));
    }

    public void close() throws IOException {
        if (inputStream != null) inputStream.close();
        if (reader != null) reader.close();
    }

}
