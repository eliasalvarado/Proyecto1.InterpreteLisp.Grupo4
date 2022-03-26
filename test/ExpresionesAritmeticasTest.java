import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import proyecto1.CalculadoraInterpreter;
import proyecto1.Interprete;
import proyecto1.PrefixToPosfix;

class ExpresionesAritmeticasTest {

	@Test
	void test() {
		CalculadoraInterpreter calculadora = new CalculadoraInterpreter();
		HashMap<String, Integer> variables = new HashMap<>();
		variables.put("x", 5);
		assertEquals(calculadora.Evaluate(PrefixToPosfix.convPrefixToPosfix("+ 5 2"), variables), 7);
		assertEquals(calculadora.Evaluate(PrefixToPosfix.convPrefixToPosfix("* x 2"), variables), 10);
		assertEquals(calculadora.Evaluate(PrefixToPosfix.convPrefixToPosfix("/ 2 10"), variables), 5);
		assertEquals(calculadora.Evaluate(PrefixToPosfix.convPrefixToPosfix("- x 2"), variables), -3);
	
	}

}
