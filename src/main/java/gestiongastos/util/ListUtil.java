package gestiongastos.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Bárbara Salinas
 *
 */
public class ListUtil {
	/**
	 * Casteo de listas
	 * 
	 * @param clazz clase a la que se ha de castear
	 * @param c colección de objetos a castear
	 * @return Lista de objetos de la clase soliticitada
	 */
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
