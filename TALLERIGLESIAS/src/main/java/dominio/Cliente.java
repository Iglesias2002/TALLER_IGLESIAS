package dominio;

import java.io.Serializable;
public class Cliente  implements Serializable{
	private int idusuario;
	private String nombre;
	private int identificador;
	private float costes;
	private String estadoVeiculo;
	private String valoracionCliente;
	
	
	public Cliente(int idusuario, String nombre, int identificador, float costes, String estadoVeiculo,
			String valoracionCliente) {
		
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.identificador = identificador;
		this.costes = costes;
		this.estadoVeiculo = estadoVeiculo;
		this.valoracionCliente = valoracionCliente;
	}

	public Cliente(int identifiacdor) {
		this.identificador = identifiacdor;
	}

	public String getEstadoVeiculo() {
		return estadoVeiculo;
	}

	public void setEstadoVeiculo(String estadoVeiculo) {
		this.estadoVeiculo = estadoVeiculo;
	}

	public String getValoracionCliente() {
		return valoracionCliente;
	}

	public void setValoracionCliente(String valoracionCliente) {
		this.valoracionCliente = valoracionCliente;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public float getCostes() {
		return costes;
	}

	public void setCostes(float costes) {
		this.costes = costes;
	}
	@Override
    public boolean equals(Object object) {
        if(identificador==(((Cliente) object).getIdentificador())) {
            return true;
        }else {
            return false;
        }
    }
	@Override
	public String toString() {
		return "Cliente [idusuario=" + idusuario + ", nombre=" + nombre + ", identificador=" + identificador
				+ ", costes=" + costes + ", estadoVeiculo=" + estadoVeiculo + ", valoracionCliente=" + valoracionCliente
				+ "]";
	}

	
}
