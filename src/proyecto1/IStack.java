/**
 * Interfaz IStack. Se implementara en la clase StackArrayList
 * Fecha de creacion: 24/03/2022
 */

package proyecto1;

/**
 * @author moises.alonso
 * @param <T>
 *
 */
public interface IStack<T> {

    void push(T value);

    T pull();

    T peek();

    int count();

    boolean isEmpty();
}
