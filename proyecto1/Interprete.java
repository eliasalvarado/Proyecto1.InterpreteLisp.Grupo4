package proyecto1;

import java.util.*;

public class Interprete {
        HashMap<String, String> funciones = new HashMap<>();
        HashMap<String, String> variables = new HashMap<>();
        HashMap<Integer, String> instrucciones = new HashMap<>();
        ArrayList<String> lineas = new ArrayList<>();
    public Interprete(){
        funciones.put("cond", "Se ejecuta el código correspondiente a cond");
        funciones.put("quote", "Se ejecuta la función quote");
        funciones.put("'", "Se ejecuta una single quote");
        funciones.put("defun", "Se añade una nueva función");
        funciones.put("setq", "Se define una variable");
        funciones.put("atom", "Se evalúa atom");
        funciones.put("list", "Se evalua lista");
        funciones.put("=", "Se iguala una funcion");
        funciones.put("<", "Se evalua si es menor");
        funciones.put(">", "Se evalua si es mayor");
    }
    
    public boolean validarFunciones(String expresion){
        boolean isExpression = false;
        boolean flag = false;
        for(int i = 0; i < expresion.length(); i ++){
            if(expresion.charAt(i) == '(' && flag){
                isExpression = true;
                break;
            } else if(expresion.charAt(i) == '('){
                flag = true;
            } else if(expresion.charAt(i) == ')'){
                return false;
            }
        }
        return isExpression;
    }
    
    public void leerFunciones(String expresion){
        boolean finish = false;
        int charfinal = 0;
        while(!finish){
            if(!validarFunciones(expresion)){
                int charinicio= 0;
                for(int i = charfinal; i<expresion.length(); i++){
                    if(expresion.charAt(i) == '('){
                        charinicio = i+1;
                    } else if (expresion.charAt(i) == ')'){
                        charfinal = i;
                        break;
                    }
                }
                System.out.println(expresion.substring(charinicio, charfinal));
                finish = true;
                
            }
        }
    }
    
    
    public void ejecutar(String expresion){
       //Se ejecutan cadenas de código
       //Si aparece (funcion
       //ejecutar(funciones.get("funcion"))
    }
    
}
