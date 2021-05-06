package io.quantics.codingchallenge.service;

import io.quantics.codingchallenge.model.Node;
import io.quantics.codingchallenge.model.Row;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class TreeServiceTest {

    private final TreeService service = new TreeService();

    @Test
    void testReadInputFile() {
        Path path = Paths.get("src/test/resources/input.csv");
        List<Row> rows = service.readInputFile(path);
        BufferedReader bufferedReader = null;
        String line = "";

        System.out.println("****************Test the ReadInputFile methode****************");
        System.out.println("The input with the attributes in the first line is: ");
        System.out.println();

        try {
            bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!((line = bufferedReader.readLine()) != null))break;
                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        System.out.println("The generated rows mapped on the attributes and values are:");
        System.out.println();
        System.out.println(rows.toString());
    }

    @Test
    void testBuildTree() {
        List<Node> listTree = new ArrayList<>();
        List<String> attributes = new ArrayList<>();
        attributes.add("Region");
        attributes.add("Country");

        List<String> attributes1 = new ArrayList<>();
        attributes1.add("Country");
        attributes1.add("Region");

        Path path = Paths.get("src/test/resources/input.csv");
        System.out.println("attributes and rows are null:");
        System.out.println(service.buildTree(null,null));

        System.out.println("attributes or rows are null:");
        System.out.println(service.buildTree(null,service.readInputFile(path)));

        System.out.println("attributes or rows are null:");
        System.out.println(service.buildTree(attributes,null));

        System.out.println("Tree build in order of Region Country:");
        System.out.println(service.buildTree(attributes,service.readInputFile(path)));
        System.out.println("Tree build in order of Country, Region:");
        System.out.println(service.buildTree(attributes1,service.readInputFile(path)));

    }

    @Test
    void testSerializeTree() {
        Path path = Paths.get("src/test/resources/input.csv");
        String toCheck = "";
        List<String> attributes = new ArrayList<>();
        attributes.add("Region");
        attributes.add("Country");
        attributes.add("Product group");
        attributes.add("SKU");

        String serializeTree = service.serializeTree(service.buildTree(attributes,service.readInputFile(path)));

        for (int i = 0; i < serializeTree.length(); i++) {
            if ('{' == serializeTree.charAt(i) || '}' == serializeTree.charAt(i)){
                toCheck = toCheck + serializeTree.charAt(i);
            }
        }

        System.out.println("Test the methode SerializeTree");
        System.out.println(toCheck);
        String result = "";
        String result1 = "";
        boolean isSerialize = false;

        for (int i = 0; i < toCheck.length(); i++) {
            if ('}' == toCheck.charAt(i) && '{' == toCheck.charAt(i - 1)){
                result = toCheck.substring(0,i-1);
                result1 =  toCheck.substring(i+1);
                toCheck = result + result1;
                i = 0;
            }
        }
        if (toCheck.length() == 0){
            isSerialize = true;
        }

        System.out.println("Is it serialize:");
        System.out.println(isSerialize);

    }

}
