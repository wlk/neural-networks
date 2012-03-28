package network;

public class Network {

	private double learningRate, momentum, globalError;

	private int numInput, numHidden, numOutput, numNeuron, weightSum;

	private double x[], weigthMatrix[], error[], deltaMatrixDelta[],
			thresholds[], matrixDelta[], deltaThresholdsDelta[],
			thresholdsDelta[], errorDelta[];

	public Network(int numInput, int numHidden, int numOutput,
			double learnRate, double momentum) {
		this.globalError = 1.0;
		this.learningRate = learnRate;
		this.momentum = momentum;
		this.numInput = numInput;
		this.numHidden = numHidden;
		this.numOutput = numOutput;

		numNeuron = numInput + numHidden + numOutput;
		weightSum = (numInput * numHidden) + (numHidden * numOutput);

		x = new double[numNeuron];
		weigthMatrix = new double[weightSum];
		matrixDelta = new double[weightSum];
		thresholds = new double[numNeuron];
		errorDelta = new double[numNeuron];
		error = new double[numNeuron];
		deltaThresholdsDelta = new double[numNeuron];
		deltaMatrixDelta = new double[weightSum];
		thresholdsDelta = new double[numNeuron];

		for (int i = 0; i < numNeuron; i++) {
			thresholds[i] = Math.random();
			thresholdsDelta[i] = 0;
			deltaThresholdsDelta[i] = 0;
		}
		for (int i = 0; i < weigthMatrix.length; i++) {
			weigthMatrix[i] = Math.random();
			matrixDelta[i] = 0;
			deltaMatrixDelta[i] = 0;
		}
	}
	
	public double getGlobalErrorNoReset(int testLength){
		return Math.sqrt(globalError / (testLength));
	}
	
	private void resetError(){
		globalError = 0.0;
	}

	public double getGlobalError(int testLength) {
		double ret = getGlobalErrorNoReset(testLength);
		resetError();
		return ret;
	}

	private double getThreshold(double sum) {
		// return Math.tanh(sum);
		return 1.0 / (1 + Math.exp(-2.0 * sum));
	}
	
	/*
	 * page 102 in 'Neural Networks - Algorithms, Applications And Programming Techniques'
	 */

	public double[] getOutputs(double input[]) {
		int i, j;
		int hiddenIndex = numInput;
		int outIndex = numInput + numHidden;
		double result[] = new double[numOutput];

		for (i = 0; i < numInput; i++) {
			x[i] = input[i];
		}

		int matrixIndex = 0;
		// calculating threshold values for first layer
		for (i = hiddenIndex; i < outIndex; i++) {
			double sum = thresholds[i];
			for (j = 0; j < numInput; j++) {
				sum += x[j] * weigthMatrix[matrixIndex++]; // 3.1
			}
			x[i] = getThreshold(sum); //3.2
		}

		// calculating output from hidden layer
		for (i = outIndex; i < numNeuron; i++) {
			double sum = thresholds[i];
			for (j = hiddenIndex; j < outIndex; j++) {
				sum += x[j] * weigthMatrix[matrixIndex++]; //3.3
			}
			x[i] = getThreshold(sum); // 3.4
			result[i - outIndex] = x[i]; // assigning values to result array
		}

		return result;
	}

	public void learn(double ideal[]) {
		int hiddenIndex = numInput;
		int outputIndex = numInput + numHidden;

		calcuateErrors(ideal, hiddenIndex, outputIndex);

		calculateDeltas(hiddenIndex, outputIndex);

		for (int i = 0; i < weigthMatrix.length; i++) {
			matrixDelta[i] = (momentum * matrixDelta[i])
					+ (learningRate * deltaMatrixDelta[i]);
			weigthMatrix[i] += matrixDelta[i];
			deltaMatrixDelta[i] = 0;
		}

		for (int i = numInput; i < numNeuron; i++) {
			thresholdsDelta[i] = (momentum * thresholdsDelta[i]) //3.11, 3.12
					+ (learningRate * deltaThresholdsDelta[i]);
			thresholds[i] += thresholdsDelta[i];
			deltaThresholdsDelta[i] = 0;
		}

	}

	private void calcuateErrors(double[] ideal, int hiddenIndex, int outputIndex) {
		for (int i = numInput; i < numNeuron; i++) {
			error[i] = 0;
		}

		for (int i = outputIndex; i < numNeuron; i++) {
			error[i] = ideal[i - outputIndex] - x[i]; // calculating exact error, 3.6, 3.7
			globalError += error[i] * error[i];
			errorDelta[i] = error[i] * x[i] * (1 - x[i]); // calculating error
															// delta
		}
	}

	private void calculateDeltas(int hiddenIndex, int outputIndex) {
		int weigthMatrixIndex = numInput * numHidden;

		for (int i = outputIndex; i < numNeuron; i++) {
			for (int j = hiddenIndex; j < outputIndex; j++) {
				deltaMatrixDelta[weigthMatrixIndex] += errorDelta[i] * x[j];
				error[j] += weigthMatrix[weigthMatrixIndex] * errorDelta[i];
				weigthMatrixIndex++;
			}
			deltaThresholdsDelta[i] += errorDelta[i];
		}

		for (int i = hiddenIndex; i < outputIndex; i++) {
			errorDelta[i] = error[i] * x[i] * (1 - x[i]);
		}

		weigthMatrixIndex = 0;
		for (int i = hiddenIndex; i < outputIndex; i++) {
			for (int j = 0; j < hiddenIndex; j++) {
				deltaMatrixDelta[weigthMatrixIndex] += errorDelta[i] * x[j];
				error[j] += weigthMatrix[weigthMatrixIndex] * errorDelta[i];
				weigthMatrixIndex++;
			}
			deltaThresholdsDelta[i] += errorDelta[i];
		}
	}
}