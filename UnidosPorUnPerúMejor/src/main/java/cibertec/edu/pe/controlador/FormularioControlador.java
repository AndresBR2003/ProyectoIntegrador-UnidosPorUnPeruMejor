package cibertec.edu.pe.controlador;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import cibertec.edu.pe.modelo.EstadoUsuario;
import cibertec.edu.pe.modelo.Formulario;
import cibertec.edu.pe.modelo.Programa;
import cibertec.edu.pe.modelo.Usuario;
import cibertec.edu.pe.repositorio.EstadoRepositorio;
import cibertec.edu.pe.repositorio.UsuarioRepositorio;
import cibertec.edu.pe.servicio.FormularioServicio;
import cibertec.edu.pe.servicio.ProgramaServicio;
import cibertec.edu.pe.servicio.UsuarioServicio;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@Secured("ROLE_USER")
public class FormularioControlador {
	@Autowired
	private FormularioServicio formServ;
	@Autowired
    private ProgramaServicio programaServicio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;

	
	@RequestMapping("/nuevoForm/{idPro}")
	public ModelAndView  mostrarFormularioDeFormulario(@PathVariable(name = "idPro") Long idPro) {
		ModelAndView m = new ModelAndView("nuevo_form");
		// Obtener el programa y usuario
		Programa programa = programaServicio.get(idPro);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepositorio.findByEmail(authentication.getName());
        System.out.println("" + usuario.getEstado().getIdEst());
        if (usuario.getEstado().getIdEst() == 2) {
            // El usuario tiene el estado "postulante", por lo que no puede volver a crear un formulario
            m.setViewName("error_form");
            m.addObject("error", "Ya tienes un formulario creado");
        } else {
        Formulario form = new Formulario();	
		form.setPrograma(programa);
		m.addObject("formulario", form);
        }
		return m;
	}
	
	@PostMapping("/guardarFormulario")
	public String guardarPrograma(@ModelAttribute("formulario") Formulario form) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario2 = usuarioRepositorio.findByEmail(authentication.getName());
		form.setUsuario(usuario2);
		EstadoUsuario estUsu = estadoRepositorio.getById(2);
		usuario2.setEstado(estUsu);
		formServ.save(form);
		return "redirect:/programas";
	}
	
	@GetMapping
	public ResponseEntity<Usuario> getCurrentUser() {
	    Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return ResponseEntity.ok(usuario);
	}
}
