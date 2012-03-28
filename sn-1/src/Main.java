import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		if (args.length >= 3) {
			Main m = new Main();
			m.run(args);

		}
	}
	
	protected void run(String[] args){
		ArrayList<KeyValue> kv = new ArrayList<KeyValue>();
		long sum = 0;
		String[] tokens;
		long val = 0;
		try {
			FileInputStream fstream = new FileInputStream(args[1]);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				tokens = strLine.split(" ");
				val = Long.parseLong(tokens[1]);
				sum += val;
				kv.add(new KeyValue(tokens[0], val));
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		for(KeyValue k: kv){
			k.setAvg(k.getCount()/sum);
		}
		
		for(KeyValue k: kv){
			System.out.println(k.getCharacter() + "," + k.getAvg());
		}
	}
	
	
	public class KeyValue{
		private String character;
		private long count;
		private double avg;
		
		public KeyValue(String character, long count){
			this.character = character;
			this.count = count;
		}
		
		public void setCharacter(String character) {
			this.character = character;
		}
		public String getCharacter() {
			return character;
		}
		public void setCount(long count) {
			this.count = count;
		}
		public long getCount() {
			return count;
		}

		public void setAvg(double avg) {
			this.avg = avg;
		}

		public double getAvg() {
			return avg;
		}
	}

}
