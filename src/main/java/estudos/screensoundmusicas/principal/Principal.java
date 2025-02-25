package estudos.screensoundmusicas.principal;

import estudos.screensoundmusicas.model.Artista;
import estudos.screensoundmusicas.model.Musica;
import estudos.screensoundmusicas.model.Tipo;
import estudos.screensoundmusicas.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.apache.logging.log4j.util.StringBuilders.equalsIgnoreCase;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ArtistaRepository artistaRepository;
    private List<Artista> artistas = new ArrayList<>();


    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void main() {
        var opcao = -1;
        while (opcao != 9) {
            var menu = """
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    9- Sair
                    """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }



    private void cadastrarArtista() {
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = scanner.nextLine();
        System.out.println("Informe o tipo desse artista: (solo, dupla, banda)");
        var tipoArtista = scanner.nextLine();

        Tipo tipoCategoria = Tipo.valueOf(tipoArtista.toUpperCase());
        Artista artista = new Artista(nomeArtista, tipoCategoria);
        artistaRepository.save(artista);
        System.out.println("Adicionado com sucesso!");
        System.out.println("Deseja cadastrar outro artista? (S/N)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            cadastrarArtista();
        } else if (resposta.equalsIgnoreCase("N")) {
            main();
        } else {
            System.out.println("Inválido.");
        }
    }

    private void cadastrarMusica() {
        System.out.println("Cadastrar música de que artista?  ");
        var nome = scanner.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()) {
            System.out.println("Qual o nome da música do artista? ");
            var nomemusica = scanner.nextLine();
            Musica musica = new Musica(nomemusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        } else{
            System.out.println("Artista não encontrado!");
        }

    }

    private void listarMusicas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(System.out::println);
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Qual o nome do artista que deseja buscar? ");
        var nomeArtista = scanner.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if (artista.isPresent()) {
            System.out.println("O artista foi encontrado!");
            List<Musica> musicaDoArtista = artista.get().getMusicas();
            musicaDoArtista.forEach(System.out::println);
        } else {
            System.out.println("Não foi encontrado!");
        }
    }



}
