package com.hlops.mimas.data.descriptor;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public interface DescriptorReader {

    String defaultEncoding = "UTF-8";

    String get(String key);

    void close() throws IOException;

}
