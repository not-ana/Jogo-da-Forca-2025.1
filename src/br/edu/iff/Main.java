package br.edu.iff;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.repository.RepositoryException;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Aplicacao aplicacao = Aplicacao.getSoleInstance();

    public static void main(String[] args) {
        
        aplicacao.configurar();

        PalavraAppService palavraAppService = PalavraAppService.getSoleInstance();

        TemaRepository temaRepository = aplicacao.getRepositoryFactory().getTemaRepository();
        TemaFactory temaFactory = aplicacao.getTemaFactory();

        adicionarTemasEPalavrasDeArquivo("src/br/edu/iff/jogo.txt", temaRepository, temaFactory, palavraAppService);

        System.out.print("Iniciar a partida? (sim/nao): ");
        String iniciarPartida = scanner.nextLine().trim().toLowerCase();

        if (iniciarPartida.equals("sim")) {

            System.out.print("Digite o seu nome: ");
            String nomeJogador = scanner.nextLine();

            Jogador jogador = aplicacao.getJogadorFactory().getJogador(nomeJogador);

            try {

                aplicacao.getRepositoryFactory().getJogadorRepository().inserir(jogador);

            } catch (RepositoryException e) {

                System.err.println("Erro ao inserir o jogador: " + e.getMessage());
            }

            jogarRodada(jogador);

        } else {

            System.out.println("Encerrando o jogo.");
        }
    }


    private static void jogarRodada(Jogador jogador) {

        RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();

        Rodada rodada = rodadaAppService.novaRodada(jogador);

        System.out.println("Tema: " + rodada.getTema().getNome());

        do {
            System.out.println("\nTentativas restantes: " + rodada.getQtdeTentativasRestantes());
            System.out.println("Tentativas anteriores: ");

            for (Letra letraTentativa : rodada.getTentativas()) {
                
                letraTentativa.exibir(null);
                System.out.print(" | ");
            }

            System.out.println("\n\n---Palavras---\n"); 
            rodada.exibirItens(null);

            System.out.println("\nCorpo: ");
            rodada.exibirBoneco(null);
            System.out.println();

            System.out.println("(1) Tentar letra");
            System.out.println("(2) Arriscar");
            System.out.print("Digite uma letra: ");

            String escolha = scanner.next();

            switch (escolha) {
                case "1":
                    System.out.print("\n\n Digite a letra: ");
                    rodada.tentar(scanner.next().charAt(0));
                    break;
                case "2":
                    String[] palavrasArriscadas = new String[rodada.getNumPalavras()];
                    scanner.nextLine();
                    for (int i = 0; i < palavrasArriscadas.length; i++) {
                        System.out.print("\n Chute a palavra " + (i + 1) + ": ");
                        palavrasArriscadas[i] = scanner.nextLine();
                    }
                    rodada.arriscar(palavrasArriscadas);
                    break;
                default:
                    System.out.println("\n Opção inválida!");
                    break;
            }
            if (rodada.descobriu()) {
                System.out.println("\n----Você conseguiu " + rodada.getJogador().getNome() + "!!!----");
                System.out.println("Pontuação: " + rodada.calcularPontos());
                break;
            }

        } while (!rodada.encerrou());

        if (!rodada.descobriu()) {
            System.out.println("\n----Você errou " + rodada.getJogador().getNome() + "----");
        }
           
    }

    private static void adicionarTemasEPalavrasDeArquivo(String arquivo, TemaRepository temaRepository, TemaFactory temaFactory, PalavraAppService palavraAppService) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
           
            String linha;

            while ((linha = br.readLine()) != null) {
                
                String[] partes = linha.split(":");
               
                if (partes.length < 2) {
                    
                    System.out.println("Formato de linha inválido: " + linha);
                    continue;
                }

                String nomeTema = partes[0].trim();
                String[] palavras = partes[1].split(",");

                Tema tema = temaFactory.getTema(nomeTema);

                try {

                    temaRepository.inserir(tema);

                } catch (RepositoryException e) {

                    System.err.println("Erro ao inserir o tema '" + nomeTema + "': " + e.getMessage());
                    continue;
                }

                for (String palavra : palavras) {

                    palavra = palavra.trim();

                    try {

                        palavraAppService.novaPalavra(palavra, tema.getId());

                    } catch (Exception e) {

                        System.err.println("Erro ao adicionar a palavra '" + palavra + "' para o tema '" + nomeTema + "': " + e.getMessage());
                    }
                }
            }

        } catch (IOException e) {

            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
