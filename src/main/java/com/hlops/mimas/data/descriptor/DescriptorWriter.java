package com.hlops.mimas.data.descriptor;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public interface DescriptorWriter {

    void save(String key, String value);

    void openGroup(String items);

    void closeGroup();
}
