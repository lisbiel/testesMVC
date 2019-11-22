package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Emprestimo;
import com.fatec.scel.model.EmprestimoRepository;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ11ExcluirEmprestimo {
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
		repository.deleteById(ro.getId());
		assertThat(repository.findByIsbn("3333")).isEqualTo(null);
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
		Emprestimo ro = repository.findByIsbn("3333");
		Long id = ro.getId() - 1;
		try {
			repository.deleteById(id);
		} catch (RuntimeException e) {
			assertThat(e.getMessage().equals("No class com.fatec.scel.model.Emprestimo entity with id "+ id +" exists!"));
		}
	}

}
