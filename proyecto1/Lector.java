package proyecto1;

import java.io.File;
import java.util.*;

public class Lector {

    public static String leerArchivo(String path) {
        String instrucciones = "";
        try {
            File doc = new File(path);
            Scanner obj = new Scanner(doc);
            while (obj.hasNextLine()) {
                instrucciones = instrucciones + obj.nextLine();
            }
            String[] cadenas = instrucciones.split(" ");
            instrucciones = "";
            for(String cadena: cadenas){
                instrucciones = instrucciones+cadena;
            }
            instrucciones = instrucciones.replace("_", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instrucciones;
    }
    
    public static void ejecutarInstruccion(String instrucciones){
        
    }
}
