package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.EmprestimoRepository;
import com.fatec.scel.model.LivroRepository;
import com.fatec.scel.model.Servico;
import com.fatec.scel.model.UsuarioRepository;
import com.fatec.scel.model.Emprestimo;

@RunWith(SpringRunner.class)
@SpringBootTest
class REQ10ConsultarEmprestimo {
	@Autowired
	EmprestimoRepository repository;
	static Emprestimo emprestimo;

	@Test
	public void test() {
		// dado que nao existem emprestimos cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		emprestimo = new Emprestimo("3333", "1234");
		repository.save(emprestimo);
		// entao o sistema valida as informações E envia uma mensagem de aluno
		// cadastrado com sucesso
		Emprestimo ro = repository.findByIsbn("3333");
		assertThat(ro.getIsbn()).isEqualTo(emprestimo.getIsbn());
	}

	@Test
	public void test1() {
		// dado que nao existem emprestimos cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		emprestimo = new Emprestimo("3333", "1234");
		repository.save(emprestimo);
		// entao o sistema valida as informações E envia uma mensagem de aluno
		// cadastrado com sucesso
		assertThat(repository.findByIsbn("1234")).isEqualTo(null);
	}

}
