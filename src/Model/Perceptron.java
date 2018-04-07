package Model;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Random;


public class Perceptron {
	
	final double ETA = 0.5; //wspó³czynnik uczenia
	final double BETA = 1.0; //wspó³czynnik funkcji przejœcia

	private ArrayList<Double> inputs = new ArrayList<>();// Lista wejœæ perceptrona
	private ArrayList<Double> weights = new ArrayList<>();// Lista wag perceptrona
	private double output; // wyjœcie
	private boolean bias; // flaga biasu true-zalaczony false - wylaczony
	private double biasWeight; // waga biasu
	private double delta;//b³¹d perceptrona

	//-----------------konstruktor---------------------
	public Perceptron(int inputCount, boolean bias) {
		this.bias = bias;
		for (int i = 0; i < inputCount; i++) {
			// Double a = Double.valueOf(0.0);
			inputs.add(0.0);
			weights.add(new Random().nextDouble());
		}
		output = 0.0;
		biasWeight = new Random().nextDouble();
	}
	//--------------gettery i settery----------------------
	public double getOutput() {
		return output;
	}
	
	public ArrayList<Double> getWeight() {
		return weights;
	}
	public double getdelta() {
		return delta;
	}
	

	public void setInputs(ArrayList<Double> inputs) {
		this.inputs = inputs;
	}
	public void setdelta(double delta) {
		this.delta = delta;
	}
	public void setWeights(int inputNumber, double newValue) {
		weights.set(inputNumber, newValue);
	}
	
	//-------------------------------------------
	public void Calculate() {
		double sum = 0.0;
		// obliczanie sumy wejœæ pomno¿onych przez wagi
		for (int i = 0; i < inputs.size(); i++) {
			sum += weights.get(i) * inputs.get(i);
		}
		if (bias) { // dodanie biasu
			sum += biasWeight;
		}
		// funkcja przej¹cia
	//	System.out.println("suma wejsc = " + sum);
		output = sigma(sum);
	//	System.out.println("wyjscie = " + output);

	}
	
	//------ korekcja wag ---------------
	public void changeWeights() {
		for(int i=0; i < inputs.size(); i++ ) {
			weights.set(i, weights.get(i) + ETA * getdelta() * inputs.get(i)); 
		}
	}
	
// --------------------   Wydruki  -------------------------------
	public void printWeights() {
		System.out.print("wagi perceptrona: ");
		for (int i = 0; i < weights.size(); i++) {
			System.out.print(weights.get(i) + " ");
		}
		System.out.println();
	}

	public void printinputs() {
		System.out.print("wejscia perceptrona: ");
		for (int i = 0; i < inputs.size(); i++) {
			System.out.print(inputs.get(i) + " ");
		}
		System.out.println();
	}
//------------------ Funkcja przejscia --------------------------
	private double sigma(double arg) {
		return 1.0 / (1 + Math.exp(-BETA * arg));//beta = 2.0
	}

	
}
