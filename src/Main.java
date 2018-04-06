import java.util.ArrayList;
import java.lang.*;

import Model.*;

public class Main {

	public static void main(String[] args) {
		final int inputCount = 2;
		final int firstLayerPerceptronCount = 3;
		final int secondLayerPerceptronCount = 2;
		

System.out.println("mam na imie Martysia");
		
		
		Layer l1 = new Layer(inputCount, firstLayerPerceptronCount, false);// wejscia, ilosc perceptronów, bias
		Layer l2 = new Layer(firstLayerPerceptronCount, secondLayerPerceptronCount, false);// wejscia, ilosc perceptronów, bias

		ArrayList<Double> we = new ArrayList();
		for (int i = 0; i < inputCount; i++) {
			we.add(i+0.3);
		}
		
		l1.setInput(we);
		l1.calculate();
		l1.printLayer();
		System.out.println("");
		
		l2.setInput(l1.getOutputs());
		l2.calculate();
		l2.printLayer();

	}

}
