package Model;

import java.util.ArrayList;

public class Layer {
	private ArrayList<Double> inputs = new ArrayList<>();// Lista sygna³ów wejœciowych warstwy
	private ArrayList<Double> outputs = new ArrayList<>();// Lista sygna³ów wyjœciowych warstwy
	private ArrayList<Perceptron> perceptrons = new ArrayList<>();// Lista perceptronów
	private int inputCount;// inputCount - liczba wejœæ w ka¿dym z neuronów warstwy
	private int perceptronCount;// perceptronCount - liczba neuronów w warstwie
	private boolean bias;

	// --------konstruktor-----------------------
	public Layer(int inputCount, int perceptronCount, boolean bias) {
		this.inputCount = inputCount;
		this.perceptronCount = perceptronCount;
		this.bias = bias;
		for (int i = 0; i < inputCount; i++) {// inicjalizacja listy wejœæ
			inputs.add(0.0);
		}
		for (int i = 0; i < perceptronCount; i++) {
			outputs.add(0.0); // inicjalizacja listy wyjœæ
			perceptrons.add(new Perceptron(inputCount, bias)); // utworzenie perceptronów
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
	//przepisanie wejsc do perceptronów
		for(int i=0; i<perceptronCount; i++){
			perceptrons.get(i).setInputs(inputs);//pod³¹czenie wejœæ warstwy do wejœæ percepr=trona
			perceptrons.get(i).Calculate();//przeliczenie perceptrona
			outputs.set(i, perceptrons.get(i).getOutput());//zapisanie wyjœcia perceptrona na wyjscie warstwy
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
