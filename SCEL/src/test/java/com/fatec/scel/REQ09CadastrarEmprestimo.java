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
		repository.save(emprestimo);
		assertEquals(repository.count(), 1);
	}
	
	@Test
	void CT01CadastrarEmprestimoSemISBN() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Emprestimo emprestimo = new Emprestimo("","1234");
		Set<ConstraintViolation<Emprestimo>> violations = validator.validate(emprestimo);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O isbn deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	void CT01CadastrarEmprestimoSemRa() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Emprestimo emprestimo = new Emprestimo("3333","");
		Set<ConstraintViolation<Emprestimo>> violations = validator.validate(emprestimo);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O RA deve ser preenchido", violations.iterator().next().getMessage());
	}

}
