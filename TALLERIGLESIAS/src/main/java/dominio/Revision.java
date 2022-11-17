package dominio;

import java.io.Serializable;

public class Revision extends Trabajo implements Serializable{
	public Revision(int identificador, String descripcion) {
		super(identificador, descripcion);		
	}
	@Override
	public void aumentarCostes(float costes) {
		System.out.println("No se puede aplicar el aumento de costes para el tipo de trabajo revision");
	}
	@Override
	public float costeTotal() {		
		setCoste(30 * getNumHoras() + 20);
		
		return getCoste();
	}
}
