package Model;

import java.util.ArrayList;

public class Layer {
	private ArrayList<Double> inputs = new ArrayList<>();// Lista sygna��w wej�ciowych warstwy
	private ArrayList<Double> outputs = new ArrayList<>();// Lista sygna��w wyj�ciowych warstwy
	private ArrayList<Perceptron> perceptrons = new ArrayList<>();// Lista perceptron�w
	private int inputCount;// inputCount - liczba wej�� w ka�dym z neuron�w warstwy
	private int perceptronCount;// perceptronCount - liczba neuron�w w warstwie
	private boolean bias;

	// --------konstruktor-----------------------
	public Layer(int inputCount, int perceptronCount, boolean bias) {
		this.inputCount = inputCount;
		this.perceptronCount = perceptronCount;
		this.bias = bias;
		for (int i = 0; i < inputCount; i++) {// inicjalizacja listy wej��
			inputs.add(0.0);
		}
		for (int i = 0; i < perceptronCount; i++) {
			outputs.add(0.0); // inicjalizacja listy wyj��
			perceptrons.add(new Perceptron(inputCount, bias)); // utworzenie perceptron�w
		}
	}

	// ----------------------------------------------------
	public void setInput(ArrayList<Double> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<Double> getOutputs() {
		return outputs;
	}

	// --------------------------------------------------
	public void calculate() {
	//przepisanie wejsc do perceptron�w
		for(int i=0; i<perceptronCount; i++){
			perceptrons.get(i).setInputs(inputs);//pod��czenie wej�� warstwy do wej�� percepr=trona
			perceptrons.get(i).Calculate();//przeliczenie perceptrona
			outputs.set(i, perceptrons.get(i).getOutput());//zapisanie wyj�cia perceptrona na wyjscie warstwy
		}
	}

	// --------------------------------------------------
	public void printLayer() {
		
		for (int i = 0; i < perceptronCount; i++) {
			System.out.println("perceptron "+ i + "- wejscia");
			perceptrons.get(i).printinputs();
			perceptrons.get(i).printWeights();
			System.out.println("wyjscie = " + perceptrons.get(i).getOutput());
			System.out.println();
		}
	}

		



}
