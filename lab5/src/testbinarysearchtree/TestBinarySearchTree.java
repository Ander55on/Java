package testbinarysearchtree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class TestBinarySearchTree {

	private BinarySearchTree<String> treeStringsEmptyConstructor;
	private BinarySearchTree<Integer> treeIntegersEmptyConstructor;

	private BinarySearchTree<String> treeStringsComparator;
	private BinarySearchTree<Integer> treeIntegersComparator;

	@BeforeEach
	void setUp() throws Exception {
		treeStringsEmptyConstructor = new BinarySearchTree<>();
		treeIntegersEmptyConstructor = new BinarySearchTree<>();

		treeStringsComparator = new BinarySearchTree<>((x, y) -> x.compareTo(y));
		treeIntegersComparator = new BinarySearchTree<>((x, y) -> x - y);

	}

	@AfterEach
	void tearDown() throws Exception {

		treeStringsEmptyConstructor = null;
		treeIntegersEmptyConstructor = null;

		treeIntegersEmptyConstructor = null;
		treeIntegersComparator = null;

	}

	/**
	 * Test adding two string elements with empty constructor and with an comparator
	 */
	@Test
	void testAddStrings() {
		// EMPTY CONSTRUCTOR
		assertEquals(0, treeStringsEmptyConstructor.size(), "testing size with empty tree");
		boolean added = treeStringsEmptyConstructor.add("Balin");
		assertEquals(true, added, "Added string Balin");
		assertEquals(1, treeStringsEmptyConstructor.size(), "Testing size() of size 1");
		added = treeStringsEmptyConstructor.add("Legolas");
		assertEquals(true, added, "Added string Legolas");
		assertEquals(2, treeStringsEmptyConstructor.size(), "Testing size() of size 2");

		// COMPARATOR
		assertEquals(0, treeStringsComparator.size(), "testing size with empty tree");
		added = treeStringsComparator.add("Balin");
		assertEquals(true, added, "Added string Balin");
		assertEquals(1, treeStringsComparator.size(), "Testing size() of size 1");
		added = treeStringsComparator.add("Legolas");
		assertEquals(true, added, "Added string Legolas");
		assertEquals(2, treeStringsComparator.size(), "Testing size() of size 2");
	}
	/**
	 * Test Height - (x) order of insertion
	 * 
	 * 			O (1)
	 * 		   / \
	 * 	  (4) O	  O (2)
	 * 			/
	 * 		   O (3)		
	 */			 	

	void testHeight() {
		// EMPTY CONSTRUCTOR STRINGS
		int height = treeStringsEmptyConstructor.height();
		assertEquals(0, height, "testing height of empty tree");
		treeStringsEmptyConstructor.add("Balin");
		height = treeStringsEmptyConstructor.height();
		assertEquals(1, height, "testing height of tree with one element");
		treeStringsEmptyConstructor.add("Legolas");
		height = treeStringsEmptyConstructor.height();
		assertEquals(2, height, "testing height of tree with two elements");
		treeStringsEmptyConstructor.add("Gandalf");
		height = treeStringsEmptyConstructor.height();
		assertEquals(3, height, "testing height of tree with three elements all bigger than root");
		treeStringsEmptyConstructor.add("Aragon");
		height = treeStringsEmptyConstructor.height();
		assertEquals(3, height, "testing height of four elements");

		// COMPARATOR STRINGS
		height = treeStringsComparator.height();
		assertEquals(0, height, "testing height of empty tree");
		treeStringsEmptyConstructor.add("Balin");
		height = treeStringsComparator.height();
		assertEquals(1, height, "testing height of tree with one element");
		treeStringsEmptyConstructor.add("Legolas");
		height = treeStringsComparator.height();
		assertEquals(2, height, "testing height of tree with two elements");
		treeStringsEmptyConstructor.add("Gandalf");
		height = treeStringsComparator.height();
		assertEquals(3, height, "testing height of tree with three elements all bigger than root");
		treeStringsEmptyConstructor.add("Aragon");
		height = treeStringsComparator.height();
		assertEquals(3, height, "testing height of four elements");

		// EMPTY CONSTRUCTOR INTEGERS
		height = treeIntegersEmptyConstructor.height();
		assertEquals(0, height, "testing height of empty tree");
		treeIntegersEmptyConstructor.add(10);
		height = treeIntegersEmptyConstructor.height();
		assertEquals(1, height, "testing height of tree with one element");
		treeIntegersEmptyConstructor.add(20);
		height = treeIntegersEmptyConstructor.height();
		assertEquals(2, height, "testing height of tree with two elements");
		treeIntegersEmptyConstructor.add(15);
		height = treeStringsEmptyConstructor.height();
		assertEquals(3, height, "testing height of tree with three elements all bigger than root");
		treeIntegersEmptyConstructor.add(5);
		height = treeIntegersEmptyConstructor.height();
		assertEquals(3, height, "testing height of four elements");

		// COMPARATOR INTEGERS
		height = treeIntegersComparator.height();
		assertEquals(0, height, "testing height of empty tree");
		treeIntegersComparator.add(10);
		height = treeIntegersComparator.height();
		assertEquals(1, height, "testing height of tree with one element");
		treeIntegersComparator.add(20);
		height = treeIntegersComparator.height();
		assertEquals(2, height, "testing height of tree with two elements");
		treeIntegersComparator.add(15);
		height = treeStringsComparator.height();
		assertEquals(3, height, "testing height of tree with three elements all bigger than root");
		treeIntegersComparator.add(5);
		height = treeIntegersComparator.height();
		assertEquals(3, height, "testing height of four elements");

	}

	/**
	 * Test adding two Integer elements
	 */
	@Test
	void testAddIntegers() {
		// EMPTY CONSTRUCTOR
		assertEquals(0,treeIntegersEmptyConstructor.size(), "testing size with empty tree");
		boolean added = treeIntegersEmptyConstructor.add(10);
		assertEquals(true, added, "Added 10");
		assertEquals(1, treeIntegersEmptyConstructor.size(), "Testing size() of size 1");
		added = treeIntegersEmptyConstructor.add(50);
		assertEquals(true, added, "Added 50");
		assertEquals(2, treeIntegersEmptyConstructor.size(), "Testing size() of size 2");

		// COMPARATOR
		assertEquals(0,treeIntegersComparator.size(), "testing size with empty tree");
		added = treeIntegersComparator.add(10);
		assertEquals(true, added, "Added 10");
		assertEquals(1, treeIntegersComparator.size(), "Testing size() of size 1");
		added = treeIntegersComparator.add(50);
		assertEquals(true, added, "Added 50");
		assertEquals(2, treeIntegersComparator.size(), "Testing size() of size 2");
	}

	/**
	 * Test adding two equal Strings
	 */
	@Test
	void testAddTwoEqualStrings() {
		// EMPTY CONSTRUCTOR
		boolean added = treeStringsEmptyConstructor.add("Balin");
		assertEquals(true, added, "Added string Balin");
		assertEquals(1, treeStringsEmptyConstructor.size(), "Testing size() of size 1");
		added = treeStringsEmptyConstructor.add("Balin");
		assertEquals(false, added, "Added string Balin");
		assertEquals(1, treeStringsEmptyConstructor.size(), "Testing size() of size 1");

		// COMPARATOR
		added = treeStringsComparator.add("Balin");
		assertEquals(true, added, "Added string Balin");
		assertEquals(1, treeStringsComparator.size(), "Testing size() of size 1");
		added = treeStringsComparator.add("Balin");
		assertEquals(false, added, "Added string Balin");
		assertEquals(1, treeStringsComparator.size(), "Testing size() of size 1");

	}

	/**
	 * Test adding two equal Integers
	 */
	@Test
	void testAddTwoEqualIntegers() {
		// EMPTY CONSTRUCTOR
		boolean added = treeIntegersEmptyConstructor.add(10);
		assertEquals(true, added, "Added 10");
		assertEquals(1, treeIntegersEmptyConstructor.size(), "Testing size() of size 1");
		added = treeIntegersEmptyConstructor.add(10);
		assertEquals(false, added, "Added 10");
		assertEquals(1, treeIntegersEmptyConstructor.size(), "Testing size() of size 1");

		// COMPARATOR
		added = treeIntegersComparator.add(10);
		assertEquals(true, added, "Added 10");
		assertEquals(1, treeIntegersComparator.size(), "Testing size() of size 1");
		added = treeIntegersComparator.add(10);
		assertEquals(false, added, "Added 10");
		assertEquals(1, treeIntegersComparator.size(), "Testing size() of size 1");

	}
	
	/**
	 * Test clear() method
	 */
	@Test
	void testClear() {
		//STRINGS
		
		//EMPTY CONSTRUCTOR
		treeStringsEmptyConstructor.add("Balin");
		treeStringsEmptyConstructor.add("Legolas");
		treeStringsEmptyConstructor.clear();
		assertEquals(0, treeStringsEmptyConstructor.size(), "Testing clear");
		
		//COMPARATOR
		treeStringsComparator.add("Balin");
		treeStringsComparator.add("Legolas");
		treeStringsComparator.clear();
		assertEquals(0, treeStringsComparator.size(), "Testing clear");
		
		//INTEGERS
		
		//EMPTY CONSTRUCTOR
		treeIntegersEmptyConstructor.add(10);
		treeIntegersEmptyConstructor.add(50);
		treeIntegersEmptyConstructor.clear();
		assertEquals(0, treeIntegersEmptyConstructor.size(), "Testing clear");
		
		//COMPARATOR
		treeIntegersComparator.add(10);
		treeIntegersComparator.add(50);
		treeIntegersComparator.clear();
		assertEquals(0, treeIntegersComparator.size(), "Testing clear");
		
	
	}

}
