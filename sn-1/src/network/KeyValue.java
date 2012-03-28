package network;

public class KeyValue implements Comparable<KeyValue> {
	private String character;
	private double prob;

	public int compareTo(KeyValue a) {
		return  -a.getCharacter().compareTo(this.getCharacter()); //a...z
	}

	public KeyValue(String character, double prob) {
		this.character = character;
		this.prob = prob;
	}

	public String getCharacter() {
		return character;
	}

	public double getProb() {
		return prob;
	}
}