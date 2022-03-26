/*
Calculadora de Expresiones Posfix
@author Sebastian Silva
@date 18/02/2022
 */
package proyecto1;

import proyecto1.IPosfixCalc;
import proyecto1.StackArrayList;

public class CalculadoraPosFix implements IPosfixCalc {

    /*
 * Constructor de CalculadoraPosFix
     */
    public CalculadoraPosFix() {
    }

    /*
     * Metodo que evalua una linea de texto que contiene una expresion posfix, 
realiza la operacion y devuelve el resultado
     * @param expresion String. 
     * @return int.
     */

    @Override
    public int Evaluate(String expresion) {
        //Se hace un split de el String dado por espacios.
        String[] dataArray = expresion.split(" ");
        int resultado = 0;
        //Se crea el Stack basado en ArrayList
        StackArrayList<Integer> stack = new StackArrayList<>();
        //Cadena de operandos permitidos.
        String operandos = "+*/*";
        //Iteracion del Array de la cadena
        for (String data : dataArray) {
            //Si no hay espacios...
            if (!(data.trim().equals(""))) {
                //Si es un operador...
                if (data.equals("+") || data.equals("-") || data.equals("/")
                        || data.equals("*")) {
                    //Si el stack tiene menos de dos operandos...
                    if (stack.count() < 2) {
                        System.out.println(
                        "La operacion es invalida, deben de haber mas de un operando");
                        throw new IllegalArgumentException();
                        //Si el array no contiene operadores validos...
                    } else if (operandos.contains(data) == false) {
                        System.out.println("La operación es invalida");
                        throw new IllegalArgumentException();
                        //Si todo es correcto...
                    } else {
                        int dato1 = stack.pull();
                        int dato2 = stack.pull();
                        //Ejecucion de suma
                        if (data.equals("+")) {
                            resultado = dato1 + dato2;
                            stack.push(resultado);
                            //Ejecucion de resta
                        } else if (data.equals("-")) {
                            resultado = dato1 - dato2;
                            stack.push(resultado);
                            //Ejecucion de division
                        } else if (data.equals("/")) {
                            //Si se quiere dividir entre 0
                            if (dato2 == 0) {
                                System.out.println("No puede hacer una division sobre 0");
                                throw new IllegalArgumentException();
                            } else {
                                resultado = dato1 / dato2;
                                stack.push(resultado);
                            }
                            //Ejecuciuon de resta.
                        } else if (data.equals("*")) {
                            resultado = dato1 * dato2;
                            stack.push(resultado);
                        }
                    }
                    //Introducir operandos a la pila
                } else {
                    int numero = Integer.parseInt(data);
                    stack.push(numero);
                }
            } else {
                System.out.println("Expresion invalida");
            }
        }
        return resultado;
    }

}
