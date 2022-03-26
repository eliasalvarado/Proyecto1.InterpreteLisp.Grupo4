/**
 * Clase Controlador. Sera la encargada de interactuar con el usuario
 * Autores: 
 *      Herber Sebastian Silva Muñoz -	21764
 *      Daniel Esteban Morales Urizar - 21785 
 *      Elias Alberto Alvarado Raxon -	21808
 * Fecha de creacion: 24/03/2022
 */

package proyecto1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sebastián
 */
public class Controlador
{
    /** 
     * @param args
     */
    public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Interprete inter = new Interprete();
                ArrayList<String> instrucciones = new ArrayList<>();
                instrucciones = Lector.leerArchivo("F:\\Programacion\\Proyecto1\\src\\archivos\\linea.txt");
                System.out.println("----------EJECUCION DE PROGRAMA PREESTABLECIDO----------");
                for(String instruccion: instrucciones){
                    System.out.println(instruccion);
                    inter.Evaluar(instruccion, SintaxScanner.getState(instruccion));
                }
                
		System.out.println("Bienvenido al interpreter Lisp ingrese una expresion o escriba exit para salir");
		String opt = "";
                
		do {
                        System.out.println("↓");
			opt = in.nextLine();
			
			if (!opt.equals("exit")) {
                            inter.Evaluar(opt, SintaxScanner.getState(opt));
			}
			
		}while (!opt.equals("exit"));
	}
    /*public static void main(String[] args) {
        Interprete inter = new Interprete();
        System.out.println(inter.limpiar("(defun f (x)(+ x 5))", "d", "n", 4));
    }*/
}
