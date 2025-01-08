package com.br.marlon.literalura.repository;

import com.br.marlon.literalura.model.Autor;
import com.br.marlon.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByAutor(String autor);




}
