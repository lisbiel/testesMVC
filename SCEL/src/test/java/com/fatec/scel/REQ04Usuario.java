package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
import com.fatec.scel.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
class REQ04Usuario {
	@Autowired
	LivroRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;
	
	@Test
	void CT01SetRASucesso() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		usuario.setRa("1243");
		assertThat(usuario.getRa().equals("1243"));
	}
	
	@Test
	void CT02SetRAErro() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Usuario usuario = new Usuario("", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		assertEquals(violations.size(), 1);
		assertEquals("O ra deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	void CT03SetNomeSucesso() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		usuario.setNome("Crianço Feliz");
		assertThat(usuario.getNome().equals("Crianço Feliz"));
	}
	
	@Test
	void CT03SetNomeErro() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Usuario usuario = new Usuario("1234", "", "hum@berto.com", "09405-240", "Rua ALegre");
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		assertEquals(violations.size(), 1);
		assertEquals("O nome deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	void CT04SetEmailSucesso() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		usuario.setEmail("dois@berto.com");
		assertThat(usuario.getEmail().equals("dois@berto.com"));
	}
	
	@Test
	void CT04SetEmailErro() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "", "09405-240", "Rua ALegre");
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		assertEquals(violations.size(), 1);
		assertEquals("O e-mail deve ser preenchido", violations.iterator().next().getMessage());
	}
	
	@Test
	void CT05SetCEPSucesso() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		usuario.setCep("09589-987");
		assertThat(usuario.getCep().equals("09589-987"));
	}
	
	@Test
	void CT06SetEnderecoSucesso() {
		Usuario usuario = new Usuario("1234", "Humberto Doisberto", "hum@berto.com", "09405-240", "Rua ALegre");
		usuario.setEndereco("Rua Limeira");
		assertThat(usuario.getEndereco().equals("Rua Limeira"));
	}

}
