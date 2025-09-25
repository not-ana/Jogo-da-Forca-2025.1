package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;
import java.util.List;
import java.util.ArrayList;


public class MemoriaPalavraRepository implements PalavraRepository {

    private static MemoriaPalavraRepository soleInstance;
    private List<Palavra> pool;


    private MemoriaPalavraRepository() {

        this.pool = new ArrayList<>();
    }


    public static MemoriaPalavraRepository getSoleInstance() {

        if (soleInstance == null) {

            soleInstance = new MemoriaPalavraRepository();
        }

        return soleInstance;
    }


    @Override
    public long getProximoId() {
        
        if (this.pool.isEmpty()) {

            return 1L;
        }
        
        return this.pool.getLast().getId() + 1L;
    }

    @Override
    public Palavra getPorId(long id) {

        return this.pool.stream()
                        .filter(palavra -> palavra.getId() == id)
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public Palavra[] getPorTema(Tema tema) {
        
        return this.pool.stream()
                        .filter(palavra -> palavra.getTema().equals(tema))
                        .toArray(Palavra[]::new);
    }

    @Override
    public Palavra[] getTodas() {
        
        return this.pool.toArray(new Palavra[this.pool.size()]);
    }

    @Override
    public Palavra getPalavra(String palavra) {
        
        return this.pool.stream()
                        .filter(objetoPalavra -> objetoPalavra.toString().equals(palavra))
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
       
        if (palavra == null) {

            throw new RepositoryException("A palavra não pode ser nula.");
        }

        if (this.pool.contains(palavra)) {

            throw new RepositoryException("A palavra já existe no repositório.");
            
        }

        this.pool.add(palavra);
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        
        if (palavra == null) {
            
            throw new RepositoryException("A palavra não pode ser nula.");
        }

        Palavra palavraExistente = this.getPorId(palavra.getId());

        if (palavraExistente == null) {

            throw new RepositoryException("Não foi possível encontrar a palavra no repositório.");
        }

        this.pool.set(this.pool.indexOf(palavraExistente), palavra);
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {

        if (palavra == null) {

            throw new RepositoryException("A palavra não pode ser nula.");
        }
        
        this.pool.remove(palavra);
    }
}
