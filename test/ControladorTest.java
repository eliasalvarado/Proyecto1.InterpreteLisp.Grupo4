import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ControladorTest {
	

	@Test
	void testSetq() {
		Controlador controlador = new Controlador();
	    assertEquals(controlador.getVariables().size(), 0);
	    assertEquals(controlador.getListas().size(), 0);
	    controlador.setq("( setq  x 10)");
	    controlador.setq("( setq  y 200)");
	    controlador.setq("(setq z 25)");
	    controlador.setq("( setq  lista (10 45 167) )");
	    assertEquals(controlador.getVariables().size(), 3);
	    assertEquals(controlador.getListas().size(), 1);
	    
	    assertEquals(controlador.getVariables().get("x"), 10);
	    assertEquals(controlador.getVariables().get("y"), 200);
	    assertEquals(controlador.getVariables().get("z"), 25);
	    assertEquals(controlador.getListas().get("lista"), "(10 45 167)");
	}

	@Test
	void testLimpiar() {
		Controlador controlador = new Controlador();
	    assertEquals(controlador.limpiar("(  setq  x 100)", "s", "q", 3), "x 100");
	    assertEquals(controlador.limpiar("   (  atom   200  )", "a", "m", 3), "200");
	    assertEquals(controlador.limpiar("  (equal 300 400 )", "e", "l", 4), "300 400");
	    assertEquals(controlador.limpiar("  (   list 500 600 700 )  ", "l", "t", 3), "500 600 700");
	    assertEquals(controlador.limpiar("  (   listp (800 900 1000 )  )", "l", "p", 4), "(800 900 1000 )");
	}

	@Test
	void testImprimir() {
		Controlador controlador = new Controlador();
	    assertEquals(controlador.getVariables().size(), 0);
	    assertEquals(controlador.getListas().size(), 0);
	    controlador.setq("( setq  x 10)");
	    controlador.setq("( setq  y 200)");
	    controlador.setq("(setq z 25)");
	    controlador.setq("( setq  lista (10 45 167) )");
	    assertEquals(controlador.imprimir(), "\n{x=10, y=200, z=25}\n{lista=(10 45 167)}");
	    assertEquals(controlador.getListas().size(), 1);
	}

	@Test
	void testAtom() {
		Controlador controlador = new Controlador();
		controlador.setq("( setq  x 10)");
		controlador.setq("( setq  lista (10 45 167) )");
	    assertEquals(controlador.atom("(atom 150)"), true);
	    assertEquals(controlador.atom("(atom (1 2 3)"), false);
	    assertEquals(controlador.atom("(atom x)"), true);
	    assertEquals(controlador.atom("(atom lista)"), false);
	}

	@Test
	void testEqual() {
		Controlador controlador = new Controlador();
		controlador.setq("( setq  a 10)");
		controlador.setq("( setq  b 20)");
		controlador.setq("( setq  c 10)");
		controlador.setq("( setq  d 20)");
	    assertEquals(controlador.equal("(equal 100 100)"), true);
	    assertEquals(controlador.equal("(equal 40 100)"), false);
	    
	    assertEquals(controlador.equal("(equal a b)"), false);
	    assertEquals(controlador.equal("(equal a c)"), true);
	    assertEquals(controlador.equal("(equal b c)"), false);
	    assertEquals(controlador.equal("(equal b d)"), true);
	}

	@Test
	void testAscendente() {
		Controlador controlador = new Controlador();
		controlador.setq("( setq  a 10)");
		controlador.setq("( setq  b 20)");
		controlador.setq("( setq  c 30)");
		controlador.setq("( setq  d 40)");
	    assertEquals(controlador.ascendente("(< 1 2 3 4 5)"), true);
	    assertEquals(controlador.ascendente("(< 5 4 3 2 1)"), false);
	    
	    assertEquals(controlador.ascendente("(< a b c d)"), true);
	    assertEquals(controlador.ascendente("(< d c b a)"), false);
	}

	@Test
	void testDescendente() {
		Controlador controlador = new Controlador();
		controlador.setq("( setq  a 4)");
		controlador.setq("( setq  b 3)");
		controlador.setq("( setq  c 2)");
		controlador.setq("( setq  d 1)");
	    assertEquals(controlador.descendente("(> 100 90 80 70 1)"), true);
	    assertEquals(controlador.descendente("(> 100 90 80 70 1 2)"), false);
	    
	    assertEquals(controlador.descendente("(> a b c d)"), true);
	    assertEquals(controlador.descendente("(> d c b a)"), false);
	}

	@Test
	void testLimpiarSimbolo() {
		Controlador controlador = new Controlador();
	    assertEquals(controlador.limpiarSimbolo("(> 1   2  3   4 5  ) "), "1 2 3 4 5");
	    assertEquals(controlador.limpiarSimbolo("(< 1000 400  6 3    0) "), "1000 400 6 3 0");
	}

	@Test
	void testList() {
		Controlador controlador = new Controlador();
	    assertEquals(controlador.list("(  list 50  6   4   5  ) "), "(50 6 4 5)");
	}

	@Test
	void testListp() {
		Controlador controlador = new Controlador();
		controlador.setq("( setq  x 10)");
		controlador.setq("( setq  lista (10 45 167) )");
	    assertEquals(controlador.listp("(   listp 150)"), false);
	    assertEquals(controlador.listp("(listp   (1 2 3)"), true);
	    assertEquals(controlador.listp("(listp  x)"), false);
	    assertEquals(controlador.listp("(listp  lista)"), true);
	}

}
