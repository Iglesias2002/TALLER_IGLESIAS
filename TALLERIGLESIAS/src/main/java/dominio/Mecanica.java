package dominio;

import java.io.Serializable;

public class Mecanica extends Reparacion  implements Serializable{

	public Mecanica(int identificador, String descripcion, float costeReparacion) {
		super(identificador, descripcion, costeReparacion);		
	}
	@Override
	public float costeTotal() {		
		setCoste((float) ((30 * getNumHoras()) + (getCosteReparacion() *1.1)));
		return getCoste();
	}
	
}
