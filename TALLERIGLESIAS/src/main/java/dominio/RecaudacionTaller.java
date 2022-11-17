package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RecaudacionTaller  implements Serializable{
	private int identificador;
	private int numHoras;
	private float piezas;
	private String descripcion;
	private int tipo;
	private boolean finalizado;
	private float dinero;
	private String valoracionCliente;
	private LocalDateTime horaFinalizacion;
	
	public RecaudacionTaller(int identificador, int numHoras, float piezas, String descripcion, int tipo, boolean finalizado,float dinero,String valoracionCliente, LocalDateTime horaFinalizacion) {
		
		this.identificador = identificador;
		this.numHoras = numHoras;
		this.piezas = piezas;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.finalizado = finalizado;
		this.dinero = dinero;
		this.valoracionCliente = valoracionCliente;
		this.horaFinalizacion = horaFinalizacion;
	}
	public RecaudacionTaller(int identificador) {
		
		this.identificador = identificador;
		
	}
	
	public int getIdentificador() {
		return identificador;
	}


	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}


	public int getNumHoras() {
		return numHoras;
	}


	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}


	public float getPiezas() {
		return piezas;
	}


	public void setPiezas(float piezas) {
		this.piezas = piezas;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public boolean isFinalizado() {
		return finalizado;
	}


	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}


	public float getDinero() {
		return dinero;
	}


	public void setDinero(float dinero) {
		this.dinero = dinero;
	}


	public LocalDateTime getHoraFinalizacion() {
		return horaFinalizacion;
	}


	public void setHoraFinalizacion(LocalDateTime horaFinalizacion) {
		this.horaFinalizacion = horaFinalizacion;
	}
	@Override
	public String toString() {
		return "Identificador :" + identificador + ", Horas :" + numHoras + ", Piezas :" + piezas
				+ ", Descripcion :" + descripcion + ", 	Tipo :" + tipo + ", Estado Finalizado :" + finalizado + ", Dinero :"
				+ dinero + ", Valoracion :" + valoracionCliente + ", Hora Finalizacion=" + horaFinalizacion + "";
	}
}
