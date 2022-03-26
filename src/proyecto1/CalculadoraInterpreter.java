/*
Calculadora de Expresiones Posfix
@author Sebastian Silva
@date 18/02/2022
 */
package proyecto1;

import java.util.HashMap;

public class CalculadoraInterpreter {

    /*
 * Constructor de CalculadoraPosFix
     */
    public CalculadoraInterpreter() {
    }

    /*
     * Metodo que evalua una linea de texto que contiene una expresion posfix, 
realiza la operacion y devuelve el resultado
     * @param expresion String. 
     * @return int.
     */
    public int Evaluate(String expresion, HashMap<String, Integer> variables) {
        //Se hace un split de el String dado por espacios.
        String[] dataArray = expresion.split(" ");
        int resultado = 0;
        //Se crea el Stack basado en ArrayList
        StackArrayList<Integer> stack = new StackArrayList<>();
        //Cadena de operandos permitidos.
        String operandos = "+-/*";
        //Iteracion del Array de la cadena
        for (int i = 0; i <dataArray.length; i++) {
           String data = dataArray[i];
            if (!(data.trim().equals(""))) {
                //Si es un operador...
                if (data.equals("+") || data.equals("-") || data.equals("/") || data.equals("*")) {
                    //Si el stack tiene menos de dos operandos...
                    if (stack.count() < 2) {
                        System.out.println("La operacion es invalida, deben de haber mas de un operando");
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
                    int numero = 0;
                    boolean flag = true;
                    String temp = "";
                    String restricciones ="abcdefghijklmnopqrstuvwxyz";
                    for (String llaves : variables.keySet()) {
                        for(int a = i; a < dataArray.length; a++){
                            if(restricciones.contains(dataArray[a]) == true){
                               temp=temp+dataArray[a];
                               i = a;
                            }
                        }
                        if (llaves.equals(temp)) {
                            flag = false;
                            numero = variables.get(temp);
                        }
                    }
                    if (flag) {
                        numero = Integer.parseInt(data);
                    }
                    stack.push(numero);
                }
            } else {
                System.out.println("Expresion invalida");
            }
        }
        return resultado;
    }

}
