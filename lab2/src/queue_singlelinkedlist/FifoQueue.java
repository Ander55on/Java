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
		if (size == 0) {
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
			last.next = head.next; // Set last.next to reference the second oldest node
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
		return new QueueIterator();
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue is empty after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (this.equals(q)) {
			throw new IllegalArgumentException();
		}

		if (this.size == 0) {
			this.last = q.last;
		} else if (q.size != 0) {
			// Retrieve the old last node and the first node
			QueueNode<E> oldLast = this.last;
			QueueNode<E> first = this.last.next;

			// Link the old last node with the first of the appended list
			oldLast.next = q.last.next;

			// Set the last node to be the last node of the appended list
			this.last = q.last;

			// Link the last node to the first node
			last.next = first;
		}

		this.size = this.size + q.size;
		q.size = 0;
	}

	private class QueueIterator implements Iterator<E> {

		private QueueNode<E> pos;
		private QueueNode<E> end;

		public QueueIterator() {
			pos = last;
		}

		@Override
		public boolean hasNext() {
			// Empty queue
			if (pos == null) {
				return false;
			}

			return !pos.next.equals(end);
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			E e = pos.next.element; // Retrieve the next element
			pos = pos.next; // Move the pointer

			// If The pointer is at the start of the queue set that node to be the end
			if (pos.equals(last.next)) {
				end = pos;
			}

			return e;
		}

	}

}
