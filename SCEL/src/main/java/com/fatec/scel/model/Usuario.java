package com.fatec.scel.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NaturalId;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NaturalId
	@Column(nullable = false, length = 4)
	@NotEmpty(message = "O ra deve ser preenchido")
	String ra;
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O nome deve ser preenchido")
	String nome;
	
	public Usuario() {
	}
	public Usuario(@NotEmpty(message = "O ra deve ser preenchido") String ra,
			@NotEmpty(message = "O nome deve ser preenchido") String nome,
			@NotEmpty(message = "O e-mail deve ser preenchido") String email, String cep, String endereco) {
		this.ra = ra;
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O e-mail deve ser preenchido")
	String email;
	String cep;
	String endereco;
//getters e setters omitidos
}