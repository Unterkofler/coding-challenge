package io.quantics.codingchallenge.model;

import java.util.Map;

/**
 * This class is to map strings on strings.
 */

public class Row {

    private final Map<String, String> attributes;

    public Row(Map<String, String> attributes) {
        this.attributes = attributes;
    }


    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Row{" +
                "attributes=" + attributes +
                "}\n";
    }
}
