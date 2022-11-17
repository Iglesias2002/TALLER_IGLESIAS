package dominio;

import java.io.Serializable;

public class Reparacion extends Trabajo  implements Serializable{
	private float costeReparacion;
	public Reparacion(int identificador, String descripcion, float costeReparacion) {
		super(identificador, descripcion);
		this.costeReparacion = 0;
	}	
	public float getCosteReparacion() {
		return costeReparacion;
	}
	public void setCosteReparacion(float costeReparacion) {
		this.costeReparacion = costeReparacion;
	}
	@Override
	public void aumentarCostes(float costes) {
		this.costeReparacion = costes + costeReparacion;		
	}
	@Override
	public String toString() {
		return "Coste reparacion =" + costeReparacion + ", Identificador=" + getIdentificador()
				+ ", NumHoras=" + getNumHoras() + ", Descripcion=" + getDescripcion() + ", Finalizado="
				+ isFinalizado() + ", Coste=" + getCoste() + "]";
	}	
}
