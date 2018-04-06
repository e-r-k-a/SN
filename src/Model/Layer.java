package Model;

import java.util.ArrayList;

public class Layer {
	private ArrayList<Double> inputs = new ArrayList<>();// Lista sygna³ów wejœciowych warstwy
	private ArrayList<Double> outputs = new ArrayList<>();// Lista sygna³ów wyjœciowych warstwy
	private ArrayList<Perceptron> perceptrons = new ArrayList<>();// Lista perceptronów
	private ArrayList<Double> delta = new ArrayList<>();// lista b³êdów poszczególnych perceptronów
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
			outputs.add(0.0); // utworzenie elementów listy wyjœæ
			perceptrons.add(new Perceptron(inputCount, bias)); // utworzenie perceptronów
			delta.add(0.0); // utworzenie elementów listy b³êdów
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

	public ArrayList<Double> getWeight(int number) { // zwrócenie listy wag wejscia number wszystkich perceptronów
		ArrayList<Double> weight = new ArrayList<>();// Lista wag wejscia number kolejnych perceptronów
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
		// przepisanie wejsc do perceptronów
		for (int i = 0; i < perceptronCount; i++) {
			perceptrons.get(i).setInputs(inputs);// pod³¹czenie wejœæ warstwy do wejœæ percepr=trona
			perceptrons.get(i).Calculate();// przeliczenie perceptrona
			outputs.set(i, perceptrons.get(i).getOutput());// zapisanie wyjœcia perceptrona na wyjscie warstwy
		}
	}

	// ---------------przeliczenie wstecz b³êdu -----------------------------------
	// Na podstawie listy b³êdów i listy wag nastêpnej sieci wylicza b³êdy
	// wszystkich perceptronów
	// i zapisuje je do swojej listy b³êdów
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
			System.out.print("b³¹d = " + delta.get(i));
			System.out.println();
		}
	}

}
