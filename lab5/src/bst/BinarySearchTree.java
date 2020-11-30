package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;
	private boolean addReturn;
	
	/**
	 * Constructs an empty binary search tree.
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchTree () {
		this.root = null;
		this.size = 0;
		comparator = (x, y) -> ((Comparable<E>)x).compareTo(y);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.root = null;
		this.size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
	/*	if(root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		return add(root, x, null, -1); */
		
		this.root = add(root, x);
		return addReturn;
	}
	
	private BinaryNode<E> add(BinaryNode<E> n, E x) {
		
		if(n == null) {
			addReturn = true;
			size++;
			return new BinaryNode<E>(x);
		}
				
		int result = comparator.compare(x, n.element);
		
		if(result == 0) {
			addReturn = false;
			
		} else if(result < 0) {
			n.left = add(n.left, x);
			
		} else {
			n.right = add(n.right,x);			
		}
		
		 return n;			 
	}

	@SuppressWarnings("unchecked")
	private boolean add(BinaryNode<E> n, E x, BinaryNode<E> parent, int res) {

		if (n == null) {	
			 if(res < 0) {
				parent.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				parent.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
		}

		int result = 0;

		if (comparator == null) {
			result = ((Comparable<E>) x).compareTo(n.element);
		} else {
			result = comparator.compare(x, n.element);
		}

		if (result == 0) {
			return false;
		} else if (result < 0) {
			return add(n.left, x, n, result);
		} else {
			return add(n.right, x, n, result);
		}

	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> node) {

		if (node == null) {
			return 0;
		}

		return 1 + Math.max(height(node.left), height(node.right));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> arr = new ArrayList<>();
	    toArray(root, arr);
	    this.root =  buildTree(arr, 0, arr.size() - 1); // build on the root
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
			if(n != null) {
				toArray(n.left, sorted);
				sorted.add(n.element);
				toArray(n.right, sorted);
			}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		
		
		if(first > last) {
			return null;
		}
		
		int mid = first + (last - first) / 2;
		
		BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
		node.left = buildTree(sorted, first, mid - 1);
		node.right = buildTree(sorted, mid + 1, last);
		return node;

	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
			this.left = null;
			this.right = null;
		}
	}

}
