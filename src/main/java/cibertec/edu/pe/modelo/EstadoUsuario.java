package cibertec.edu.pe.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estadousuario")
public class EstadoUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEst;
	private String nombreEst;
	
	
	public int getIdEst() {
		return idEst;
	}
	public void setIdEst(int idEst) {
		this.idEst = idEst;
	}
	public String getNombreEst() {
		return nombreEst;
	}
	public void setNombreEst(String nombreEst) {
		this.nombreEst = nombreEst;
	}
	
	public EstadoUsuario(int idEst, String nombreEst) {
		super();
		this.idEst = idEst;
		this.nombreEst = nombreEst;
	}
	
	public EstadoUsuario(String nombreEst) {
		super();
		this.nombreEst = nombreEst;
	}
	public EstadoUsuario() {
		
	}
	
	
	


}
