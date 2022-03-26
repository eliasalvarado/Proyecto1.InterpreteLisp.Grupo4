package proyecto1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interprete {

    private HashMap<String, Integer> variables;
    private HashMap<String, String> listas;
    public CalculadoraInterpreter calculadora;

    public Interprete() {
        this.variables = new HashMap<>();
        this.listas = new HashMap<>();
        this.calculadora = new CalculadoraInterpreter();
    }
    
    
    public ArrayList<String> Jerarqui(String exp) {
        ArrayList<String> jerarquias = new ArrayList<>();
        boolean bandera = true;
        int pos = 0;
        String func = "";
        while (bandera) {
            func = "";
            pos = exp.lastIndexOf("(");
            int cont = 0;
            boolean bandera2 = true;
            while (bandera2) {
                if (String.valueOf(exp.charAt(pos + cont)).equals(")")) {
                    bandera2 = false;
                } else {
                    func = func + String.valueOf(exp.charAt(pos + cont));
                    cont++;
                }
            }
            func = func + ")";
            jerarquias.add(func);
            exp = exp.replace(func, "");
            if (pos == 0) {
                bandera = false;
            }
        }
        return jerarquias;
    }

    public void setq(String instruccion) {
        instruccion = this.limpiar(instruccion, "s", "q", 3);

        //Se mira si la cadena tiene mas parentesis dentro de ella
        String lista = "";
        int inicio = 0;
        int fin = 0;
        for (int i = 0; i < instruccion.length(); i++) {
            String caracter = String.valueOf(instruccion.charAt(i));
            if (caracter.equals("(")) {
                inicio = i;
            }
            if (caracter.equals(")")) {
                fin = i;
            }
        }

        boolean esLista = false;

        if (inicio != 0 && fin != 0) //Si son distinto a 0 ambos, significa que el for anterior identifico otros parentesis dentro de la cadena
        {
            esLista = true;
            for (int x = inicio; x <= fin; x++) {
                lista += String.valueOf(instruccion.charAt(x)); //Se le concatena a "lista" todos los caracteres que estan dentro de los parentesis de "editado", incluyendo los mismos parentesis
            }

            StringBuilder listaE = new StringBuilder(lista);
            for (int i = 0; i < listaE.length(); i++) {
                String caracter = String.valueOf(listaE.charAt(i));
                if (caracter.equals("(") || caracter.equals(")")) {
                    listaE.deleteCharAt(i); //Se eliminan los parentesis para poder hacer la limpieza de espacios en blanco innecesarios
                }
            }
            lista = new String(listaE).trim().replaceAll("\\s+", " ");
            lista = "(" + lista + ")";  //se regresan los parentesis a la lista para poder manejarla luego como tal

        }

        instruccion = instruccion.replaceAll("\\s+", " ");

        String[] split = instruccion.split(" ");

        if (esLista) {
            this.listas.put(split[0], lista);
        } else {
            this.variables.put(split[0], Integer.parseInt(split[1]));
        }
    }

    public String limpiar(String instruccion, String letraInicio, String letraFinal, int largo) {
        instruccion = instruccion.trim().replaceAll("\\s+", " ");
        StringBuilder editado = new StringBuilder(instruccion);
        editado.deleteCharAt(0);
        editado.deleteCharAt(editado.length() - 1);
        int indexInicio = editado.indexOf(letraInicio);
        int indexFinal = editado.indexOf(letraFinal);
        if ((indexFinal - indexInicio) == largo) {
            editado.delete(indexInicio, indexFinal + 1);
        }
        instruccion = new String(editado).trim();
        instruccion = instruccion.replaceAll("\\s+", " ");
        return instruccion;
    }

    public String imprimir() {
        return "\n" + String.valueOf(this.variables) + "\n" + String.valueOf(this.listas);
    }

    public String operaciones() {
        String info = "";
        info += "\nPrimera operacion: " + (this.variables.get("x") + this.variables.get("y") + this.variables.get("z"));
        info += "\nSegunda operacion: " + (this.variables.get("z") + this.variables.get("y") - this.variables.get("x"));
        info += "\nTercera operacion: " + (this.variables.get("x") / this.variables.get("y") + this.variables.get("z"));

        return info;
    }

    public String atom(String instruccion) //CAMBIAR A QUE DEVUELVA "T" SI ES TRUE O "NIL" SI ES FALSO
    {
        instruccion = this.limpiar(instruccion, "a", "m", 3);
        //aqui debe ir un codigo para convertir la "instruccion" si es que esta viene con una funcion definida por el usuario
        Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruccion);
        boolean esAtom = matcher.find();  //El matcher.find() cambia cada vez que se utiliza, por lo que se guarda el match en la varibale "esAtom"
        if (esAtom) {
            return "T";
        } else {
            Pattern variable = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = variable.matcher(instruccion);
            if (matcher2.find()) {
                try {
                    int valor = this.variables.get(instruccion);
                    return "T";
                } catch (Exception e) {
                    if (this.listas.containsKey(instruccion)) {
                        return "NIL";
                    } else {
                        return instruccion + " no tiene valor. Por lo que no se puede determinar si es atom o no.";
                    }
                }
            }
            return "NIL";
        }

        /*
        boolean esAtom = false;
        System.out.println("\nEntrada ATOM: '" + instruccion + "'");
        instruccion = limpiar(instruccion, "a", "m", 3);
        System.out.println("\nLimpiado ATOM: '" + instruccion + "'");
        StringBuilder editado = new StringBuilder(instruccion);
        System.out.println("\nEditado ATOM: '" + editado + "'");
        String lista = "";
        int inicio = 0;
        int fin = 0;
        for(int i = 0; i < editado.length(); i++)
        {
            String caracter = String.valueOf(editado.charAt(i));
            if(caracter.equals("(")) inicio = i;
            if(caracter.equals(")")) fin = i;
        }
        boolean parentesis = false;
        if(inicio != 0 || fin != 0) //Si son distinto a 0 uno de los dos, significa que el for anterior identifico otros parentesis dentro de la cadena
        {
            parentesis = true;
            for(int x = inicio; x <= fin; x++)
            {
                lista += String.valueOf(editado.charAt(x)); //Se le concatena a "lista" todos los caracteres que estan dentro de los parentesis de "editado", incluyendo los mismos parentesis
            }
            StringBuilder listaE = new StringBuilder(lista);
            for(int i = 0; i < listaE.length(); i++)
            {
                String caracter = String.valueOf(listaE.charAt(i));
                if(caracter.equals("(") || caracter.equals(")")) listaE.deleteCharAt(i); //Se eliminan los parentesis para poder hacer la limpieza de espacios en blanco innecesarios
            }
            lista = new String(listaE).trim().replaceAll("\\s+", " ");
            lista = "(" + lista + ")";
        }
        if(parentesis)
        {
            //codigo para ver si lo que esta dentro de los parentesis es una funcion o algo que podria ser un ATOM
            return false;
        }
        else
        {
            if(instruccion.length() > 0 ) return true;
        }
        System.out.println("\nLimpiado ATOM: '" + instruccion + "'");
        
        return esAtom;
         */
    }

    public String equal(String instruccion) {
        instruccion = this.limpiar(instruccion, "e", "l", 4);

        Pattern pattern = Pattern.compile("^[0-9]+[ ]+[0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruccion);
        boolean sonNumeros = matcher.find();
        String[] split = instruccion.split(" ");

        if (sonNumeros) {
            if (Integer.valueOf(split[0]) == Integer.valueOf(split[1])) {
                return "T";
            } else {
                return "NIL";
            }
        } else //Pueden ser numeros y letras (variables) juntos o solo variables
        {
            Pattern letras = Pattern.compile("^[a-zA-Z]+[ ]+[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = letras.matcher(instruccion);
            boolean sonLetras = matcher2.find();
            if (sonLetras) //Son solo letras, por lo tanto, son variables definidas por el usuario
            {
                try {
                    if (this.variables.containsKey(split[0]) && this.variables.containsKey(split[1])) {
                        int primero = this.variables.get(split[0]);
                        int segundo = this.variables.get(split[1]);
                        if (primero == segundo) {
                            return "T";
                        } else {
                            return "NIL";
                        }
                    } else {
                        return "Uno de los argumentos no existe (variable no definida)";
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                    System.out.println("\n" + e);
                    return "NIL";
                }
            } else //Son letras (variables definidas por el usaurio) y numeros
            {
                try {
                    Pattern letra = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
                    Matcher matcher3 = letra.matcher(split[0]);
                    boolean esLetra = matcher3.find();

                    if (esLetra) {
                        int primero = this.variables.get(split[0]);
                        int segundo = Integer.valueOf(split[1]);
                        if (primero == segundo) {
                            return "T";
                        } else {
                            return "NIL";
                        }
                    } else {
                        int primero = Integer.valueOf(split[0]);
                        int segundo = this.variables.get(split[1]);
                        if (primero == segundo) {
                            return "T";
                        } else {
                            return "NIL";
                        }
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                    System.out.println("\n" + e);
                    return "Uno de los argumentos no existe (variable no definida)";
                }
            }
        }
    }

    public String ascendente(String instruccion) {
        instruccion = this.limpiarSimbolo(instruccion);

        Pattern pattern = Pattern.compile("^[0-9 ]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruccion);
        boolean sonNumeros = matcher.find();
        String[] split = instruccion.split(" ");
        Vector<Integer> numeros = new Vector<>();

        if (sonNumeros) {
            for (int i = 0; i < split.length; i++) {
                numeros.add(Integer.valueOf(split[i]));
            }

        } else //Pueden ser numeros y letras (variables) juntos o solo variables
        {
            Pattern letras = Pattern.compile("^[a-zA-Z ]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = letras.matcher(instruccion);
            boolean sonLetras = matcher2.find();
            if (sonLetras) //Son solo letras, por lo tanto, son variables definidas por el usuario
            {
                try {
                    for (int i = 0; i < split.length; i++) {
                        numeros.add(this.variables.get(split[i]));
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                    System.out.println("\n" + e);
                }
            } else //Son letras (variables definidas por el usaurio) y numeros
            {
                for (int i = 0; i < split.length; i++) {
                    Pattern letra = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
                    Matcher matcher3 = letra.matcher(split[i]);
                    boolean esLetra = matcher3.find();
                    if (esLetra) {
                        try {
                            numeros.add(this.variables.get(split[i]));
                        } catch (Exception e) {
                            //TODO: handle exception
                            return "\n" + e;
                        }

                    } else {
                        numeros.add(Integer.valueOf(split[i]));
                    }
                }
            }
        }
        for (int i = 0; i < numeros.size() - 1; i++) {
            if ((numeros.get(i)) > numeros.get(i + 1)) {
                return "NIL";
            }
        }
        return "T";
    }

    public String descendente(String instruccion) {
        instruccion = this.limpiarSimbolo(instruccion);

        Pattern pattern = Pattern.compile("^[0-9 ]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruccion);
        boolean sonNumeros = matcher.find();

        String[] split = instruccion.split(" ");
        Vector<Integer> numeros = new Vector<>();

        if (sonNumeros) {
            for (int i = 0; i < split.length; i++) {
                numeros.add(Integer.valueOf(split[i]));
            }

        } else {
            Pattern letras = Pattern.compile("^[a-zA-Z ]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = letras.matcher(instruccion);
            boolean sonLetras = matcher2.find();
            if (sonLetras) //Son solo letras, por lo tanto, son variables definidas por el usuario
            {
                try {
                    for (int i = 0; i < split.length; i++) {
                        numeros.add(this.variables.get(split[i]));
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                    System.out.println("\n" + e);
                }
            } else //Son letras (variables definidas por el usaurio) y numeros
            {
                for (int i = 0; i < split.length; i++) {
                    Pattern letra = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);
                    Matcher matcher3 = letra.matcher(split[i]);
                    boolean esLetra = matcher3.find();
                    if (esLetra) {
                        try {
                            numeros.add(this.variables.get(split[i]));
                        } catch (Exception e) {
                            //TODO: handle exception
                            return "\n" + e;
                        }

                    } else {
                        numeros.add(Integer.valueOf(split[i]));
                    }
                }
            }

        }

        for (int i = 0; i < numeros.size() - 1; i++) {
            if ((numeros.get(i)) < numeros.get(i + 1)) {
                return "NIL";
            }
        }
        return "T";
    }

    public String limpiarSimbolo(String instruccion) {
        instruccion = instruccion.trim().replaceAll("\\s+", " ");
        StringBuilder editado = new StringBuilder(instruccion);
        editado.deleteCharAt(0);
        editado.deleteCharAt(editado.length() - 1);
        for (int i = 0; i < editado.length(); i++) {
            String caracter = String.valueOf(editado.charAt(i));
            if (caracter.equals("<") || caracter.equals(">")) {
                editado.deleteCharAt(i);
            }
        }
        instruccion = new String(editado).trim().replaceAll("\\s+", " ");
        return instruccion;
    }

    public String list(String instruccion) {
        String list = "(";
        instruccion = this.limpiar(instruccion, "l", "t", 3);

        String[] split = instruccion.split(" ");
        for (int i = 0; i < split.length; i++) {
            Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(split[i]);
            boolean numeros = matcher.find();
            if (numeros) {
                list += split[i] + " ";
            } else //significa que son letras, se verifica si ese conjunto de letras es una variable
            {
                if (this.variables.containsKey(split[i])) {
                    list += this.variables.get(split[i]) + " "; //una variable con valor numerico
                } else if (this.listas.containsKey(split[i])) {
                    list += this.listas.get(split[i]) + " "; //una variable que es lista
                } else {
                    list += split[i] + " "; //no era ninguna variable, por lo que solo se le agrega la letra
                }
            }
        }
        list = list.trim();
        list += ")";
        return list;
    }

    public String listp(String instruccion) {
        instruccion = this.limpiar(instruccion, "l", "p", 4);
        //aqui debe ir un codigo para convertir la "instruccion" si es que esta viene con una funcion definida por el usuario
        Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(instruccion);
        boolean esListp = matcher.find();  //El matcher.find() cambia cada vez que se utiliza, por lo que se guarda el match en la varibale "esAtom"
        if (esListp) {
            return "NIL";
        } else {
            Pattern variable = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = variable.matcher(instruccion);
            if (matcher2.find()) {
                try {
                    int valor = this.variables.get(instruccion);
                    return "NIL";
                } catch (Exception e) {
                    return "T";
                }
            }
            return "T";
        }
    }

    public String Quote(String arg) {
        return arg;
    }

    public String Cond(String args) {
        String[] lineas = args.split("\\n");
        String txt = "";
        String operador = "";
        String valor1 = "";
        String valor2 = "";
        String accion = "";
        String linea = "";
        String expresion = "";
        String funcion = "";
        String expresion_resp = "";
        String parametro = "";
        for (int i = 0; i < lineas.length; i++) {//ciclo que recorre todas las líneas
            expresion = "";
            operador = "";
            valor1 = "";
            valor2 = "";
            accion = "";
            linea = lineas[i];
            funcion = "";
            expresion_resp = "";
            parametro = "";
            for (int j = 0; j < linea.length(); j++) {// quita parentesis de la expresion
                if (String.valueOf(linea.charAt(j)).equals("(") || String.valueOf(linea.charAt(j)).equals(")")) {
                    //no pasa nada
                } else {
                    expresion = expresion + linea.charAt(j);
                }
            }

            operador = expresion.substring(0, 1).trim();

            expresion = expresion.substring(1).trim(); //quita operador de la expresion 
            expresion_resp = expresion;//Se guarda otra expresion sin operador por si es una funcion y no una variable.
            boolean bandera = false;

            for (int x = 0; x < expresion.length(); x++) {	//ciclo para encontrar primer valor			
                if (String.valueOf(expresion.charAt(x)).equals(" ")) {
                    bandera = true;//encontro un espacio ya no concatena más al valor
                } else {
                    if (bandera == false) {
                        valor1 = valor1 += expresion.charAt(x);
                    }
                }
            }

            valor1 = valor1.trim();
            expresion = expresion.substring(valor1.length()).trim(); //quita valor1 de la expresion
            boolean bandera2 = false;

            for (int w = 0; w < expresion.length(); w++) {	//ciclo para encontrar primer valor			
                if (String.valueOf(expresion.charAt(w)).equals(" ")) {
                    bandera2 = true;//encontro un espacio ya no concatena más al valor
                } else {
                    if (bandera2 == false) {
                        valor2 = valor2 += expresion.charAt(w);
                    }
                }
            }

            valor2.trim();
            expresion = expresion.substring(valor2.length()).trim(); //quita valor2 de la expresion (dejando solo la accion)
            accion = expresion;

            //--------------------------------ya estan todos separados------------------------------------------
            //-------------------------------comienza la evaluación---------------------------------------------
            if (variables.containsKey(valor1)) {//el valor1 es una variable
                int val1 = variables.get(valor1); //llamar al Hash con nombre de la variable
                int val2 = Integer.parseInt(valor2);
                switch (operador) {
                    case "=":
                        if (val1 == val2) {
                            //hacer accion
                            if (txt.equals("")) {
                                txt = accion;
                            }
                        }
                        break;
                    case ">":
                        if (val1 > val2) {
                            //hacer accion
                            if (txt.equals("")) {
                                txt = accion;
                            }
                        }
                        break;
                    case "<":
                        if (val1 < val2) {
                            //hacer accion
                            if (txt.equals("")) {
                                txt = accion;
                            }
                        }
                        break;
                }
            } else {
                //el valor1 no es una variable es función
                // hay que reajustar las variables.
                valor2 = "";

                funcion = valor1;
                expresion_resp = expresion_resp.substring(funcion.length()).trim();//se quita la funcion de la expresion
                boolean bandera3 = false;

                for (int y = 0; y < expresion_resp.length(); y++) {//ciclo para encontrar el parámetro de la función
                    if (String.valueOf(expresion_resp.charAt(y)).equals(" ")) {
                        bandera3 = true;// encontro un espacio
                    } else {
                        if (bandera3 == false) {
                            parametro = parametro + expresion_resp.charAt(y);
                        }
                    }
                }

                parametro = parametro.trim();
                expresion_resp = expresion_resp.substring(parametro.length()).trim();
                boolean bandera4 = false;

                for (int t = 0; t < expresion_resp.length(); t++) {
                    if (String.valueOf(expresion_resp.charAt(t)).equals(" ")) {
                        bandera4 = true;//encontro un espacio
                    } else {
                        if (bandera4 == false) {
                            valor2 = valor2 + expresion_resp.charAt(t);
                        }
                    }
                }

                valor2 = valor2.trim();
                expresion_resp = expresion_resp.substring(valor2.length());
                accion = expresion.trim();
                //-------------------------------------Ya estan todos separados-------------------------------
                //llamar a la función con el parámetro encontrado y colocar el resultado en resultado
                int resultado = 0;
                int val3 = Integer.parseInt(valor2);
                switch (operador) {
                    case "=":

                        if (resultado == val3) {
                            //hacer accion				
                        }
                        break;
                    case ">":
                        if (resultado > val3) {
                            //hacer accion
                        }
                        break;
                    case "<":
                        if (resultado < val3) {
                            //hacer accion
                        }
                        break;
                }
            }

        }
        return txt;
    }

    public void Evaluar(String expresion, int funcion) {
        if (funcion == 0) {
            System.out.println("Expresion invalida");
        } else if (funcion == 1) {
            setq(expresion);
        } else if (funcion == 2) {
            System.out.println(atom(expresion));
        } else if (funcion == 3) {
            System.out.println(list(expresion));
        } else if (funcion == 4) {
            System.out.println(listp(expresion));
        } else if (funcion == 5) {
            System.out.println(equal(expresion));
        } else if (funcion == 6) {
            System.out.println(descendente(expresion));
        } else if (funcion == 7) {
            System.out.println(ascendente(expresion));
        } else if (funcion >= 8 && funcion <= 11) {
            expresion = expresion.trim().replaceAll("\\s+", " ");
            StringBuilder editado = new StringBuilder(expresion);
            editado.deleteCharAt(0);
            editado.deleteCharAt(editado.length() - 1);
            expresion = new String(editado);
            System.out.println(calculadora.Evaluate(PrefixToPosfix.convPrefixToPosfix(expresion), variables));
        } else if (funcion == 12) {
            System.out.println(Quote(limpiar(expresion, "q", "e", 4)));
        } else if (funcion == 13) {
            expresion = limpiar(expresion, "c", "d", 3);
            expresion = expresion.replaceAll("\\)\\) \\(\\(", "))\n((");
            System.out.println(Cond(expresion));
            
        } else if (funcion == 14) {
            System.out.println("Instruccion no soportada ");
        }
    }

    public boolean validarFunciones(String expresion) {
        boolean isExpression = false;
        boolean flag = false;
        for (int i = 0; i < expresion.length(); i++) {
            if (expresion.charAt(i) == '(' && flag) {
                isExpression = true;
                break;
            } else if (expresion.charAt(i) == '(') {
                flag = true;
            } else if (expresion.charAt(i) == ')') {
                return false;
            }
        }
        return isExpression;
    }

    public void leerFunciones(String expresion) {
        boolean finish = false;
        int charfinal = 0;
        while (!finish) {
            if (!validarFunciones(expresion)) {
                int charinicio = 0;
                for (int i = charfinal; i < expresion.length(); i++) {
                    if (expresion.charAt(i) == '(') {
                        charinicio = i + 1;
                    } else if (expresion.charAt(i) == ')') {
                        charfinal = i;
                        break;
                    }
                }
                System.out.println(expresion.substring(charinicio, charfinal));
                finish = true;

            }
        }
    }
    
    public static void defun(String expresion, HashMap<String, String> funciones) {
        int parentesis = 0;
        String temp = "";
        for (int i = 2; i < expresion.length(); i++) {
            temp = temp + expresion.charAt(i);
            if (expresion.charAt(i) == '(') {
                parentesis++;
            } else if (expresion.charAt(i) == ')') {
                parentesis--;
            }

            funciones.put("" + expresion.charAt(0), temp);
        }
    }
    
    public void ejecutarFuncion(String key){
        
    }
    
    /*public ArrayList<String> anidado(String expresion){

    }*/
}
