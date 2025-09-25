package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoriaJogadorRepository implements JogadorRepository {

    private Map<Long, Jogador> pool;
    private static MemoriaJogadorRepository soleInstance;
    private long idCounter;

    public static MemoriaJogadorRepository getSoleInstance(){

        if(soleInstance == null){

            soleInstance = new MemoriaJogadorRepository();
        }

        return soleInstance;
    }

    private MemoriaJogadorRepository() {

        pool = new HashMap<>();
        idCounter = 0;
    }

    @Override
    public Jogador getPorId(long id) {

        return pool.get(id);
    }

    @Override
    public Jogador getPorNome(String nome) {

        for (Jogador jogador : pool.values()){

            if(jogador.getNome().equals(nome)){

                return jogador;
            }
        }
        return null;
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {

        pool.put(jogador.getId(), jogador);

        if (!pool.containsKey(jogador.getId())) {

            throw new RepositoryException("Erro ao inserir o jogador. Jogador não foi adicionado.");

        }
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {

        if ((pool.replace(jogador.getId(), jogador)) == null){

            throw new RepositoryException("Erro ao atualizar a jogador. Chave não foi encontrada.");

        }
    } //replace retorna null caso a chave não exista


    @Override
    public void remover(Jogador jogador) throws RepositoryException {

        if(pool.remove(jogador.getId()) == null){

            throw new RepositoryException("Erro ao remover a jogador. Chave não foi encontrada.");

        }
    }

    @Override
    public long getProximoId() {

        return this.idCounter ++;
    }
}
