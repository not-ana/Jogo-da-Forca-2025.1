package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;


public class RodadaAppService {

    private RodadaRepository rodadaRepository;
    private RodadaFactory rodadaFactory;
    private JogadorRepository jogadorRepository;
    private static RodadaAppService soleInstance;


    private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {

        this.rodadaRepository = rodadaRepository;
        this.rodadaFactory = rodadaFactory;
        this.jogadorRepository = jogadorRepository;
    }


    public static void createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository, JogadorRepository jogadorRepository) {

        soleInstance = new RodadaAppService(rodadaFactory, rodadaRepository, jogadorRepository);
    }

    public static RodadaAppService getSoleInstance() {

        if(soleInstance == null) {

            throw new RuntimeException("A instância de RodadaAppService não foi criada.");
        }

        return soleInstance;
    }


    public Rodada novaRodada(Long idJogador) {

        if(jogadorRepository.getPorId(idJogador) == null) {

            throw new RuntimeException("Jogador não existe no repositório.");
        }      

        return rodadaFactory.getRodada(jogadorRepository.getPorId(idJogador));
    }

    public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {

        return rodadaFactory.getRodada(jogadorRepository.getPorNome(nomeJogador));
    }

    public Rodada novaRodada(Jogador jogador) {

        return rodadaFactory.getRodada(jogador);
    }

    public boolean salvarRodada(Rodada rodada) {

        try{

            rodadaRepository.inserir(rodada);
            return true;

        } catch (RepositoryException e) {

            System.out.println("Erro ao salvar rodada: " + e.getMessage());
            return false;
        }     
    }
}
