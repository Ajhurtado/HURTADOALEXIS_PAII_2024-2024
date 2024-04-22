package default_package;

import interaces_package.InterfaceTwo;
import interaces_package.interfaceExample;

public class Clase_B {

	
	public Clase_B(interfaceExample ie) {

		ie.MetodoA();
		ie.MetodoB();
	}

	public Clase_B(InterfaceTwo ie) {

		ie.metodoC();

	}
}
