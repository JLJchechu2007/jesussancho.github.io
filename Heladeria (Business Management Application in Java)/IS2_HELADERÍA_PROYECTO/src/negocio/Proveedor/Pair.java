package negocio.Proveedor;

public class Pair<K, V> {
	private K first;
    private V second;

	public Pair(K first, V second) {
	    this.setFirst(first);
	    this.setSecond(second); 
	}

	public K getFirst() {
		return first;
	}

	public void setFirst(K first) {
		this.first = first;
	}

	public V getSecond() {
		return second;
	}

	public void setSecond(V second) {
		this.second = second;
	}
}
