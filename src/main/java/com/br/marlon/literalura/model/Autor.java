package com.br.marlon.literalura.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;
    private Integer anoNascimento;
    private Integer anoFalecimento;
//    private String livro;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
    }

    public Autor(DadosAutor dadosAutor) {
        this.autor = dadosAutor.autor();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
//        this.livro = dadosAutor.Livro();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalescimento() {
        return anoFalecimento;
    }

    public void setAnoFalescimento(Integer anoFalescimento) {
        this.anoFalecimento = anoFalescimento;
    }

//    public String getLivro() {
//        return livro;
//    }
//
//    public void setLivro(String livro) {
//        this.livro = livro;
//    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
        @Override
        public String toString() {
            String anoNascimentoStr = anoNascimento != null ? anoNascimento.toString() : "Desconhecido";
            String anoFalecimentoStr = anoFalecimento != null ? anoFalecimento.toString() : "Desconhecido";

            return "Autor: " + autor + " (nascido em " + anoNascimentoStr + ", falecido em " + anoFalecimentoStr + ")";
        }
    }
