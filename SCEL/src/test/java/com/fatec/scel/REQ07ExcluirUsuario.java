package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class REQ07ExcluirUsuario {

	@Autowired
	UsuarioRepository repository;
	static Usuario usuario;
	
	@Test
	public void CT01ExcluirSucesso() {
		// dado que nao existem usuarios cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		repository.save(usuario);
		Usuario ro = repository.findByRa("1234");
		repository.deleteById(ro.getId());
		assertThat(repository.findByRa("3333")).isEqualTo(null);
	}
	
	@Test
	public void CT01ConsultarFalhaExcluirErro() {
		// dado que nao existem usuarios cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		repository.save(usuario);
		Usuario ro = repository.findByRa("1234");
		long id = ro.getId()-1;
		try {
			repository.deleteById(id);
		} catch (RuntimeException e) {
			assertThat(e.getMessage().equals("No class com.fatec.scel.model.Usuario entity with id "+ id +" exists!"));
		}
	}

}
