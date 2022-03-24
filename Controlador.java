
import java.util.HashMap;

public class Controlador
{
    private HashMap<String, Integer> variables = new HashMap<>();
    private HashMap<String, String> listas = new HashMap<>();

    public void setq(String instruccion)
    {
        instruccion = instruccion.trim().replaceAll("\\s+", " ");
        StringBuilder editado = new StringBuilder(instruccion);

        editado.deleteCharAt(0);
        editado.deleteCharAt(editado.length() - 1);

        String lista = "";
        int inicio = 0;
        int fin = 0;
        for(int i = 0; i < editado.length(); i++)
        {
            String caracter = String.valueOf(editado.charAt(i));
            if(caracter.equals("(")) inicio = i;
            if(caracter.equals(")")) fin = i;
        }

        boolean esLista = false;

        if(inicio != 0 && fin != 0)
        {
            esLista = true;
            for(int x = inicio; x <= fin; x++)
            {
                lista += String.valueOf(editado.charAt(x));
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

        int s = editado.indexOf("s");
        int q = editado.indexOf("q");
        if((q - s) == 3) editado.delete(s, q + 1);
        instruccion = new String(editado).trim();
        instruccion = instruccion.replaceAll("\\s+", " ");

        instruccion = this.limpiar(instruccion, "s", "q", 3);
        String[] split = instruccion.split(" ");

        if(esLista)
        {
            this.listas.put(split[0], lista);
        }
        else
        {
            this.variables.put(split[0], Integer.parseInt(split[split.length - 1]));
        }
    }

    private String limpiar(String instruccion, String letraInicio, String letraFinal, int largo)
    {
        StringBuilder editado = new StringBuilder(instruccion);
        for(int i = 0; i < editado.length(); i++)
        {
            String caracter = String.valueOf(editado.charAt(i));
            if(caracter.equals("(") || caracter.equals(")")) editado.deleteCharAt(i);
        }
        int indexInicio = editado.indexOf(letraInicio);
        int indexFinal = editado.indexOf(letraFinal);
        if((indexFinal - indexInicio) == largo) editado.delete(indexInicio, indexFinal + 1);
        instruccion = new String(editado).trim();
        instruccion = instruccion.replaceAll("\\s+", " ");
        return instruccion;
    }

    public String imprimir()
    {
        return "\n" + String.valueOf(this.variables) + "\n" + String.valueOf(this.listas);
    }

    public String operaciones()
    {
        String info = "";
        info += "\nPrimera operacion: " + (this.variables.get("x") + this.variables.get("y") + this.variables.get("z"));
        info += "\nSegunda operacion: " + (this.variables.get("z") + this.variables.get("y") - this.variables.get("x"));
        info += "\nTercera operacion: " + (this.variables.get("x") / this.variables.get("y") + this.variables.get("z"));

        return info;
    }
}
