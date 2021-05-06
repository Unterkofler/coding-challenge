package io.quantics.codingchallenge;

import io.quantics.codingchallenge.model.Node;
import io.quantics.codingchallenge.model.Row;
import io.quantics.codingchallenge.service.TreeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The program reads the inputfile and build a tree out of it and
 * build out of the tree a string that represent a tree in JSON format
 */

@SpringBootApplication
public class CodingChallengeApplication {

	public static void main(String[] args) { SpringApplication.run(CodingChallengeApplication.class, args);
		Path path = Paths.get("src/test/resources/input.csv");
		TreeService treeService = new TreeService();
		List<String> attributes = new ArrayList<>();
		attributes.add("Region");
		attributes.add("Country");
		attributes.add("Product group");
		attributes.add("SKU");

		treeService.serializeTree(treeService.buildTree(attributes,treeService.readInputFile(path)));
	}

}
