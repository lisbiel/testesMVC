package com.fatec.scel.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;
import com.fatec.scel.model.Servico;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private Servico servico;

	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ConsultarUsuario");
		modelAndView.addObject("usuarios", repository.findAll());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastraUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("CadastrarUsuario");
		mv.addObject("usuario", usuario);
		return mv;
	}

	@GetMapping("/edit/{ra}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView mostraFormAdd(@PathVariable("ra") String ra) {
		ModelAndView modelAndView = new ModelAndView("AtualizaUsuario");
		modelAndView.addObject("usuario", repository.findByRa(ra)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("ConsultarUsuario");
		modelAndView.addObject("usuarios", repository.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("ConsultarUsuario");
		if (result.hasErrors()) {
			return new ModelAndView("CadastrarUsuario");
		}
		try {
			Usuario jaExiste = null;
			jaExiste = repository.findByRa(usuario.getRa());
			if (jaExiste == null) {
				String endereco = servico.obtemEndereco(usuario.getCep());
				usuario.setEndereco(endereco);
				repository.save(usuario);
				modelAndView = new ModelAndView("ConsultarUsuario");
				modelAndView.addObject("usuarios", repository.findAll());
				return modelAndView;
			} else {
				return new ModelAndView("CadastrarUsuario");
			}
		} catch (Exception e) {
			System.out.println("erro ===> " + e.getMessage());
			return modelAndView; // captura o erro mas nao informa o motivo.
		}
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualiza(@PathVariable("id") Long id, @Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			usuario.setId(id);
			return new ModelAndView("AtualizaUsuario");
		}
		Usuario umUsuario = repository.findById(id).get();
		umUsuario.setRa(usuario.getRa());
		umUsuario.setNome(usuario.getNome());
		umUsuario.setEmail(usuario.getEmail());
		repository.save(umUsuario);
		ModelAndView modelAndView = new ModelAndView("ConsultarUsuario");
		modelAndView.addObject("usuarios", repository.findAll());
		return modelAndView;
	}
}
