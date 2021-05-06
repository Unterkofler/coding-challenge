package io.quantics.codingchallenge.service;

import io.quantics.codingchallenge.model.Node;
import io.quantics.codingchallenge.model.Row;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeService {

    public TreeService() {
    }

    /**
     * This method is used to map the input file to a list of row.
     * Where the attributes are the keys to access the values.
     *
     * @param file is the path to the file
     * @return a list of row where the elements are mapped
     * @throws IOException
     */

    public List<Row> readInputFile(Path file) {
        List<String[]> list = dataInToList(file);
        List<Row> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        String[] attributes;
        String[] values;
        //0 to get the first line of the file witch are the attributes
        attributes = list.get(0);

        for (int j = 1; j < list.size(); ++j) {
            values = list.get(j);
            for (int i = 0; i < attributes.length; i++) {
                map.put(attributes[i], values[i]);
            }
            result.add(new Row(map));
            map = new HashMap<>();
        }
        return result;
    }

    /**
     * This method is used to build a tree out of the rows and
     * the attributes. The method calls another method witch build
     * the tree.
     *
     * @param attributes are the keys to access the values
     * @param rows       a list of row where the elements are mapped
     * @return the tree bild out of the mapped row list
     */
    public List<Node> buildTree(List<String> attributes, List<Row> rows) {
        if (attributes == null || rows == null){
            System.out.println("attributes or rows are null!!!");
            return null;
        }

        List<Node> tree = new ArrayList<>();
        int count = 0;

        tree = buildTree(attributes, rows, count);
        return tree;
    }

    /**
     * This method is used to build a tree out of the rows and
     * the attributes. Build the tree in order of the attributes
     * first attribute --> level 0 in the tree, second attribute --> level 1
     * in the tree and so on. It splits the rows into subrows until
     * there is only one row left and create the leave node.
     *
     * @param attributes give the order how the tree is build
     * @param rows       a list of row where the elements are mapped
     * @param count      to take track witch attribut is selected
     * @return the tree in order of the given attributes
     */

    private List<Node> buildTree(List<String> attributes, List<Row> rows, int count) {

        List<Node> tree = new ArrayList<>();
        List<String> childs = new ArrayList<>();
        List<Row> subRows = new ArrayList<>();

        if (count == attributes.size()) {
            return null;
        }

        childs = childrenNodes(rows, attributes.get(count));
        for (int i = 0; i < childs.size(); i++) {
            subRows = getsubRow(rows, attributes.get(count), childs.get(i));
            Node node = new Node(childs.get(i), buildTree(attributes, subRows, count + 1));
            tree.add(node);
        }
        return tree;
    }

    /**
     * This method take a tree and bild a string that
     * represent a tree in JSON format.
     *
     * @param tree is a representation of a tree
     * @return a string that represent a tree in JSON format
     */

    public String serializeTree(List<Node> tree) {
        int level = 0;
        return "{\n" + serializeTree(tree, level) + '}';
    }

    /**
     * This method take a tree and bild a string that
     * represent a tree in JSON format.
     *
     * @param tree  is a representation of a tree
     * @param level track the actual depth of the tree
     * @return a string that represent a tree in JSON format
     */

    private String serializeTree(List<Node> tree, int level) {
        String result = "";
        if (tree == null) {
            return "";
        }

        for (int i = 0; i < tree.size(); i++) {
            for (int j = 0; j <= level; j++) {
                result = result + "\t\t";
            }

            if (tree.get(i).getChildren() == null) {
                //to decide in the leave if a ',' is necessary
                if (tree.size() > 1 && i == tree.size() - 1 || tree.size() == 1) {
                    result = result + '"' + tree.get(i).getName() + "\": {}\n";
                } else {
                    result = result + '"' + tree.get(i).getName() + "\": {},\n";
                }
            } else {
                result = result + '"' + tree.get(i).getName() + "\": {\n";
                result = result + serializeTree(tree.get(i).getChildren(), level + 1);
                for (int j = 0; j <= level; j++) {
                    result = result + "\t\t";
                }
                //to decide if a ',' is necessary
                if (tree.size() > 1 && i == tree.size() - 1 || tree.size() == 1) {
                    result = result + "}\n";
                } else {
                    result = result + "},\n";
                }
            }
        }
        return result;
    }

    /**
     * This method take the rows at the specified attrebute and distinct
     * them and give the children nodes back.
     *
     * @param rows      to searche through
     * @param attribute where to search for the children in the row
     * @return the children nodes in a string list
     */
    private List<String> childrenNodes(List<Row> rows, String attribute) {
        List<String> children = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            children.add(rows.get(i).getAttributes().get(attribute));
        }
        children = children.stream().distinct().collect(Collectors.toList());
        return children;
    }

    /**
     * This methode give back the subrows and distinct them.
     *
     * @param rows         to searche through
     * @param attribute    the place where to look for the value
     * @param valueContain the value we are searching for
     * @return the subrows
     */
    private List<Row> getsubRow(List<Row> rows, String attribute, String valueContain) {
        List<Row> subList = rows.stream()
                .filter(x -> x.getAttributes().get(attribute).equals(valueContain))
                .distinct()
                .collect(Collectors.toList());
        return subList;
    }

    /**
     * This methode is used to read the input file line by line and split
     * it up where a ',' is.
     *
     * @param file is the path to the file
     * @return a list of strings where a line is split by ','
     * @throws IOException
     */
    private List<String[]> dataInToList(Path file) {
        String line = "";
        List<String[]> list = new ArrayList<>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(String.valueOf(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add(line.split(","));
        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
