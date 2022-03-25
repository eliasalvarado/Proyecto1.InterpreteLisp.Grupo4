/**
 * Clase SintaxScanner. Encargada de evaluar y devolver un entero dependiendo de la expresion que se le envie
 * Autores:
 * 		Herber Sebastian Silva Muñoz 	-	21764
 * 		Daniel Esteban Morales Urizar 	- 	21785
 * 		Elias Alberto Alvarado Raxon 	-	21808
 * Fecha de creacion: 24/03/2022
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SintaxScanner
{
	/***
	 * Devolvera un numero entero dependiendo la expresion que se le envie.
	 * 0: expresion no encontrada, podría ser una defun del usuario
	 * 1: setq
	 * 2: atom
	 * 3: list
	 * 4: listp
	 * 5: equal
	 * 6: > (descendente)
	 * 7: < (ascendente)
	 * 8: suma
	 * 9: resta
	 * 10: multiplicacion
	 * 11: division
	 * 12: quote
	 * 13: cond
	 * 14: defun
	 * @param Expresion La expresion puede ser ingresada por el usuario u obtenida del archivo de texto
	 * @return Un entero
	 */
	public static int getState(String expresion){
		if(evaluate("^[ ]*[(][ ]*setq[ ]+[\\w]+[ ]+[0-9]+[ ]*[)][ ]*$", expresion)) //SETQ
			return 1;
		else if(evaluate("^[ ]*[(][ ]*atom[ ]+[A-Za-z0-9]+[ ]*[)][ ]*$", expresion)) //ATOM
			return 2;
        else if(evaluate("^[ ]*[(][ ]*list[ ]+[A-Za-z0-9 ]+[)][ ]*$", expresion)) //LIST
			return 3;
        else if(evaluate("^[ ]*[(][ ]*listp[ ]+[A-Za-z0-9 ]+[)][ ]*$|^[ ]*[(][ ]*listp[ ]+[(][A-Za-z0-9 ]+[)][ ]*[)][ ]*$", expresion)) //LISTP - falta si vienen mas de un grupo de parentesis
			return 4;
        else if(evaluate("^[ ]*[(][ ]*equal[ ]+[A-Za-z0-9]+[ ]+[A-Za-z0-9]+[ ]*[)][ ]*$", expresion)) //EQUAL
			return 5;
        else if(evaluate("^[ ]*[(][ ]*>[ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*$", expresion)) //> descendente
			return 6;
        else if(evaluate("^[ ]*[(][ ]*<[ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*$", expresion)) //< ascendente
			return 7;
        else if(evaluate("^[ ]*[(][ ]*[+][ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*$", expresion)) //suma
			return 8;
        else if(evaluate("^[ ]*[(][ ]*[-][ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*$", expresion)) //resta
			return 9;
        else if(evaluate("^[ ]*[(][ ]*[*][ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*$", expresion)) //multiplicacion
			return 10;
        else if(evaluate("^[ ]*[(][ ]*[\\/][ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*$", expresion)) //division
			return 11;
		else if(evaluate("^[ ]*[(][ ]*quote[ ]+[A-Za-z0-9][ ]*[)][ ]*$|^[ ]*[(][ ]*quote[ ]+[(][ ]*[A-Za-z0-9 ]+[ ]*[)][ ]*[)][ ]*$", expresion)) //QUOTE
			return 12;
		else if(evaluate("^[ ]*[(][ ]*cond[ ]+([ ]*[(][ ]*[(][ ]*[\\>\\<\\=][ ]+[A-Za-z0-9]+[ ]+[A-Za-z0-9 ]+[ ]*[)][ ]*[(][ ]*[A-Za-z0-9]+[ ]*[A-Za-z0-9 ]*[ ]*[)][ ]*[)])+[ ]*[)][ ]*$", expresion)) //COND
			return 13;
		else if(evaluate("^[ ]*[(][ ]*defun[ ]+[A-Za-z0-9]+[ ]*[(][ ]*([A-Za-z0-9]+[ ]*)+[ ]*[)][ ]*[(]*[^()\\\"']*.*[)][ ]*$", expresion)) //defun
			return 14;
        else 
			return 0; //Podria ser una funcion definida por el usuario o simplemente esta mal redactada la expresion
	}
	
	/***
	 * Metodo encargado de evaluar a la expresion y hacer un match si este concuerda con el regex
	 * @param regex El pattern de la expresion a evaluar
	 * @param expresion La expresion en cuestion
	 * @return True si concuerda, False si no.
	 */
	private static boolean evaluate(String regex, String expresion) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(expresion);
	    return matcher.find();
	}
}