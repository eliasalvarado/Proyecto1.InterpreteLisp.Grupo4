import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MetodosTest {

	@Test
	void testQUOTE() {
		Metodos met = new Metodos();
		String texto = "Hola";
		assertEquals(met.QUOTE(texto),"Hola");
	}

	@Test
	void testCOND() {
		Metodos met = new Metodos();
		String texto = "(((=x 10) Es un 10)\n((=x 5) Es un 5)\n((=x 15) Es un 15)";
		assertEquals(met.COND(texto),"Es un 10");
	}

}
