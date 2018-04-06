package Model;

import java.util.ArrayList;

public class Layer {
	private ArrayList<Double> inputs = new ArrayList<>();// Lista sygna��w wej�ciowych warstwy
	private ArrayList<Double> outputs = new ArrayList<>();// Lista sygna��w wyj�ciowych warstwy
	private ArrayList<Perceptron> perceptrons = new ArrayList<>();// Lista perceptron�w
	private ArrayList<Double> delta = new ArrayList<>();// lista b��d�w poszczeg�lnych perceptron�w
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
			outputs.add(0.0); // utworzenie element�w listy wyj��
			perceptrons.add(new Perceptron(inputCount, bias)); // utworzenie perceptron�w
			delta.add(0.0); // utworzenie element�w listy b��d�w
		}
	}

	// ----------------Getery i settery ------------------------------------
	public void setInput(ArrayList<Double> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<Double> getOutputs() {
		return outputs;
	}

	public int getinputCount() {
		return inputCount;
	}

	public int getPerceptronCount() {
		return perceptronCount;
	}

	public ArrayList<Double> getWeight(int number) { // zwr�cenie listy wag wejscia number wszystkich perceptron�w
		ArrayList<Double> weight = new ArrayList<>();// Lista wag wejscia number kolejnych perceptron�w
		for (int i = 0; i < perceptronCount; i++) {
			weight.add(perceptrons.get(i).getWeight().get(number));
		}
		return weight;
	}

	public ArrayList<Double> getDelta() {
		return delta;
	}

	public void setDelta(ArrayList<Double> delta) {
		this.delta = delta;
	}

	// ---------------przeliczenie sieci -----------------------------------
	public void calculate() {
		// przepisanie wejsc do perceptron�w
		for (int i = 0; i < perceptronCount; i++) {
			perceptrons.get(i).setInputs(inputs);// pod��czenie wej�� warstwy do wej�� percepr=trona
			perceptrons.get(i).Calculate();// przeliczenie perceptrona
			outputs.set(i, perceptrons.get(i).getOutput());// zapisanie wyj�cia perceptrona na wyjscie warstwy
		}
	}

	// ---------------przeliczenie wstecz b��du -----------------------------------
	// Na podstawie listy b��d�w i listy wag nast�pnej sieci wylicza b��dy
	// wszystkich perceptron�w
	// i zapisuje je do swojej listy b��d�w
	public void calculateDelta(Layer nextLayer) {
		ArrayList<Double> weight = new ArrayList<>();
		for (int i = 0; i < perceptronCount; i++) {
			double tmpDelta = 0;
			weight = nextLayer.getWeight(i);
			for (int j = 0; j < nextLayer.getPerceptronCount(); j++) {
				tmpDelta += weight.get(j) * nextLayer.getDelta().get(j);
			}
			delta.set(i, tmpDelta);
			perceptrons.get(i).setdelta(tmpDelta);// ustawienie bledu w perceptronie
		}
	}

	// ---------------skorygowanie wag  -----------------------------------
	public void changeWeights() {
		for (int i = 0; i < perceptronCount; i++) {
			perceptrons.get(i).changeWeights();
		}
	}

	// --------------------------------------------------
	public void printLayer() {

		for (int i = 0; i < perceptronCount; i++) {
			System.out.println("perceptron " + i + "- wejscia");
			perceptrons.get(i).printinputs();
			perceptrons.get(i).printWeights();
			System.out.println("wyjscie = " + perceptrons.get(i).getOutput());
			System.out.print("b��d = " + delta.get(i));
			System.out.println();
		}
	}

}
