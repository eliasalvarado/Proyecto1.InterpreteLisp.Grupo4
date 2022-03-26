
import java.util.ArrayList;
public class Metodos {
	
	public void Jerarquia(String exp) {
		ArrayList<String> jerarquias = new ArrayList<String>();
		boolean bandera =true;
		int pos=0;
		String func="";
		while(bandera) {
			func = "";
			//System.out.println(exp);
			pos = exp.lastIndexOf("(");
			int cont =0;
			boolean bandera2 = true;			
			while(bandera2) {
				if(String.valueOf(exp.charAt(pos+cont)).equals(")")) {
					bandera2 = false;
				}else {
					func = func+String.valueOf(exp.charAt(pos+cont));
					cont++;
				}				
			}
			func = func+")";
			jerarquias.add(func);
			exp = exp.replace(func, "");
			if(pos == 0) {
				bandera=false;
			}
		}
		
	}
	
	
	public String QUOTE(String arg) {
		return arg;
	}
	public String COND(String args) {
		String[] lineas = args.split("\\n");
		String txt="";
		String expresion_resp="";
		String operador="";
		String valor1 = "";
		String valor2 = "";
		String accion = "";
		String linea="";
		String expresion="";
		String funcion="";
		for (int i =0;i<lineas.length;i++) {//ciclo que recorre todas las líneas
			expresion ="";
			expresion_resp ="";
			operador="";
			valor1="";
			valor2="";
			accion="";
			linea = lineas[i];
			for (int j = 0; j<linea.length();j++) {// quita parentesis de la expresion
				if (String.valueOf(linea.charAt(j)).equals("(")||String.valueOf(linea.charAt(j)).equals(")")) {
					//no pasa nada
				}else expresion = expresion +linea.charAt(j);
			}
			
			operador = expresion.substring(0, 1).trim();
			
			expresion = expresion.substring(1).trim(); //quita operador de la expresion 
			expresion_resp= expresion;//Se guarda otra expresion sin operador por si es una funcion y no una variable.
			boolean bandera = false;
			for (int x = 0;x<expresion.length();x++) {	//ciclo para encontrar primer valor			
				if(String.valueOf(expresion.charAt(x)).equals(" ")) {
					bandera = true;//encontro un espacio ya no concatena más al valor
				}else {
					if (bandera == false) {
						valor1 = valor1+expresion.charAt(x);
					}
				}
			}
			valor1 = valor1.trim();
			expresion = expresion.substring(valor1.length()).trim(); //quita valor1 de la expresion
			boolean bandera2 = false;
			for (int w = 0;w<expresion.length();w++) {	//ciclo para encontrar primer valor			
				if(String.valueOf(expresion.charAt(w)).equals(" ")) {
					bandera2 = true;//encontro un espacio ya no concatena más al valor
				}else {
					if (bandera2 == false) {
						valor2 = valor2+=expresion.charAt(w);
					}
				}
			}
			valor2.trim();
			expresion = expresion.substring(valor2.length()).trim(); //quita valor2 de la expresion (dejando solo la accion)
			accion = expresion;
			
			//--------------------------------ya estan todos separados------------------------------------------
			//System.out.println(operador);
			//System.out.println(valor1);
			//System.out.println(valor2);
			//System.out.println(accion+"\n");
			//-------------------------------comienza la evaluación---------------------------------------------
			int val1 = 10;//llamar al Hash con nombre de la variable
			int val2 = Integer.parseInt(valor2);
			switch(operador) {
			case "=":
				
				if (val1 == val2) {
					if(txt.equals("")) {
						txt=accion;
					}					
				}
				break;
			case ">":
				if (val1 > val2) {
					if(txt.equals("")) {
						txt=accion;
					}
				}
				break;
			case "<":
				if (val1 < val2) {
					if(txt.equals("")) {
						txt=accion;
					}
				}
				break;
			}
		}
		return txt.trim();
	}
}
