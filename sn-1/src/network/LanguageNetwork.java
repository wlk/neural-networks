package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class LanguageNetwork {

	static int numIterations = 40;
	static int numTests = 1000;
	static final int numChars = 26;
	static int numHiddenLayer = 16;
	static final int numOutputLayer = 8;
	static double learnRate = 0.2;
	static double mommentum = 0.8;
	static double positiveThreshold = 0.90;
	static double errorThreshold = 0.90;

	DataInputStream in;
	BufferedReader br;
	FileInputStream fstream = null;
	Network network;
	double globalLearnInput[][][];
	double globalIdeal[][];

	public static void main(String args[]) {
		if (args[0] != null)
			numTests = Integer.parseInt(args[0]);
		if (args[1] != null)
			numHiddenLayer = Integer.parseInt(args[1]);
		//if (args[2] != null)
		//	positiveThreshold = Double.parseDouble(args[2]);
		if (args[3] != null)
			errorThreshold = Double.parseDouble(args[3]);
		if (args[4] != null)
			numIterations = Integer.parseInt(args[4]);
		if (args[5] != null)
			learnRate =  Double.parseDouble(args[5]);
		if (args[6] != null)
			mommentum =  Double.parseDouble(args[6]);

		LanguageNetwork ln = new LanguageNetwork();

		ln.readFiles("wikipedia-pl-out.txt", 0);
		ln.readFiles("wikipedia-en-out.txt", 1);
		ln.readFiles("wikipedia-de-out.txt", 2);
		ln.readFiles("wikipedia-cs-out.txt", 3);
		ln.readFiles("wikipedia-sk-out.txt", 4);
		ln.readFiles("wikipedia-simple-out.txt", 5);
		ln.readFiles("wikipedia-fr-out.txt", 6);
		ln.readFiles("wikipedia-es-out.txt", 7);

		System.err.println("learning languages");
		
		for(int iter = 0; iter < numIterations; ++iter){//which of 2 conditions is achieved earlier
			for (int i = 0; i < numTests - 1; ++i) {
				for (int j = 0; j < numOutputLayer; ++j) {
					ln.learn(j, i, i + 1);
				}
			}
			if(iter%1000 == 0){
				System.out.print("iter: " + iter + ", ");
				ln.printError();
			}
			//System.out.print("iter: " + iter + ", ");
			//ln.printError();
			//if(ln.getError() < errorThreshold){
			//	break;
			//}
			if(iter > numIterations - 2){
				ln.printError();
			}
			ln.getError();
		}
		
		

		System.err.println("running tests pl");
		ln.test("test-wikipedia-pl-out.txt", 0);
		System.err.println("running tests en");
		ln.test("test-wikipedia-en-out.txt", 1);
		System.err.println("running tests de");
		ln.test("test-wikipedia-de-out.txt", 2);
		System.err.println("running tests cs");
		ln.test("test-wikipedia-cs-out.txt", 3);
		System.err.println("running tests sk");
		ln.test("test-wikipedia-sk-out.txt", 4);
		System.err.println("running tests simple");
		ln.test("test-wikipedia-simple-out.txt", 5);
		System.err.println("running tests fr");
		ln.test("test-wikipedia-fr-out.txt", 6);
		System.err.println("running tests es");
		ln.test("test-wikipedia-es-out.txt", 7);
	}

	private void printError() {
		System.out.println(network.getGlobalErrorNoReset(numTests*numOutputLayer*numOutputLayer));
	}
	
	private double getError(){
		return network.getGlobalError(numTests*numOutputLayer*numOutputLayer);
	}

	private void readFiles(String file, int lang) {

		double learnInput[][] = generateLearnInput(file);
		globalLearnInput[lang] = learnInput;

		double ideal[] = generateIdeal(lang);
		globalIdeal[lang] = ideal;
	}

	private void test(String filename, int lang) {
		double testInput[][] = generateLearnInput(filename);
		int results[] = new int[numOutputLayer];
		for (int i = 0; i < testInput.length; i++) {
			double out[] = network.getOutputs(testInput[i]);
			// printing out csv of field that shout be 1.0 and all values of
			// fields
			int max_j = 0;
			double max_val = out[0];
			for(int j = 0; j < out.length; ++j){
				//select option with maximum output
				if(out[j] > max_val){
					max_val = out[j];
					max_j = j;
				}
				//if(out[j] > positiveThreshold){
				//	out[j] = 1;
				//	++results[j]; 
				//}
				//else{ 
				//	out[j] = 0;
				//}
//				System.out.println("lang:" + lang + "," + out[0] + "," + out[1] + ","
//						+ out[2] + "," + out[3] + "," + out[4] + "," + out[5] + ","
//						+ out[6] + "," + out[7]);
			}
			
			++results[max_j]; 
		}
		System.out.println("lang" + lang + ":" + results[0] + "," + results[1] + ","
				+ results[2] + "," + results[3] + "," + results[4] + "," + results[5] + ","
				+ results[6] + "," + results[7]);
		
	}

	public LanguageNetwork() {
		network = new Network(numChars, numHiddenLayer, numOutputLayer, learnRate,
				mommentum);
		globalLearnInput = new double[numOutputLayer][numTests][numChars];
		globalIdeal = new double[numOutputLayer][numOutputLayer];
	}

	private void learn(int lang, int start, int end) {
		//System.err.println("learning");
		for (int i = start; i < end; i++) {
			//double[] out = 
				network.getOutputs(globalLearnInput[lang][i]);
			network.learn(globalIdeal[lang]);
//			System.out.println("lang:" + lang + "," + out[0] + "," + out[1] + ","
//					+ out[2] + "," + out[3] + "," + out[4] + "," + out[5] + ","
//					+ out[6] + "," + out[7]);
		}

	}

	private double[] generateIdeal(int language) {
		System.err.println("generating ideal");
		double[] out = new double[numOutputLayer];
		//only field 'language' in array out should have value 1.0, others should be 0.0
		out[language] = 1.0;
		return out;
	}

	private double[][] generateLearnInput(String file) {

		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		KeyValue[][] t = readInputFile();
		double[][] out = new double[numTests][numChars];
		System.err.println("generating learn input");
		for (int i = 0; i < t.length; ++i) {
			for (int j = 0; j < t[i].length; ++j) {

				if (t[i][j] != null) {
					out[i][j] = t[i][j].getProb();
				} else {
					out[i][j] = 0.0;
					System.err.println("out[i][j] = 0.0;");
				}
				// System.err.print(" " + j + ", " + out[i][j]);
			}
			 //System.err.println();
		}
		System.err.println("finished generating learn input");
		return out;
	}

	private KeyValue[][] readInputFile() {
		System.err.println("reading input file");
		ArrayList<KeyValue> kv;
		KeyValue[][] kvv = new KeyValue[numTests][numChars];
		String strLine;
		String tokens[];
		String kvvv[];
		String character;
		double val;
		int trail = 0;
		try {
			while ((strLine = br.readLine()) != null && trail < numTests) {
				strLine = strLine.trim();

				tokens = strLine.split(" ");
				if (tokens.length == numChars) {
					kv = new ArrayList<KeyValue>();

					for (String s : tokens) {
						kvvv = s.split(",");
						if (kvvv.length == 2) {
							val = Double.parseDouble(kvvv[1]);
							character = kvvv[0];
							kv.add(new KeyValue(character, val));
							// System.err.print(character + ", " + val + " ");
						} else {
							System.err.println("wrong length of kvvv: "
									+ kvvv.length);
						}
					}
					// System.err.println();

					Collections.sort(kv);
					kvv[trail] = kv.toArray(new KeyValue[0]);
					++trail;
				} else {
					System.err.println("wrong length of tokens: " + tokens.length);
				}
			}
			in.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("finished reading input file");
		return kvv;
	}
}