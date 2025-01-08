package com.br.marlon.literalura.service;

import com.br.marlon.literalura.model.Autor;
import com.br.marlon.literalura.model.DadosAutor;
import com.br.marlon.literalura.model.DadosLivro;
import com.br.marlon.literalura.model.Livro;
import com.br.marlon.literalura.repository.AutorRepository;
import com.br.marlon.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepositorio;

    @Autowired
    private AutorRepository autorRepositorio;

    public void salvarOuAtualizarAutor(Autor autor) {
        autorRepositorio.save(autor);  // Usando o save do JpaRepository, que já resolve salvar ou atualizar
    }

    public void processarLivro(DadosLivro dadosLivro) {
        System.out.println("Título: " + dadosLivro.titulo());
        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            System.out.println("Autor recebido: " + dadosAutor.autor());
            Autor autor = obterAutor(dadosAutor);
            persistirLivro(dadosLivro, autor);
        } else {
            System.out.println("Autor: Não informado.");
        }

        System.out.println("Idioma: " + (dadosLivro.idioma() != null && !dadosLivro.idioma().isEmpty()
                ? dadosLivro.idioma().get(0)
                : "Não informado"));
        System.out.println("Número de Downloads: " + (dadosLivro.numeroDownload() != null
                ? dadosLivro.numeroDownload()
                : "Não informado"));
        System.out.println("=======================================LITER ALURA=========================================");
    }

    public Autor obterAutor(DadosAutor dadosAutor) {
        // Tenta encontrar o autor no banco de dados usando o nome
        Autor autorExistente = autorRepositorio.findByAutor(dadosAutor.autor());

        if (autorExistente == null) {
            // Se o autor não existir, cria um novo autor
            autorExistente = new Autor(dadosAutor.autor(), dadosAutor.anoNascimento(), dadosAutor.anoFalecimento());
            // Salva o novo autor no banco usando o método save do JpaRepository
            salvarOuAtualizarAutor(autorExistente);
        } else {
            // Se o autor já existir, apenas retorna o autor existente
            // Não há necessidade de salvar novamente
            System.out.println("Autor já existente: " + autorExistente.getAutor());
        }

        return autorExistente;
    }

    private void persistirLivro(DadosLivro dadosLivro, Autor autor) {
        Livro livro = new Livro(dadosLivro);
        livro.setAutor(autor);
        livroRepositorio.save(livro);  // Salva o livro no banco
    }

    public List<Livro> buscarLivrosPorIdioma(String idioma) {
        return livroRepositorio.buscarLivrosPorIdioma(idioma);
    }
}

