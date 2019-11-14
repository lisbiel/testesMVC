package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
class REQ09CadastrarEmprestimo {
	@Autowired
	EmprestimoRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;
	
	@Test
	void CT01CadastrarEmprestimoSucesso() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		Emprestimo emprestimo = new Emprestimo(livro.getIsbn(), usuario.getRa());
		assertThat(emprestimo.getIsbn().equals(livro.getIsbn()) && emprestimo.getRa().equals(usuario.getRa()));
	}
	
	@Test
	void CT01CadastrarEmprestimoLivroInexistente() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		//Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		Emprestimo emprestimo = new Emprestimo("1234", usuario.getRa());
		assertThat(emprestimo.getIsbn().isEmpty());
	}

}
