package com.br.marlon.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;

    private Integer anoNascimento;

    private Integer anoFalecimento;

    private Integer numeroDeDownloads;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro() {
    }

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        Autor autor = new Autor(dadosLivro.autores().get(0));
        this.idioma = dadosLivro.idioma().get(0);
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
        public String toString() {
            return "Título: " + titulo + "\n" +
                    "Autor: " + autor + "\n" +
                    "Idioma: " + idioma + "\n" +
                    "Downloads: " + numeroDeDownloads + "\n" +
                    "----------------------------------------";
    }
}
