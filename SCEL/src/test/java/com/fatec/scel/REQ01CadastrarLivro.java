package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootTest
class REQ01CadastrarLivro {

	@Autowired
	LivroRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	/**
	 * Verificar o comportamento da classe LivroRepository
	 */
	@Test
	public void CT01CadastrarLivroComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// entao
		assertEquals(1, repository.count());
	}

	@Test
	public void CT02CadastrarLivroComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// given:
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertTrue(violations.isEmpty());
	}

	@Test
	public void CT03DeveDetectarTituloInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "", "Delamaro");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O titulo deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT04DetecarAutorInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "");
		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
		// then:
		assertEquals(violations.size(), 1);
		assertEquals("Autor deve ter entre 1 e 50 caracteres", violations.iterator().next().getMessage());
	}
	
	@Test
	public void CT05DetectarIdNulo() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setId(null);
		assertThat(livro.getId()).isEqualTo(null);
	}
	
	@Test
	public void CT06DetectarIdValido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setId((long) 1234);
		assertThat(livro.getId()).isEqualTo(1234);
	}
	
	@Test
	public void CT07DetectarTituloNulo() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setTitulo(null);
		assertThat(livro.getTitulo()).isNotEqualTo(null);
	}
	
	@Test
	public void CT08DetectarTituloValido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setTitulo("Teste de Titulo");
		assertThat(livro.getTitulo()).isEqualTo("Teste de Titulo");
	}
	
	@Test
	public void CT09DetectarAutorNulo() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setAutor(null);
		assertThat(livro.getAutor()).isEqualTo(null);
	}
	
	@Test
	public void CT10DetectarAutorValido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setAutor("Luis");
		assertThat(livro.getAutor()).isEqualTo("Luis");
	}
	
	@Test
	public void CT11DetectarISBNNulo() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setIsbn(null);
		assertThat(livro.getAutor()).isNotEqualTo(null);
	}
	
	@Test
	public void CT12DetectarISBNValido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		// when:
		livro.setIsbn("1234");
		assertThat(livro.getIsbn()).isEqualTo("1234");
	}
}
