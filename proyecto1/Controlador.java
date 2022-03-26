/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sebastián
 */
public class Controlador {
        public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Interprete inter = new Interprete();
                ArrayList<String> instrucciones = new ArrayList<>();
                instrucciones = Lector.leerArchivo("F:\\Programacion\\Proyecto1\\src\\archivos\\linea.txt");
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
       /* public static void main(String[] args) {
        Interprete inter = new Interprete();
        ArrayList<String> instrucciones = inter.Jerarqui("(func (func (+ x 10)))");
            System.out.println(instrucciones);
    }*/
}
