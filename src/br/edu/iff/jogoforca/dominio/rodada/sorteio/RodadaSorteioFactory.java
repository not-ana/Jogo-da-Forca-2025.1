package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;

import java.util.Arrays;
import java.util.Random;


public class RodadaSorteioFactory extends RodadaFactoryImpl {

    private static RodadaSorteioFactory soleInstance;

    public static void createSoleInstance(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        if(soleInstance == null) {
            soleInstance = new RodadaSorteioFactory(repository, temaRepository, palavraRepository);
        }
    }

    public static RodadaSorteioFactory getSoleInstance() {
        if(soleInstance == null) {
            throw new RuntimeException("Precisa chamar o createSoleInstance antes de getSoleInstance");
        }
        return soleInstance;
    }

    private RodadaSorteioFactory(RodadaRepository repository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(repository, temaRepository, palavraRepository);
    }

    @Override
    public Rodada getRodada(Jogador jogador) {
        Random random = new Random();
        Tema temaEscolhido = super.getTemaRepository().getTodos()[random.nextInt((int)super.getTemaRepository().getProximoId()-1)];
        int qtdPalavrasSorteadas = random.nextInt(3)+1;
        Palavra[] palavrasTema = super.getPalavraRepository().getPorTema(temaEscolhido);
        if(palavrasTema.length<qtdPalavrasSorteadas) {
            throw new RuntimeException("Não há palavras suficientes para o tema escolhido");
        }
        Palavra[] palavrasEscolhidas = new Palavra[qtdPalavrasSorteadas];
        for (int palavraAtual = 0; palavraAtual < qtdPalavrasSorteadas; palavraAtual++) {
            Palavra palavraSorteada;
            do {
                palavraSorteada = palavrasTema[random.nextInt(palavrasTema.length)];

            }while(Arrays.asList(palavrasEscolhidas).contains(palavraSorteada));
            palavrasEscolhidas[palavraAtual] = palavraSorteada;
        }

        return Rodada.criar(getRodadaRepository().getProximoId(), palavrasEscolhidas, jogador);
    }

}
