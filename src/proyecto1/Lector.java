/**
 * Clase Lector. Clase encargada de la lectura de los distintos archivos de texto
 * Autores: 
 *      Herber Sebastian Silva Muñoz -	21764
 *      Daniel Esteban Morales Urizar - 21785 
 *      Elias Alberto Alvarado Raxon -	21808
 * Fecha de creacion: 24/03/2022
 */

package proyecto1;

import java.io.File;
import java.util.*;

public class Lector
{
    /** 
     * @param path
     * @return ArrayList<String>
     */
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
    
    /** 
     * @param instrucciones
     */
    public static void ejecutarInstruccion(String instrucciones){
        
    }
}
