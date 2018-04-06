import java.util.ArrayList;
import java.lang.*;

import Model.*;

public class Main {

	public static void main(String[] args) {
		final int inputCount = 2;
		final int firstLayerPerceptronCount = 3;
		final int secondLayerPerceptronCount = 2;

		System.out.println("mam na imie Martysia");

		Layer l1 = new Layer(inputCount, firstLayerPerceptronCount, false);// wejscia, ilosc perceptron�w, bias
		Layer l2 = new Layer(firstLayerPerceptronCount, secondLayerPerceptronCount, false);// wejscia, ilosc
																							// perceptron�w, bias

		ArrayList<Double> we = new ArrayList();
		ArrayList<Double> oczekiwana = new ArrayList();
		ArrayList<Double> lastLayerData = new ArrayList(); // lista b��d�w ostatniej warstwy

		// pokazanie sieci danych na wejsciu i ustawienie warto�ci oczekiwanych na
		// wyj�ciu
		for (int i = 0; i < inputCount; i++) {
			we.add(i + 0.3);
			oczekiwana.add(5.0);
		}

for (long k = 0; k < 2; k++) {
			l1.setInput(we);// podanie sygna��w wej�ciowych na wej�cia pierwszej warstwy
			l1.calculate();// przeliczenie pierwszej warstwy

			l2.setInput(l1.getOutputs());// podanie sygna��w wyjsciowych z 1 warstwy na wej�cie drugiej
			l2.calculate(); // przeliczenie drugiej warstwy
			// l2.printLayer();

			// obliczanie b��du ostatniej warstwy
			for (int i = 0; i < l2.getOutputs().size(); i++) {
				l2.getDelta().set(i, oczekiwana.get(i) - l2.getOutputs().get(i));
			}

			// obliczanie b��du pierwszej warstwy
			l1.calculateDelta(l2);

			System.out.println("\nLayer 2");
			l2.printLayer();
			System.out.println("\nLayer 1");
			l1.printLayer();

			// korekcja wag
			l1.changeWeights();
			l2.changeWeights();
		}

	}

}
