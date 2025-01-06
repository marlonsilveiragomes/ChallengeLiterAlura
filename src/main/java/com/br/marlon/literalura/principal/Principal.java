package com.br.marlon.literalura.principal;

import com.br.marlon.literalura.repository.LivrosRepository;
import com.br.marlon.literalura.service.ConsumoAPI;
import com.br.marlon.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

public class Principal {

    @Component
    public class Principal {

        @Autowired
        private LivrosRepository livroRepository;

        @Autowired
        private ConsumoAPI consumoAPI;

        @Autowired
        private ConverteDados converteDados;

        private final Scanner leitura = new Scanner(System.in);

        public Principal(LivrosRepository livroRepository, ConsumoAPI consumoAPI, ConverteDados converteDados) {
            this.livroRepository = livroRepository;
            this.consumoAPI = consumoAPI;
            this.converteDados = converteDados;
        }

        public void exibeMenu() {
            var opcao = -1;
            while (opcao != 0) {
                var menu = """
                        1 - Buscar Livros por Título
                        2 - Listar Livros no Banco de Dados
                        3 - Listar Autores no Banco de Dados
                        4 - Buscar Autores Vivos
                        5 - Listar Livros em Um Determinado Ano
                        
                        0 - Sair                                 
                        """;

                System.out.println(menu);
                opcao = leitura.nextInt();
                leitura.nextLine();



                switch (opcao) {
                    case 1:
                        buscarLivrosPeloTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            }
        }
