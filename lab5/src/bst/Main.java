package bst;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BSTVisualizer visualizer = new BSTVisualizer("Binary", 500, 500);
		
		BinarySearchTree<Integer> balancedTree = new BinarySearchTree<>();
	
		balancedTree.add(10);
		balancedTree.add(20);
		balancedTree.add(5);
		balancedTree.add(15);
		balancedTree.add(30);
		balancedTree.add(2);
		balancedTree.add(7);
		
		//visualizer.drawTree(balancedTree);
		
		BinarySearchTree<Integer> worstCaseTree = new BinarySearchTree<>();
	
		worstCaseTree.add(10);
		worstCaseTree.add(20);
		worstCaseTree.add(30);
		worstCaseTree.add(40);
		worstCaseTree.add(50);
		worstCaseTree.add(60);	
		
		//visualizer.drawTree(worstCaseTree);
		
		
		BinarySearchTree<String> stringTree = new BinarySearchTree<>();
		stringTree.add("Balin");
		stringTree.add("Legolas");
		stringTree.add("Gandalf");
		stringTree.add("Frodo");
		stringTree.add("Sam");
		
		//visualizer.drawTree(stringTree);
		
		BinarySearchTree<Integer> skevt = new BinarySearchTree<>();
		
		skevt.add(5);
		skevt.add(13);
		skevt.add(3);
		skevt.add(1);
		skevt.add(9);
		skevt.add(11);
		skevt.add(7);
		
		skevt.rebuild();
		visualizer.drawTree(skevt);
		
	}

}
