package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */

	public boolean offer(E e) {

		if (size == 0) {
			last = new QueueNode<E>(e); // If the queue is empty create a node for which .next references itself
			last.next = last;
		} else {
			QueueNode<E> oldLast = last; // Create a temp variable for the previous last node
			last = new QueueNode<E>(e); // Insert a new node in the back of the queue
			last.next = oldLast.next; // Set the new node to reference the oldest node (what old last did before)
			oldLast.next = last; // Set the second last node to reference the last node
		}

		size++;
		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if(size == 0) {
			return null;
		}
		return last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}

		QueueNode<E> head = last.next; // Get the oldest node of the queue

		if (size == 1) {
			last.next = null;
			last = null;
		} else {
			last.next = head.next; //Set last.next to reference the second oldest node
		}
		
		size--;
		return head.element;

	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return null;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
