package io.quantics.codingchallenge.model;

import java.util.List;

/**
 * This class represent a node that contains several children nodes and
 * the name represent the node name.
 */

public class Node {

    private final String name;
    private final List<Node> children;

    public Node(String name, List<Node> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + children +
                "}";
    }
}
