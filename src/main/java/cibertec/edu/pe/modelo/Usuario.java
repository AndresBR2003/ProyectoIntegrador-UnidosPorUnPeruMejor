package cibertec.edu.pe.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;
	
	private String DNI_CE;
	private String Celular;

	private String email;
	private String password;
	
	private int rol;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Programa programa ;
	

	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Collection<Rol> roles;
	
	@ManyToOne
	@JoinTable(
			name = "usuarios_estados",
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "estado_id", referencedColumnName = "idEst")
			)
	private EstadoUsuario estado;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Formulario formulario;

	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Donaciones> donaciones = new ArrayList<>();
	
	

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa =  programa;
	}

	public Programa getIdPrograma() {
		return programa;
	}

	public void setIdPrograma(Programa programa) {
		this.programa = programa;
	}
	
	
	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public List<Donaciones> getDonaciones() {
		return donaciones;
	}

	public void setDonaciones(List<Donaciones> donaciones) {
		this.donaciones = donaciones;
	}

	public EstadoUsuario getEstado() {
		return estado;
	}

	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public String getDNI_CE() {
		return DNI_CE;
	}

	public void setDNI_CE(String dNI_CE) {
		DNI_CE = dNI_CE;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}
	

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		  for (Rol rol : roles) {
		    authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		  }
		  return authorities;
	}
	

	public String getUsername() {
	  return this.email;
	}

	public Usuario(Long id, String nombre, String apellido, String dni_ce,String celular , String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.DNI_CE = dni_ce;
		this.Celular = celular;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario(String nombre, String apellido, String dni_ce,String celular, String email, String password, Collection<Rol> roles) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.DNI_CE = dni_ce;
		this.Celular = celular;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.estado = new EstadoUsuario(1,"sin programa");
	}
	
	
	public Usuario(Long id, String nombre, String apellido, String dNI_CE, String celular, String email,
			String password, Collection<Rol> roles, EstadoUsuario estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI_CE = dNI_CE;
		Celular = celular;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.estado = estado;
	}
	
	public Usuario() {
		
	}

	public Usuario(Long id, String nombre, String apellido, String dNI_CE, String celular, String email,
			String password, int rol, Collection<Rol> roles, EstadoUsuario estado, Formulario formulario,
			List<Donaciones> donaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI_CE = dNI_CE;
		Celular = celular;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.roles = roles;
		this.estado = estado;
		this.formulario = formulario;
		this.donaciones = donaciones;
	}

	public Usuario(Long id, String nombre, String apellido, String dNI_CE, String celular, String email,
			String password, int rol, Programa programa, Collection<Rol> roles, EstadoUsuario estado,
			Formulario formulario, List<Donaciones> donaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI_CE = dNI_CE;
		Celular = celular;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.programa = programa;
		this.roles = roles;
		this.estado = estado;
		this.formulario = formulario;
		this.donaciones = donaciones;
	}


	

}
