package com.hlops.mimas.data.descriptor.storage;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 04.01.13
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class Tags {

    private static final String DELIMITER = ";";
    private final Set<String> tags = new LinkedHashSet<String>();

    public Tags() {
    }

    public Tags(@NotNull String tagString) {
        for (String s : tagString.split(DELIMITER)) {
            if (!StringUtils.isBlank(s)) {
                tags.add(s.trim().replaceAll(DELIMITER, ","));
            }
        }
    }

    public String[] getTags() {
        return tags.toArray(new String[tags.size()]);
    }

    @Override
    public String toString() {
        return StringUtils.join(tags, DELIMITER);
    }
}
