package dominio;

import java.io.Serializable;

public class Trabajo  implements Serializable{
	private int identificador;
	private String nombreCliente;
	private int numHoras;
	private int tipo;
	private String descripcion;
	private boolean finalizado;
	private float coste;
	public Trabajo(int identificador,String nombreCliente, int numHoras, String descripcion, boolean finalizado,float coste, int tipo) {
		this.identificador = identificador;
		this.nombreCliente = nombreCliente;
		this.numHoras = numHoras;
		this.descripcion = descripcion;
		this.finalizado = finalizado;
		this.coste = coste;
		this.tipo=tipo;
	}
	
	public Trabajo(int identificador,  String descripcion) {
		this.identificador = identificador;
		this.descripcion = descripcion;	
	}	
	public Trabajo(int identificador,  float coste) {
		this.identificador = identificador;
		this.coste = coste;
	}
	public Trabajo(int identificador,   int numHoras) {
		this.identificador = identificador;
		this.numHoras = numHoras;	
	}
	public Trabajo(int identificador, boolean finalizado) {
		this.identificador = identificador;
		this.finalizado = true;
	}
	public Trabajo(int identificador) {
		this.identificador = identificador;
	}
	public Trabajo(int identificador, int numHoras,boolean finalizado,int tipo ,String descripcion) {
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.numHoras = numHoras;	
		this.finalizado = finalizado;
		this.tipo=tipo;
	}
	public Trabajo(int identificador, String nombreCliente, int numHoras, int tipo, String descripcion,boolean finalizado) {
		this.identificador = identificador;
		this.nombreCliente = nombreCliente;
		this.numHoras = numHoras;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.finalizado = finalizado;
	}
	public void aumentarCostes(float costes) {
		
	}
	public void finalizarTrabajo() {
		this.finalizado=true;
	}	
	public float costeTotal() {
		 this.coste = this.coste * this.numHoras;
		 return this.coste;
	}	
	public int aumentarHoras(int horas) {
		this.numHoras=horas+numHoras;	
		return numHoras;
	}
	
	public void mostrarTrabajo() {
	System.out.println("                                    ");
	System.out.println(" El identificador es " + identificador);
	System.out.println(" La descripcion es: " + descripcion);
	System.out.println(" Su precio es: " + costeTotal() + " �");
	System.out.println("                                    ");

	}	

	
	
	
	

	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public float getCoste() {
		return coste;
	}
	public void setCoste(float coste) {
		this.coste = coste;
	} 
	
	 @Override
	    public boolean equals(Object object) {
	        if(identificador==(((Trabajo) object).getIdentificador())) {
	            return true;
	        }else {
	            return false;
	        }
	    }



	@Override
	public String toString() {
		return "Trabajo identificador :" + identificador + ", Cliente :" + nombreCliente + ", Horas :" + numHoras
				+ ", Tipo :" + tipo + ", Descripcion :" + descripcion + ", Estado Finalizado :" + finalizado + ", Coste Piezas=" + coste
				+ "";
	}
}
