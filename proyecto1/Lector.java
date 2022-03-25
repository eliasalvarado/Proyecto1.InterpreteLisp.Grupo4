package proyecto1;

import java.io.File;
import java.util.*;

public class Lector {

    public static ArrayList<String> leerArchivo(String path) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            File doc = new File(path);
            Scanner obj = new Scanner(doc);
            while (obj.hasNextLine()) {
                lista.add(obj.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static void ejecutarInstruccion(String instrucciones){
        
    }
}
