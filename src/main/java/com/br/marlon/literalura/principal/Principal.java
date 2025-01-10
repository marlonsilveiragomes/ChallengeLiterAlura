package com.br.marlon.literalura.principal;

import com.br.marlon.literalura.model.Autor;
import com.br.marlon.literalura.model.DadosAutor;
import com.br.marlon.literalura.model.DadosLivro;
import com.br.marlon.literalura.model.Livro;
import com.br.marlon.literalura.repository.AutorRepository;
import com.br.marlon.literalura.repository.LivroRepository;
import com.br.marlon.literalura.service.ConsumoAPI;
import com.br.marlon.literalura.service.ConverteDados;
import com.br.marlon.literalura.service.LivroService;
import com.br.marlon.literalura.service.RespostaLivros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class Principal {

    private final LivroRepository repositorio;
    private final AutorRepository repositorioAutor;

    @Autowired
    public Principal(LivroRepository repositorio, AutorRepository repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }


    @Autowired
    private LivroService livroService;

    private Scanner leitura = new Scanner(System.in);

    private ConsumoAPI consumo = new ConsumoAPI();

    private ConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "https://gutendex.com/books?search=";


    private List<Livro> livros = new ArrayList<>();

//    private List<Autor> autores = new ArrayList<>();


    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar Livros por Título
                    2 - Listar Livros no Banco de Dados
                    3 - Listar Autores no Banco de Dados
                    4 - Buscar Autores Vivos em Um determinado Ano:
                    5 - Listar Livros por Idioma
                    
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


    private void buscarLivrosPeloTitulo() {
        System.out.println("Digite o título do livro:");
        String titulo = leitura.nextLine();
        String json = consumo.obterDados(ENDERECO + titulo.replace(" ", "%20"));

        RespostaLivros respostaLivros = conversor.obterDados(json, RespostaLivros.class);
        List<DadosLivro> livros = respostaLivros.getResults();

        if (livros != null && !livros.isEmpty()) {
            for (DadosLivro dadosLivro : livros) {
                livroService.processarLivro(dadosLivro);
            }
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }


    }

    private void listarLivrosRegistrados() {
        List<Livro> todosLivros = repositorio.findAll(); // Busca todos os livros no banco

        // Usamos um Set para armazenar títulos únicos
        Set<String> titulosUnicos = new HashSet<>();

        // Filtrar e exibir livros com títulos únicos
        for (Livro livro : todosLivros) {
            if (titulosUnicos.add(livro.getTitulo())) { // Adiciona ao Set; retorna false se já existir
                System.out.println(livro); // Usa o método toString() para exibir no padrão da classe
            }
        }

        // Caso nenhum livro seja encontrado
        if (titulosUnicos.isEmpty()) {
            System.out.println("Nenhum livro encontrado no banco de dados.");
        }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = repositorioAutor.findAll();

        for (Autor autor : autores) {
            System.out.println("Nome do Autor: " + autor.getAutor());
            System.out.println("Data de Nascimento: " + autor.getAnoNascimento());
            System.out.println("Data de Falecimento: " + autor.getAnoFalecimento());
            System.out.println("Livros Escritos:");

            List<Livro> livros = autor.getLivros();
            if (livros.isEmpty()) {
                System.out.println("  Nenhum livro cadastrado.");
            } else {
                for (Livro livro : livros) {
                    System.out.println("  - " + livro.getTitulo());
                }
            }
            System.out.println("-------------------------");
        }
    }

    public void listarAutoresVivos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ano para buscar autores vivos nesse período: ");
        int ano = scanner.nextInt();

        List<Autor> listaAutores = repositorioAutor.buscarAutoresPorAno((double) ano);

        if (listaAutores.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano " + ano);
        } else {
            System.out.println("Autores vivos no ano " + ano + ":");
            for (Autor autor : listaAutores) {
                System.out.println("Nome: " + autor.getAutor());
                System.out.println("Data de Nascimento: " + autor.getAnoNascimento());
                System.out.println("Data de Falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "Ainda vivo"));
                System.out.println("-----");
            }
        }
    }

    public void listarLivrosPorIdioma() {


        Scanner leitura = new Scanner(System.in);

        System.out.println("Selecione o idioma:");
        System.out.println("1 - Espanhol (es)");
        System.out.println("2 - Inglês (en)");
        System.out.println("3 - Francês (fr)");
        System.out.println("4 - Português (pt)");
        System.out.print("Escolha uma opção: ");

        int opcao = leitura.nextInt();
        String idioma = leitura.nextLine();

        switch (opcao) {
            case 1:
                idioma = "es";
                break;
            case 2:
                idioma = "en";
                break;
            case 3:
                idioma = "fr";
                break;
            case 4:
                idioma = "pt";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        List<Livro> livros = livroService.buscarLivrosPorIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma selecionado (" + idioma + ").");
        } else {
            System.out.println("Livros no idioma " + idioma + ":");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getAutor());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Número de Downloads: " + livro.getNumeroDownloads());
                System.out.println("-----");
            }
        }
    }

}

















