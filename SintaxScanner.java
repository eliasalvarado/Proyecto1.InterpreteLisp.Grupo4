import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SintaxScanner
{

	/***
	 * This method return a integer number that identifies the type of the operation
	 * @param Expresion The expresion given by the user
	 * @return An integer greater than 0 that indicates the type of the operation, -1 if the expression is not valid
	 */
	public static int getState(String expresion){
		if(evaluate("^[(][ ]*setq[ ]+[a-z]+[ ]+[0-9]+[ ]*[)]$", expresion)) //SETQ
			return 1;
		else if(evaluate("^[(][ ]*atom[ ]+[A-Za-z0-9]+[ ]*[)]$", expresion)) //ATOM
			return 2;
        else if(evaluate("^[(][ ]*list[ ]+[A-Za-z0-9 ]+[)]$", expresion)) //LIST
			return 3;
        else if(evaluate("^[(][ ]*listp[ ]+[A-Za-z0-9 ]+[)]$|^[(][ ]*listp[ ]+[(][A-Za-z0-9 ]+[)][ ]*[)]$", expresion)) //LISTP - falta si vienen mas de un grupo de parentesis
			return 4;
        else if(evaluate("^[(][ ]*equal[ ]+[A-Za-z0-9]+[ ]+[A-Za-z0-9]+[ ]*[)]$", expresion)) //EQUAL
			return 5;
        else if(evaluate("^[(][ ]*>[ ]+[A-Za-z0-9 ]+[ ]*[)]$", expresion)) //> descendente
			return 6;
        else if(evaluate("^[(][ ]*<[ ]+[A-Za-z0-9 ]+[ ]*[)]$", expresion)) //< ascendente
			return 7;
        else if(evaluate("^[(][ ]*[+][ ]+[A-Za-z0-9 ]+[ ]*[)]$", expresion)) //suma
			return 8;
        else if(evaluate("^[(][ ]*[-][ ]+[A-Za-z0-9 ]+[ ]*[)]$", expresion)) //resta
			return 8;
        else if(evaluate("^[(][ ]*[*][ ]+[A-Za-z0-9 ]+[ ]*[)]$", expresion)) //multiplicacion
			return 8;
        else if(evaluate("^[(][ ]*[\\/][ ]+[A-Za-z0-9 ]+[ ]*[)]$", expresion)) //division
			return 8;
        else 
			return 0; //if no match found then the expression is incorrect. podria ser un 
	}
	
	/***
	 * Private method which evaluate an expression
	 * @param regex the patter of the expresion
	 * @param expresion The expresion given by the user
	 * @return true if is a match, false otherwise
	 */
	private static boolean evaluate(String regex, String expresion) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(expresion);
	    return matcher.find();
	}
}