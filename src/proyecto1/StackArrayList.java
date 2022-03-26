/**
 * Creacion de una pila basada en ArrayList
 * @author Sebastian Silva
 * @param <T>
 * @date 18/02/2022
 */
package proyecto1;
import java.util.ArrayList;
public class StackArrayList<T> implements IStack<T> {
//ArrayList de valores
    private ArrayList<T> data;
    
    //Constructor
    public StackArrayList() {
        data = new ArrayList<>();
    }
    
    /*
     * Introduce un valor al incio de la pila
     * @param T
     */
    @Override
    public void push(T value) {
        data.add(0, value);
    }
    
    /*
     * Obtiene el valor del inicio de la pila y lo elimina
     * @return T
     */
    @Override
    public T pull() {
        if (this.isEmpty()) {
            return null;
        } else {
            return data.remove(0);
        }
    }
    
    /*
     * Obtiene el valor del inicio de la pila y no lo elimina
     * @return T
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return data.get(0);
        }
    }
    
    /*
     * Obtiene la cantidad de elementos dentro de la pila
     * @return int
     */
    @Override
    public int count() {
        return data.size();
    }
    /*
     * Verifica si la pila esta vacia
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
