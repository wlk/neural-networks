package w;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class SOM2 {
	
	Layer inputLayer;
	Layer outputLayer;
	int deltaR, deltaC;
	static int numGenerations = 10;
	static final int numRows = 26;
	static final int numCols = 26;
	static int numVectors = 100;
	//int running_color = -3;
	int winningNeuronIndex_I = 0;
	int winningNeuronIndex_J = 0;

	static final double A = 0.3;
	
	public static void main(String[] args){
		if (args[0] != null)
			numGenerations = Integer.parseInt(args[0]);
		if (args[1] != null)
			numVectors = Integer.parseInt(args[1]);
		SOM2 som = new SOM2();
		som.run();
		
		
	}
	
	void run(){
		System.out.println("run start");

		BufferedImage img = null;
		int imgSize = 600;
		img= new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		//adding white bg
		for(int i = 0; i < imgSize; ++i){
			for(int j = 0; j < imgSize; ++j){
				img.setRGB(i, j, 0xffffff);
			}
		}
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/cs-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0xff0000, img); //red
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/de-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0x00ff00, img); //green
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/en-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0x0000ff, img); //blue
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/es-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0x00ffff, img); //cyan
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/fr-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0xff00ff, img); //magenta
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/pl-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0xffff00, img); //yellow
		
		inputLayer = new Layer(numRows, numCols);
		inputLayer.readInput("/home/wlangiewicz/sn-2/sk-100.txt");
		outputLayer = new Layer(numCols, numCols);
		train(numVectors, numGenerations, 0x808080, img); //grey
		
		
		
		
		try {
	    	File outputfile = new File("final-g" + numGenerations + "-v" + numVectors + "bc.png");
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("run end");

	}
	
	double getDistance(int outputI, int outputJ){
		double sum = 0;
		
		// calculating output for output neuron
		for(int j = 0; j < inputLayer.neurons.length; ++j){
			sum += Math.pow(inputLayer.neurons[j] - outputLayer.weights[outputI][outputJ], 2.0);
		}
		//System.out.println("unit: " + inputNeuronIndex + " prop " +  Math.sqrt(sum));
		return Math.sqrt(sum);	
	}
	
	/**
	 * propagate
	 * @return int winning neuron
	 */
	int getWinner(){
		double shortestDistance, distance;
		int winningNeuronIndex = 0;
		shortestDistance = Integer.MAX_VALUE;
		
		//for(int i = 0; i < inputLayer.neurons.length; ++i){
		for(int i = 0; i < outputLayer.weights.length; ++i){
			for(int j = 0; j < outputLayer.weights[i].length; ++j){
		
				distance = getDistance(i, j);
				
				//choosing neuron with smallest output difference
				if(distance < shortestDistance){
					winningNeuronIndex_I = i;
					winningNeuronIndex_J = j;
					shortestDistance = distance;
					//System.out.print("new winner: " + winningNeuronIndex);
				}
			}
		}
		//}
		//System.out.println("winner: " + winningNeuronIndex);
		return winningNeuronIndex;		
	}
	
	int updateWeights(){
		getWinner();
		
		++outputLayer.neuronsTest[winningNeuronIndex_I][winningNeuronIndex_J];
		
		int currentUpdateCount = 0;
		for(int i = 0; i < numRows; ++i){			
			for(int j = 0; j < numCols; ++j){
				if(isNeighborhood(i, j, winningNeuronIndex_I, winningNeuronIndex_J)){
					outputLayer.weights[i][j] += A*(inputLayer.neurons[i] - outputLayer.weights[i][j]);
					++currentUpdateCount;
					//for(int k = 0; k < outputLayer.weights[j].length; ++k){
						//outputLayer.weights[j][k] += A*(inputLayer.neurons[k] - outputLayer.weights[j][k]); //adjusting SOM weights (subtractive)
					//}
				}
			}
		}
		return currentUpdateCount;
	}
	
	boolean isNeighborhood(int r, int c, int wI, int wJ){
//		int row, col, dR1, dC1, dR2, dC2;
//		
//		row = (w) / numRows;
//		col = (w) % numRows;
//		
//		dR1 = Math.max(0, (row - deltaR));
//		dR2 = Math.min(numRows, (row + deltaR));
//		dC1 = Math.max(0, (col - deltaC));
//		dC2 = Math.min(numRows, (col + deltaC));
//		
//		return (((dR1 <= r) && (r <= dR2)) &&
//				((dC1 <= c) && (c <= dC2)));
		
		return Math.abs(wI - r) <= deltaR &&  Math.abs(wJ - c) <= deltaC;
		
	}
	
	void train(int NP, int generations, int color, BufferedImage img){
		int imgSize = 600;
		//double[][] test = new double[imgSize][imgSize];
		//double max_test = 0.0;
		/*BufferedImage image = null;
		BufferedImage image3 = null;
		
		image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		image3 = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		
		BufferedImage image4 = null;
		image4 = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		
		
		
		for(int i = 0; i < imgSize; ++i){
			for(int j = 0; j < imgSize; ++j){
				image.setRGB(i, j, 0x000000);
				image4.setRGB(i, j, 0xffffff);
				image3.setRGB(i, j, 0xffffff);
			}
		}
		*/
		for(int g = 0; g < generations; ++g){
			for(int i = 0; i < NP; ++i){
				deltaR = numRows / 2;
				deltaC = numRows / 2;
				
				inputLayer.setNewInput(i);
				
				//System.out.println("input: ");
				//for(int j = 0; j < inputs.outs.length; ++j){
				//	System.out.print(inputs.outs[j] + " ");
				//}
				//System.out.println();
				
				
				int time = 0;
				//printOutput();
				
				
				
				int upd;
				do{
					++time;
					upd = updateWeights();
					if(time % 10 == 0){
						//System.out.println("time " + time);
						deltaR = Math.max(0, deltaR -1);
						deltaC = Math.max(0, deltaC -1);
					}
					//System.out.println("upd: " + upd);
					//printOutput();
				}
				while(upd > 1);
				
				//final int neighbour = 4; //set to 1...6
				
				/*
				
				for(int j = 1; j < numRows; ++j){
					for(int k = 0; k < numCols; ++k){
						//image.setRGB(j, k, (int) (outputs.weights[j][k] * 255));
						//image.setRGB(400 * j / 26, 400 * k / 26, 255);
						
						//image.setRGB(400 * j / rows, 400 * k / numChars, 255);
						
						
						for(int l = -neighbour; l < neighbour; ++l){
							for(int m = -neighbour; m < neighbour; ++m){
								if((imgSize * j / numRows)+l >= 0 && (imgSize * k / numRows)+m <= imgSize-1 
								&& (imgSize * j / numRows)+l <= imgSize-1 && (imgSize * k / numRows)+m >= 0){
									int rgb = image.getRGB((imgSize * k / numRows) + m, (imgSize * j / numRows) + l);
									int new_rgb =  rgb + (int)(outputLayer.weights[j][k] * 0xf);
									int rgb2 = Math.max(rgb, new_rgb);
									image.setRGB((imgSize * k / numRows) + m, (imgSize * j / numRows) + l, Math.min(rgb2, 0xff));
									
									test[(imgSize * k / numRows) + m][(imgSize * j / numRows) + l] += outputLayer.weights[j][k];
									if(test[(imgSize * k / numRows) + m][(imgSize * j / numRows) + l] > max_test){
										max_test = test[(imgSize * k / numRows) + m][(imgSize * j / numRows) + l];
									}
									
								}
							}
						}
					}
				}
				
				for(int j = 0; j < numRows; ++j){
					int max_k = 0;
					double max_val = Double.MIN_VALUE;
					for(int k = 0; k < numRows; ++k){
						//iterate over all outputs.weights[j] to find greatest value in outputs.weights[j][k] to write it (only this value)
						
						if(outputLayer.weights[j][k] > max_val){
							max_k = k;
							max_val = outputLayer.weights[j][k];
						}
						
					}
					
					//System.err.println(max_val + " " + j + " " + max_k);
					
					for(int l = -neighbour; l < neighbour; ++l){
						for(int m = -neighbour; m < neighbour; ++m){
							if((imgSize * j / numRows)+l >= 0 && (imgSize * max_k / numRows)+m <= imgSize-1 
							&& (imgSize * j / numRows)+l <= imgSize-1 && (imgSize * max_k / numRows)+m >= 0){
								//int rgb = image3.getRGB((imgSize * j / rows) + l, (imgSize * max_k / numChars) + m);
								//if(rgb  - 0x000000 < 1){//hack to check if pixel has been previously written to????????????
								//	++d;
									image3.setRGB((imgSize * max_k / numRows) + m, (imgSize * j / numRows) + l, 0x000000);
								//}
								//else{
								//	++e;
								//	image3.setRGB((imgSize * j / rows) + l, (imgSize * max_k / numChars) + m, 0x000000);
								//}
							}
						}
					}
				}
				//System.out.println("--------- ");
				 * 
				 */
			}
		}
		
				
		int max_value = 0;
		for(int i = 0; i < numRows; ++i){
			for(int j = 0; j < numRows; ++j){
				if(max_value < outputLayer.neuronsTest[i][j]){
					max_value = outputLayer.neuronsTest[i][j];
				}
			}
		}
		
		System.err.println("max_value: " + max_value);
		
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(new Color(color));
		for(int i = 0; i < numRows; ++i){
			for(int j = 0; j < numRows; ++j){
				//avoid printing small circles
//			if(outputLayer.neuronsTest[i][j] == max_value){
				if(outputLayer.neuronsTest[i][j] > 0.5 * max_value){
					int x1 = (i*imgSize/numRows) ;//+ (running_color * 15);
					int y1 = (j*imgSize/numCols) ;// - (running_color * 15);
					Ellipse2D circle = new Ellipse2D.Float(x1, y1, (int)( outputLayer.neuronsTest[i][j] *  90.0 / max_value ), (int) (outputLayer.neuronsTest[i][j] * 90.0 / max_value));
					
					//g.fillOval(i*imgSize/numRows, j*imgSize/numCols, (int)( outputLayer.neuronsTest[i][j] *  50.0 / max_value ), (int) (outputLayer.neuronsTest[i][j] * 50.0 / max_value));
					//g.fill(circle);
					g.draw(circle);
				}
			}
		}
		
		//++running_color;
		
		
		
		
		//printTest();
		
//		for(int i = 0; i < imgSize; ++i){
//			for(int j = 0; j < imgSize; ++j){
//				int color2 = (int) ((test[i][j] / max_test) * 0xff);
//				image4.setRGB(i, j, color2);
//			}
//		}
		
		//ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
		//ColorConvertOp op = new ColorConvertOp(cs, null);  
		//BufferedImage image2 = op.filter(image, null); 
		
	   // try {
	    	//File outputfile = new File("saved.png");
			//ImageIO.write(image, "png", outputfile);
			
			//File outputfile2 = new File("saved-gray.png");
			//ImageIO.write(image2, "png", outputfile2);
			
			//File outputfile3 = new File("saved-3.png");
			//ImageIO.write(image3, "png", outputfile3);
			
			//File outputfile4 = new File("saved-test.png");
			//ImageIO.write(image4, "png", outputfile4);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}	
		
		//printOutput();
		
		
		
	}
	
	void printTest(){
		System.out.println("output test s");
		for(int i = 0; i < numRows; ++i){
			for(int j = 0; j < numCols; ++j){
				System.out.print(outputLayer.neuronsTest[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("output test end");
	}
	
	void printOutput(){
		System.out.println("------------");
		DecimalFormat df = new DecimalFormat("#.#####");
		for(int i = 0; i < numRows; ++i){
			for(int j = 0; j < numCols; ++j){
				System.out.print(df.format(outputLayer.weights[i][j]) + " ");
			}
			System.out.println();
		}
	}
}
