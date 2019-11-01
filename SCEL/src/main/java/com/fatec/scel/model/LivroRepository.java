package com.fatec.scel.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.scel.model.Livro;

@Repository
public interface LivroRepository  extends CrudRepository<Livro, Long> {
/*	public void deleteAll() {
	}

	public void save(Livro livro) {
	}

	public int count() {
		return 1;
	}*/
	
	@Query("SELECT l FROM Livro l WHERE l.isbn = :isbn")
	 public Livro findByIsbn(@Param("isbn") String isbn);
}
