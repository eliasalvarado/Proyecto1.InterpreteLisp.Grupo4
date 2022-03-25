package proyecto1;

import java.util.Arrays;
import java.util.*;

public class Proyecto1 {

    public static void main(String[] args) {
        String instrucciones = Lector.leerArchivo("F:\\Programacion\\Proyecto1\\src\\archivos\\convertor.txt");
        System.out.println(instrucciones);
        Interprete inter = new Interprete();
        inter.leerFunciones(instrucciones);
    }
    
}
