import java.util.ArrayList;
import java.lang.*;

import Model.*;

public class Main {

	public static void main(String[] args) {
		final int inputCount 				 = 3;
		final int firstLayerPerceptronCount  = 2;
		final int secondLayerPerceptronCount = 1;

		

		System.out.println("mam na imie Martysia");

		Layer l1 = new Layer(inputCount, firstLayerPerceptronCount, false);// wejscia, ilosc perceptronów, bias
		Layer l2 = new Layer(firstLayerPerceptronCount, secondLayerPerceptronCount, false);// wejscia, ilosc perceptronów, bias

		
		ArrayList<Double> we = new ArrayList();
		ArrayList<Double> oczekiwana = new ArrayList();
		ArrayList<Double> lastLayerData = new ArrayList(); // lista b³êdów ostatniej warstwy

		// pokazanie sieci danych na wejsciu i ustawienie wartoœci oczekiwanych na wyjœciu
		we.add(0.0);
		we.add(1.0);
		we.add(1.0);
		oczekiwana.add(0.0);
		
		//zmiana wag z losowych na konkretne dla testowania

/*		l1.getPerceptrons().get(0).setWeights(0, 0.4);
		l1.getPerceptrons().get(0).setWeights(1, -0.6);
		l1.getPerceptrons().get(0).setWeights(2, 0.9);
		l1.getPerceptrons().get(1).setWeights(0, 0.8);
		l1.getPerceptrons().get(1).setWeights(1, 0.2);
		l1.getPerceptrons().get(1).setWeights(2, -0.4);
		
		l2.getPerceptrons().get(0).setWeights(0, -0.5);
		l2.getPerceptrons().get(0).setWeights(1, 0.1);
	*/	
		
		
		//for (int i = 0; i < inputCount; i++) {
		//	we.add(i + 0.3);
		//	oczekiwana.add(5.0);
		//}

for (long k = 0; k < 10000000; k++) {
			l1.setInput(we);// podanie sygna³ów wejœciowych na wejœcia pierwszej warstwy
			l1.calculate();// przeliczenie pierwszej warstwy
		//	l1.printLayer();
			
			l2.setInput(l1.getOutputs());// podanie sygna³ów wyjsciowych z 1 warstwy na wejœcie drugiej
			l2.calculate(); // przeliczenie drugiej warstwy
	//		 l2.printLayer();

			// obliczanie b³êdu ostatniej warstwy
			for (int i = 0; i < l2.getOutputs().size(); i++) {
			//	l2.getDelta().set(i, oczekiwana.get(i) - l2.getOutputs().get(i));
				double tmpDelta = l2.getOutputs().get(i)*(1 - l2.getOutputs().get(i))*(oczekiwana.get(i) - l2.getOutputs().get(i));
				l2.getDelta().set(i, tmpDelta );
				l2.getPerceptrons().get(i).setdelta(tmpDelta);				
			}
//			System.out.println();
//			l2.printLayer();

			// obliczanie b³êdu pierwszej warstwy
			l1.calculateDelta(l2);

			//System.out.println("\nLayer 2");
			//l2.printLayer();
			//System.out.println("\nLayer 1");
			//l1.printLayer();

			// korekcja wag
			l1.changeWeights();
			l2.changeWeights();

//			System.out.println("\nLayer 2");
//			l2.printLayer();
//			System.out.println("\nLayer 1");
//			l1.printLayer();

			
		}

System.out.println("\nLayer 2");
l2.printLayer();
System.out.println("\nLayer 1");
l1.printLayer();

	}

}
