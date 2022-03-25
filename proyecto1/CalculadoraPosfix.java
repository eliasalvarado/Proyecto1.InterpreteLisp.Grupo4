/**
 * Clase CalculadoraPosfix, sera la encargada de realizar las operaciones con las expresiones posfix
 * Autores:
 * 		Herber Sebastian Silva Muñoz 	21764
 * 		Daniel Esteban Morales Urizar 	21785
 * 		Elias Alberto Alvarado Raxon 	21808
 * Fecha de creacion: 06/03/2022
 */
package proyecto1;

import java.util.Stack;


/**
 *
 * @author Sebastián
 */
public class CalculadoraPosfix {
    private static CalculadoraPosfix calc;
    private CalculadoraPosfix(){
        System.out.println("Se creara una unica calculadora");
    } 
    
    /** 
     * @return CalculadoraPosfix
     */
    public static CalculadoraPosfix getInstance(){
        if(calc == null){
            calc = new CalculadoraPosfix();
        } else{
            System.out.println("Ya se ha creado una calculadora antes");
        }
        return calc;
    }
    
    /** 
     * @param expresion
     * @param tipo
     * @return int
     */
    public int Evaluar(String expresion){
        Stack<Integer> lista= new Stack<>();
        int resultado = 0;
        int op1 = 0;
        int op2 = 0;
        String[] caracteres = expresion.split("");
        
        for (String caracter: caracteres){
            if(caracter.equals("+") || caracter.equals("-") || caracter.equals("*") || caracter.equals("/") || caracter.equals("^")){
                if(lista.capacity() <=1){
                    throw new IllegalArgumentException("\nNo se puede realizar la operacion a falta de operadores.");
                }else{
                    op1 = lista.pop();
                    op2 = lista.pop();
                    switch(caracter){
                        case "+":
                            resultado = op1 + op2;
                            break;
                        case "-":
                            resultado = op2 -op1;
                            break;
                        case "*":
                            resultado = op1 * op2;
                            break;
                        case "/":
                            if(op1 == 0){
                                throw new IllegalArgumentException("\nNo se puede realizar la division entre 0.");
                            }else{
                                resultado = op2/op1;
                            }
                            break;
                        case "^":
                            double a = new Double(op2);
                            double b = new Double(op1);
                            resultado = (int) Math.pow(a, b);
                            break;
                    }
                    lista.push(resultado);
                }
            }else
                lista.push(Integer.parseInt(caracter));
        }
        
        return resultado;
    }
}
