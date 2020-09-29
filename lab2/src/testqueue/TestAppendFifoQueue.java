package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueueA;
	private FifoQueue<Integer> myIntQueueB;
	
	@BeforeEach
	void setUp() throws Exception {
		myIntQueueA = new FifoQueue<Integer>();
		myIntQueueB  = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myIntQueueA = null;
		myIntQueueB = null;
	}

	/**
	 * Test to append an empty queue to itself
	 */
	@Test 
	void testToAppendEmptyQueueToItself() {
		assertThrows(IllegalArgumentException.class, () -> myIntQueueA.append(myIntQueueA));
	}
	
	/**
	 * Test to append non empty queue to itself
	 */
	@Test
	void testToAppendNonEmptyQueueToItself() {
		myIntQueueA.offer(1);
		assertThrows(IllegalArgumentException.class, () -> myIntQueueA.append(myIntQueueA));
	}
	
	/**
	 * Test to append empty queue with an other empty queue
	 */
	@Test
	void testToAppendEmptyWithEmpty() {
		myIntQueueA.append(myIntQueueB);
		assertEquals(0, myIntQueueB.size(), "Size of appended queue is not 0");
		assertEquals(0, myIntQueueA.size(), "Size is not 0 after append");	
	}
	
	/**
	 * Test to append empty with non empty queue
	 */
	@Test
	void testToAppendEmptyWithNonEmpty() {
		myIntQueueA.offer(1);
		myIntQueueA.offer(2);
		
		myIntQueueB.append(myIntQueueA);
		assertEquals(2, myIntQueueB.size(), "Size of appended queue is wrong");
		assertEquals(0, myIntQueueA.size(), "Size is not 0 after append");
		
		int a = myIntQueueB.poll();
		assertEquals(1, a, "Got " + a + " expected " + 1);
		a = myIntQueueB.poll();
		assertEquals(2, a, "Got " + a + " expected " + 2);
	}
	
	/**
	 * Test to append non empty queue with empty
	 */
	@Test
	void testToAppendNonEmptyWithEmpty() {
		myIntQueueA.offer(1);
		myIntQueueA.offer(2);
		
		myIntQueueA.append(myIntQueueB);
		assertEquals(2, myIntQueueA.size(), "Size of appended queue is wrong");
		assertEquals(0, myIntQueueB.size(), "Size is not 0 after append");
		
		int a = myIntQueueA.poll();
		assertEquals(1, a, "Got " + a + " expected " + 1);
		a = myIntQueueA.poll();
		assertEquals(2, a, "Got " + a + " expected " + 2);
	}
	
	
	/**
	 * Test to append non empty queues
	 */
	@Test
	void testToAppendNonEmptyQueue() {
		myIntQueueA.offer(1);
		myIntQueueA.offer(2);
		myIntQueueB.offer(3);
		myIntQueueB.offer(4);
		
		myIntQueueA.append(myIntQueueB);
		
		assertEquals(4, myIntQueueA.size(), "Size of appended queue is wrong");
		assertEquals(0, myIntQueueB.size(), "Size is not 0 after append");
		
		for(int i = 1; i <= 4; i++) {
			int k = myIntQueueA.poll();
			assertEquals(i, k, "Expected " + k);
		}
						
	}
	

}
