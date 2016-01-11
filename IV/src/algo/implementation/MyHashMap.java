package algo.implementation;

public class MyHashMap<K, V> {
	transient Entry<K, V>[] table;
	transient int size;
	int capacity = 16; //always 2^n
	
	public MyHashMap() {
		table = new Entry[capacity];
	}
	
	public V get(Object key) {
		if (key == null) {
			return getForNullKey();
		} else {
			int hashValue = hash(key.hashCode());
			int index = indexFor(hashValue);
			for (Entry<K, V> e = table[index]; e != null; e = e.next) {
				Object k;
				if (e.hash == hashValue && ((k = e.key) == key || k.equals(key))) {
					return e.value;
				}
			}
			return null;
		}
	}
	
	public V put(K key, V value) {
		if (key == null) {
			return putForNullKey(value);
		} else {
			int hashValue = hash(key.hashCode());
			int index = indexFor(hashValue);
			for (Entry<K, V> e = table[index]; e != null; e = e.next){
				Object k;
				if (e.hash == hashValue && ((k = e.key) == key || k.equals(key))) {
					V oldValue = e.value;
					e.value = value;
					return oldValue;
				}
			}
			addEntry(hashValue, key, value, index);
			return null;
		}
	}
	
	/************** helper ***************/
	private int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	private int indexFor(int hash) {
		return hash ^ (capacity - 1);
	}
	
	private void addEntry(int hashValue, K key, V value, int index) {
		Entry<K, V> e = table[index];
		table[index] = new Entry<K, V>(key, value, hashValue, e);
		//resize if needed
	}
	
	public V getForNullKey() {
		for (Entry<K, V> e = table[0]; e != null; e = e.next) {
			if (e.key == null) {
				return e.value;
			}
		}
		return null;
	}
	
	public V putForNullKey(V value) {
		for (Entry<K, V> e = table[0]; e != null; e = e.next) {
			if (e.key == null) {
				V oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}
		return null;
	}
	
	class Entry<K, V>{
		K key;
		V value;
		int hash;
		Entry<K, V> next;
		public Entry(K key, V value, int hash, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.hash = hash;
			this.next = next;
		}
	}
}
