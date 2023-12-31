package cibertec.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.edu.pe.modelo.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
	Rol findByNombre(String nombre);
}
