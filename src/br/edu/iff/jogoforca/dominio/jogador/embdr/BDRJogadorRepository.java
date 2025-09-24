package br.edu.iff.jogoforca.dominio.jogador.embdr;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRJogadorRepository implements JogadorRepository {

    private static BDRJogadorRepository soleInstance;

    private BDRJogadorRepository() {}

    public static BDRJogadorRepository getSoleInstance() {

        if (soleInstance == null) {

            soleInstance = new BDRJogadorRepository();
        }

        return soleInstance;
    }

    @Override
    public Jogador getPorId(long id) {

        throw new UnsupportedOperationException("Unimplemented method 'getPorId'");
    }

    @Override
    public Jogador getPorNome(String nome) {

        throw new UnsupportedOperationException("Unimplemented method 'getPorNome'");
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {

        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {

    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {

    }

    @Override
    public long getProximoId() {
        return 0;
    }
}
