package io.quantics.codingchallenge.model;

import java.util.List;

public class Node {

    private final String name;
    private final List<Node> children;

    public Node(String name, List<Node> children) {
        this.name = name;
        this.children = children;
    }

    // TODO add methods

}
