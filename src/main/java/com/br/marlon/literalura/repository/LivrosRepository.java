package com.br.marlon.literalura.repository;

import com.br.marlon.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
