/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Stack;

/**
 *
 * @author Sebastián
 */
public class PrefixToPosfix {
    public static String invertir(String expresion){
        StringBuilder strn = new StringBuilder(expresion);
        expresion = strn.reverse().toString();
        String temp = "";
        for(int i = 0; i<expresion.length(); i++){
            if(expresion.charAt(i) != '(' && expresion.charAt(i) != ')'){
                temp = temp+expresion.charAt(i);
            } else if(expresion.charAt(i) == '('){
                temp = temp+")";
            } else if (expresion.charAt(i) == ')'){
                temp = temp+"(";
            }
        }
        expresion = temp;
        return expresion;
    }
    
    public static String convPrefixToPosfix(String expresion){
        expresion = invertir(expresion);
        String temp = "";
        StackArrayList<String> stack = new StackArrayList<>();
        for(int i = 0; i<expresion.length(); i++){
            if(expresion.charAt(i) >= 'a' && expresion.charAt(i) <= 'z'){
                stack.push(""+expresion.charAt(i));
            } else if(expresion.charAt(i) == ' '){
                stack.push(" ");
            }else if (expresion.charAt(i) == '*' || expresion.charAt(i) == '+' || expresion.charAt(i) == '-' || expresion.charAt(i) == '/'){
                for(int a = 0; a<= 1; a++){
                    if(stack.peek() == " "){
                        temp = temp+stack.pull();
                    }
                    if(!stack.isEmpty()){
                    temp = temp+stack.pull();}
                }
                temp = temp+" "+expresion.charAt(i);
                temp = temp.trim();
            } else if (expresion.charAt(i) >= '0' && expresion.charAt(i) <= '9'){
                String temp2 = "";
                for(int a = i; a<expresion.length(); a++){
                    if(expresion.charAt(a) >= '0' && expresion.charAt(a) <= '9'){
                        temp2 = temp2+expresion.charAt(a);
                        i = a;
                    } else{
                        stack.push(invertir(temp2));
                        break;   
                    }
                }
            } else if(expresion.charAt(i) == '(' || expresion.charAt(i) == ')'){
                temp = temp+""+expresion.charAt(i);
            }
        }
        temp = temp.replaceAll("  ", " ");
        return temp;
    }
}
