
import java.util.HashMap;

public class Controlador
{
    private HashMap<String, Integer> variables = new HashMap<>();

    public void setq(String instruccion)
    {
        /*StringBuilder editado = new StringBuilder(instruccion);
        for(int i = 0; i < editado.length(); i++)
        {
            String caracter = String.valueOf(editado.charAt(i));
            if(caracter.equals("(") || caracter.equals(")")) editado.deleteCharAt(i);
        }
        int s = editado.indexOf("s");
        int q = editado.indexOf("q");
        if((q - s) == 3) editado.delete(s, q + 1);
        instruccion = new String(editado).trim();
        instruccion = instruccion.replaceAll("\\s+", " ");*/

        instruccion = this.limpiar(instruccion, "s", "q", 3);
        String[] split = instruccion.split(" ");
        this.variables.put(split[0], Integer.parseInt(split[split.length - 1]));
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
        return "\n" + String.valueOf(this.variables);
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
