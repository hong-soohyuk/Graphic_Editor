package main;

import java.io.Serializable;

public final class Pair implements Serializable{
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	private final Object first;
	private final Object second;
	public Pair(Object first, Object second) {
		this.first = first;
		this.second = second;
	}
	public Object getFirst() {
		return first;
	}
	public Object getSecond() {
		return second;
	}
}
