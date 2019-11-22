package com.fatec.scel.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.scel.model.Emprestimo;;

@Repository
public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
	
	@Query("SELECT l FROM Emprestimo l WHERE l.isbn = :isbn")
	 public Emprestimo findByIsbn(@Param("isbn") String isbn);
}