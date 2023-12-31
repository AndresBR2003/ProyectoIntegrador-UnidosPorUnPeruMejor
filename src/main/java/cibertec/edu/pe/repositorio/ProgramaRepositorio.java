package cibertec.edu.pe.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cibertec.edu.pe.modelo.Programa;


public interface ProgramaRepositorio extends JpaRepository<Programa, Long>{

	@Query("SELECT p FROM Programa p WHERE p.nombrePro LIKE %?1% AND p.estado = true")
	public List<Programa> findAll(String palabraClave);
	
	@Query("SELECT p FROM Programa p WHERE p.estado = true")
	public List<Programa> findByEstado();
	

}
