import java.util.Scanner;
import java.util.Vector;

public class Principal
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Controlador controlador = new Controlador();
        String instruccion = "";

        System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        controlador.setq(instruccion);
        System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        controlador.setq(instruccion);
        System.out.println(controlador.imprimir());


        System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        System.out.println("\nList: " + controlador.listp(instruccion));
        /*instruccion = controlador.limpiar(instruccion, "l", "t", 3);
        System.out.println("\nInstruccion: '" + instruccion + "'");
        String[] split = instruccion.split(" ");
        for(int i = 0; i < split.length; i++)
        {
            System.out.println("\nSplit[" + i + "]: '" + split[i] + "'");
        }*/

        //System.out.println(controlador.imprimir());
        //System.out.println("\nListp: " + controlador.listp(instruccion));
        //controlador.list(instruccion);
        //System.out.println("\nAscendente: '" + controlador.ascendente(instruccion) + "'");
        //System.out.println("\nDescendente: '" + controlador.descendente(instruccion) + "'");
        //controlador.setq(instruccion);

        /*System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        instruccion = controlador.limpiar(instruccion, "a", "m", 3);
        System.out.println("\nLimpiado: '" + instruccion + "'");
        String[] k = instruccion.split(" ");
        System.out.println("\nNombre: '" + k[0] + "' Valor: '" + k[1] + "'" + " Cantidad: " + k.length);
        //controlador.setq(instruccion);
        System.out.println(controlador.atom(instruccion));

        System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        System.out.println(controlador.atom(instruccion));*/

        
        /*System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        controlador.setq(instruccion);
        
        System.out.println("\nIngrese la linea: ");
        instruccion = scanner.nextLine();
        controlador.setq(instruccion);
        
        
        System.out.println(controlador.imprimir());*/
        
        
        //System.out.println(controlador.operaciones());
        

        /*--------------------------------------------------
        instruccion = instruccion.trim().replaceAll("\\s+", " ");
        StringBuilder editado = new StringBuilder(instruccion);
        
        System.out.println("\nSin espacios: '" + instruccion + "'");
        
        editado.deleteCharAt(0);
        editado.deleteCharAt(editado.length() - 1);
        
        System.out.println("\nEditado: '" + editado + "'");
        
        String lista = "";
        int inicio = 0;
        int fin = 0;
        for(int i = 0; i < editado.length(); i++)
        {
            String caracter = String.valueOf(editado.charAt(i));
            if(caracter.equals("(")) inicio = i;
            if(caracter.equals(")")) fin = i;
        }

        System.out.println("\nInicio: '" + inicio + "'");
        System.out.println("\nFin: '" + fin + "'");

        for(int x = inicio; x <= fin; x++)
        {
            lista += String.valueOf(editado.charAt(x));
        }

        System.out.println("\nLista: '" + lista + "'");
        
        int s = editado.indexOf("s");
        int q = editado.indexOf("q");
        if((q - s) == 3) editado.delete(s, q + 1);
        instruccion = new String(editado).trim();

        System.out.println("\nInstruccion: '" + instruccion + "'");
        
        System.out.println("\nProbando: '" + instruccion.replaceAll("\\s+", " ") + "'");

        instruccion = instruccion.replaceAll("\\s+", " ");

        String[] split = instruccion.split(" ");

        System.out.println("\nNombre: '" + split[0] + "' Valor: '" + split[1] + "'" + " Cantidad: " + split.length);
        -------------------------------------------------------*/
        
        
        /*instruccion = instruccion.trim().replaceAll("\\s+", " ");
        StringBuilder editado = new StringBuilder(instruccion);
        System.out.println("\nSin espacios: '" + instruccion + "'");

        editado.deleteCharAt(0);
        editado.deleteCharAt(editado.length() - 1);
        System.out.println("\nEditado: '" + editado + "'");
        
        int indexInicio = editado.indexOf("s");
        int indexFinal = editado.indexOf("q");
        if((indexFinal - indexInicio) == 3) editado.delete(indexInicio, indexFinal + 1);
        instruccion = new String(editado).trim();
        instruccion = instruccion.replaceAll("\\s+", " ");

        System.out.println("\nInstruccion: '" + instruccion + "'");
        
        System.out.println("\nProbando: '" + instruccion.replaceAll("\\s+", " ") + "'");

        String[] split = instruccion.split(" ");

        System.out.println("\nNombre: '" + split[0] + "' Valor: '" + split[1] + "'" + " Cantidad: " + split.length);*/
    }
}