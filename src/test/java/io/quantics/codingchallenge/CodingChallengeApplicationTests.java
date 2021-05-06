package io.quantics.codingchallenge;

import io.quantics.codingchallenge.service.TreeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Tis class is for testing the CodingCallengeApplication
 */
@SpringBootTest
class CodingChallengeApplicationTests {

	@Test
	void contextLoads() {
		Path path = Paths.get("src/test/resources/input.csv");
		TreeService treeService = new TreeService();
		List<String> attributesTest1 = new ArrayList<>();
		attributesTest1.add("Region");
		attributesTest1.add("Date");
		attributesTest1.add("Country");
		attributesTest1.add("Product group");
		attributesTest1.add("Size");
		attributesTest1.add("Quantity");
		attributesTest1.add("SKU");

		String result1 = treeService.serializeTree(treeService.buildTree(attributesTest1,treeService.readInputFile(path)));
		System.out.println("************************************Test1************************************");
		System.out.println("The Attributes in the following order:");
		System.out.println("Region, Date, Country, Product group, Size, Quantity, SKU");
		System.out.println(result1);
		System.out.println("\n");

		List<String> attributesTest2 = new ArrayList<>();
		attributesTest2.add("Country");
		attributesTest2.add("Date");
		attributesTest2.add("Product group");
		attributesTest2.add("Size");
		attributesTest2.add("Quantity");
		attributesTest2.add("Region");
		attributesTest2.add("SKU");

		String result2 = treeService.serializeTree(treeService.buildTree(attributesTest2,treeService.readInputFile(path)));
		System.out.println("************************************Test2************************************");
		System.out.println("The Attributes in the following order:");
		System.out.println("Country, Date, Product group, Size, Quantity, Region, SKU");
		System.out.println(result2);
		System.out.println("\n");

		List<String> attributesTest3 = new ArrayList<>();
		attributesTest3.add("Country");
		attributesTest3.add("Region");
		attributesTest3.add("Product group");
		attributesTest3.add("SKU");

		String result3 = treeService.serializeTree(treeService.buildTree(attributesTest3,treeService.readInputFile(path)));
		System.out.println("************************************Test3************************************");
		System.out.println("The Attributes in the following order:");
		System.out.println("Country, Region, Product group, SKU");
		System.out.println(result3);
		System.out.println("\n");

		List<String> attributesTest4 = new ArrayList<>();
		attributesTest4.add("Region");

		String result4 = treeService.serializeTree(treeService.buildTree(attributesTest4,treeService.readInputFile(path)));
		System.out.println("************************************Test4************************************");
		System.out.println("The Attributes in the following order:");
		System.out.println("Region");
		System.out.println(result4);
		System.out.println("\n");

		List<String> attributesTest5 = new ArrayList<>();


		String result5 = treeService.serializeTree(treeService.buildTree(attributesTest5,treeService.readInputFile(path)));
		System.out.println("************************************Test4************************************");
		System.out.println("The Attributes in the following order:");
		System.out.println("----");
		System.out.println(result5);
		System.out.println("\n");

	}

}
