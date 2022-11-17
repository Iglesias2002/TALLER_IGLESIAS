package dominio;

import java.io.Serializable;

public class Usuarios implements Serializable{
	private int idusuario;
	private String nombre;
	private String contrasenia;
	private int tipo;
	
	public Usuarios(int idusuario, String nombre, String contrasenia, int tipo) {
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.tipo = tipo;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
    public boolean equals(Object object) {
        if(nombre.equals(((Usuarios) object).getNombre())) {
            return true;
        }else {
            return false;
        }
    }
	@Override
	public String toString() {
		return "Usuarios idusuario :" + idusuario + ", nombre :" + nombre + ", contrasenia :" + contrasenia + ", tipo :"
				+ tipo + "";
	}
	

}
