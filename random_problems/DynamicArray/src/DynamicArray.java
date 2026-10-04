/**
 * author: marcavenzaid
 * created: Nov 11, 2018
 */

@SuppressWarnings("unchecked")
public class DynamicArray<T> {
	
	private T[] a;
	private int n;
	private int capacity;
	
	public DynamicArray() {
		this(16);
	}
	
	public DynamicArray(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		this.capacity = capacity;
		a = (T[]) new Object[capacity];
	}
	
	public int size() {
		return a.length;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public T get(int index) {
		return a[index];
	}
	
	public void set(int index, T element) {
		a[index] = element;
	}
	
	public void clear() {
		for (int i = 0; i < capacity; ++i) {
			a[i] = null;
		}
		n = 0;
	}
	
	public void add(T element) {
		if (n + 1 > capacity) {
			capacity = (capacity == 0) ? 1 : capacity * 2;
			T[] newA = (T[]) new Object[capacity];
			copyElements(a, 0, newA, 0, n);
			a = newA;
		}
		a[n++] = element;
	}
	
	public T removeAt(int index) {
		if (index < 0 || index >= capacity) {
			throw new IndexOutOfBoundsException();
		}
		T element = a[index];
		T[] newA = (T[]) new Object[n - 1];
		copyElements(a, 0, newA, 0, index);
		copyElements(a, index + 1, newA, index, n - index - 1);
		a = newA;
		capacity = --n;
		return element;
	}
	
	// srcBegin (inclusive), srcEnd (exclusive).
	private void copyElements(T[] src, int srcBegin, T[] dest, int destBegin, int length) {
		for (int i = 0; i < length; ++i) {
			dest[destBegin++] = src[srcBegin + i];
		}
	}
	
	
	public String toString() {
		if (n == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder(n + 2);
		sb.append("[");
		for (int i = 0; i < n - 1; ++i) {
			sb.append(a[i] + ", ");
		}
		return sb.append(a[n - 1] + "]").toString();
	}
}
