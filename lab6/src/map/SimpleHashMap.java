package map;


public class SimpleHashMap<K, V> implements Map<K, V> {

	private Entry<K, V>[] table;
	private float loadFactor;
	private final int initial_capacity = 16;
	private int currentCapacity;
	private int size;

	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		this.table = (Entry<K, V>[]) new Entry[initial_capacity];
		this.loadFactor = 0.75f;
		this.size = 0;
		this.currentCapacity = initial_capacity;
	}

	@SuppressWarnings("unchecked")
	public SimpleHashMap(float loadFactor) {
		this.table = (Entry<K, V>[]) new Entry[initial_capacity];
		this.loadFactor = loadFactor;
		this.loadFactor = 0.75f;
		this.size = 0;
		this.currentCapacity = initial_capacity;
	}

	@Override
	public V get(Object arg0) {
		@SuppressWarnings("unchecked")
		K key = (K) arg0;
		Entry<K, V> entry = find(index(key), key);

		if (entry == null) {
			return null;
		} else {
			return entry.getValue();
		}
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);

		Entry<K, V> entry = find(index, key);
		V oldValue = null;

		if (entry != null) {
			oldValue = entry.setValue(value); // If entry was found means we found a key mapping to a value, replace
												// that value
		} else {
			Entry<K, V> oldFirst = table[index]; // Get the first entry in the bucket
			table[index] = new Entry<K, V>(key, value); // Put a new entry in that place
			table[index].next = oldFirst; // Link the new entry to the rest of the list
			size++; // Increase the size
		}

		if (((float) size / table.length) > this.loadFactor) {
			reHash();
		}

		return oldValue;

	}

	@SuppressWarnings("unchecked")
	private void reHash() {
		this.size = 0;
		Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[currentCapacity];
		System.arraycopy(table, 0, temp, 0, table.length);

		currentCapacity = currentCapacity * 2;
		table = (Entry<K, V>[]) new Entry[currentCapacity];

		for (int i = 0; i < temp.length; i++) {
			Entry<K, V> entry = temp[i];

			// loop through every bucket
			while (entry != null) {
				put(entry.getKey(), entry.getValue());
				entry = entry.next;
			}
		}
	}

	@Override
	public V remove(Object arg0) {
		@SuppressWarnings("unchecked")
		K key = (K) arg0;
		int index = index(key);
		Entry<K,V> entry = table[index];
		V value;
		
		if(entry == null) { // if its an empty list in the bucket
			
			return null;
		
		} else if(entry.key.equals(key)) { //the first element
			
			value = entry.getValue();
			table[index] = entry.next;		//Set the first element in the bucket to be the second one or null
			size--;
			return value;
		
		} else {
			Entry<K,V> prevEntry = entry;
			
			while(entry != null && !entry.getKey().equals(key)) {
				prevEntry = entry;
				entry = entry.next;
			}
			
			if(entry == null) {
				return null;
			} else {
				value = entry.getValue();
				prevEntry.next = entry.next;			//link over the gap
				size--;
				return value;
			}
		}
		
	}

	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Print all the content in each bucket
	 */
	public void show() {
		StringBuilder builder = new StringBuilder();

		// loop through all buckets
		for (int i = 0; i < table.length; i++) {
			builder.append(i);

			// get the first element of the bucket
			Entry<K, V> entry = table[i];

			// loop through the content of the bucket
			while (entry != null) {
				builder.append(" ");
				builder.append(entry.toString());
				entry = entry.next;
			}

			builder.append("\n");
		}

		System.out.println(builder.toString());
	}

	private int index(K key) {
		return (key.hashCode() & 0x7FFFFFFF) % table.length;
	}

	private Entry<K, V> find(int index, K key) {
		// get the first element in the bucket
		Entry<K, V> entry = table[index];

		// loop through the bucket
		while (entry != null && !entry.getKey().equals(key)) {
			entry = entry.next;
		}

		return entry;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;
		private Entry<K, V> next = null;

		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public String toString() {
			return this.key.toString() + "=" + this.value.toString();
		}

	}

}
