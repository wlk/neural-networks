package w;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Layer {
	double[] neurons;
	double[][] weights;
	int[][] neuronsTest;
	
	private double[][] inputOuts;
	
	public Layer(int lengthOuts, int lengthWeights) {
		// generate Layer with random data
		neurons = new double[lengthOuts];
		for (int i = 0; i < neurons.length; ++i) {
			neurons[i] = Math.random();
		}
		
		inputOuts = new double[320][26];
		
		weights = new double[lengthWeights][lengthWeights];
		for (int i = 0; i < weights.length; ++i) {
			for (int j = 0; j < weights[i].length; ++j) {
				weights[i][j] = Math.random();
				
			}
		}
		
		neuronsTest = new int[333][333];
		for (int i = 0; i < 333; ++i) {
			for (int j = 0; j < 333; ++j) {
				neuronsTest[i][j] = 0;
				
			}
		}
	}
	
	public void setNewInput(int i){
		//normalize input first
		double sum = 0.0;
		
		
		neurons = inputOuts[i];
		
		for(int j = 0; j < neurons.length; ++j){
			//outs[j] /= factor;
			sum += Math.pow(neurons[j], 2.0);
		}
		
		double factor = 1.0 / Math.sqrt(sum);
		
		for(int j = 0; j < neurons.length; ++j){
			neurons[j] *= factor;
		}
	}

	// call only for input layer - to read data from file named filename
	public void readInput(String filename) {
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = null;
			String[] tokens;
			int i = 0;
			while ((strLine = br.readLine()) != null) {
				strLine = strLine.trim();

				tokens = strLine.split(" ");
				for(int j = 0; j < SOM.numRows; ++j){
					inputOuts[i][j] = Double.parseDouble(tokens[j].split(",")[1]);
				}
				
				++i;
				

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
