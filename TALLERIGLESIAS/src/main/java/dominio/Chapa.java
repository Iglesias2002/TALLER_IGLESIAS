package dominio;

import java.io.Serializable;

public class Chapa extends Reparacion implements Serializable{

	public Chapa(int identificador, String descripcion, float costeReparacion) {
		super(identificador, descripcion, costeReparacion);		
	}
	
	@Override
	public float costeTotal() {		
		setCoste((float) ((30 * getNumHoras()) + (getCosteReparacion() * 1.3)));
	return getCoste();
	}
}
