package cibertec.edu.pe.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.modelo.Programa;
import cibertec.edu.pe.repositorio.ProgramaRepositorio;
import cibertec.edu.pe.servicio.ProgramaServicio;

@Service
public class ProgramaServicioImpl implements ProgramaServicio{
	
	@Autowired
	private ProgramaRepositorio programaRepo;

	@Override
	public List<Programa> listAll(String palabraClave) {
		if(palabraClave != null) {
			return programaRepo.findAll(palabraClave);
		}
		return programaRepo.findByEstado();
	}

	@Override
	public void save(Programa programa) {
		programaRepo.save(programa);
	}

	@Override
	public Programa get(Long idPro) {
		return programaRepo.findById(idPro).get();
	}

	@Override
	public void delete(Long idPro) {
		programaRepo.deleteById(idPro);
		
	}



	



}
