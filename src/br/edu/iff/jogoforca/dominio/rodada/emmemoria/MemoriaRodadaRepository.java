package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemoriaRodadaRepository implements RodadaRepository {

    private static MemoriaRodadaRepository soleInstance;
    private Map<Long, Rodada> pool;
    private long idCounter;

    public static MemoriaRodadaRepository getSoleInstance(){

        if(soleInstance == null){

            soleInstance = new MemoriaRodadaRepository();
        }

        return soleInstance;
    }

    private MemoriaRodadaRepository(){

        pool = new HashMap<>();
        idCounter = 0;
    }

    @Override
    public Rodada getPorId(long id) {

        return pool.get(id);
    } //retorna null se não encontrar

    @Override
    public Rodada[] getPorJogador(Jogador jogador) {

        List<Rodada> rodadasDoJogador = new ArrayList<>();

        for (Rodada rodada : pool.values()) {

            if (rodada.getJogador().equals(jogador)) {

                rodadasDoJogador.add(rodada);
            }
        }
        return rodadasDoJogador.toArray(new Rodada[0]);
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {

        pool.put(rodada.getId(), rodada);

        if (!pool.containsKey(rodada.getId())) {

            throw new RepositoryException("Erro ao inserir a rodada. Rodada não foi adicionada.");

        }
    }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {

        if ((pool.replace(rodada.getId(), rodada)) == null){

            throw new RepositoryException("Erro ao atualizar a rodada. Chave não foi encontrada.");

        }
    } //replace retorna null caso a chave não exista

    @Override
    public void remover(Rodada rodada) throws RepositoryException {

        if(pool.remove(rodada.getId()) == null){

            throw new RepositoryException("Erro ao remover a rodada. Chave não foi encontrada.");

        }
    }

    @Override
    public long getProximoId() {

        return this.idCounter ++;
    }
}
