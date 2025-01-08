package com.br.marlon.literalura.repository;

import com.br.marlon.literalura.model.Autor;
import com.br.marlon.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByAutor(String autor);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> buscarAutoresPorAno(@Param("ano") Double ano);




}
