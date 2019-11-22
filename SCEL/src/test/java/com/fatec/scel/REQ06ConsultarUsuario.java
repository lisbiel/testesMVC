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
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class REQ06ConsultarUsuario {

	@Autowired
	UsuarioRepository repository;
	static Usuario usuario;
	
	@Test
	public void CT01ConsultarComSucesso() {
		// dado que nao existem usuarios cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		repository.save(usuario);
		// entao o sistema valida as informações E envia uma mensagem de aluno
		// cadastrado com sucesso
		Usuario ro = repository.findByRa("1234");
		assertThat(ro.getNome()).isEqualTo(usuario.getNome());
	}
	
	@Test
	public void CT01ConsultarFalha() {
		// dado que nao existem usuarios cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		repository.save(usuario);
		// entao o sistema valida as informações E envia uma mensagem de aluno
		// cadastrado com sucesso
		assertThat(repository.findByRa("3333")).isEqualTo(null);
	}

}
