package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoriaTemaRepository  implements TemaRepository{

  private static MemoriaTemaRepository soleInstance;
    Map<Long, Tema> pool;
    private long idCounter;

    private MemoriaTemaRepository() {

        pool = new HashMap<>();
        idCounter = 0;
    }

    public static MemoriaTemaRepository getSoleInstance() {

        if (soleInstance == null) {

            soleInstance = new MemoriaTemaRepository();
        }

        return soleInstance;
    }


    @Override
    public Tema getPorId(long id) {

        return pool.get(id);
    }

    @Override
    public Tema[] getPorNome(String nome) {

        List<Tema> nomesDeTema = new ArrayList<>();

        for (Tema tema : pool.values()) {

            if (tema.getNome().equals(nome)) {

                nomesDeTema.add(tema);
            }
        }

        return nomesDeTema.toArray(new Tema[0]);
    }

    @Override
    public Tema[] getTodos() {
        //transformar o pool em array, o proprio metodo ajusta o tamanho
        return pool.values().toArray(new Tema[0]);
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {// erro aqui, diz que metodos overridem não lançam exceção

        pool.put(tema.getId(), tema);

        if (!pool.containsKey(tema.getId())) {

            throw new RepositoryException("Erro ao inserir o tema. Tema não foi adicionado.");

        }

    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {

        if ((pool.replace(tema.getId(), tema)) == null){

            throw new RepositoryException("Erro ao atualizar o tema. Chave não foi encontrada.");

        }

    }

    @Override
    public void remover(Tema tema) throws RepositoryException {

        if(pool.remove(tema.getId()) == null){

            throw new RepositoryException("Erro ao remover o tema. Chave não foi encontrada.");

        }
    }

    @Override
    public long getProximoId() {

        return this.idCounter ++;
    }


}
