package br.edu.iff.bancodepalavras.dominio.palavra.embdr;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;


public class BDRPalavraRepository implements PalavraRepository{

    private static BDRPalavraRepository soleInstance;


    private BDRPalavraRepository() {}


    public static BDRPalavraRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BDRPalavraRepository();
        }
        return soleInstance;
    }


    @Override
    public long getProximoId() {
        throw new UnsupportedOperationException("Unimplemented method 'getProximoId'");
    }

    @Override
    public Palavra getPorId(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getPorId'");
    }

    @Override
    public Palavra[] getPorTema(Tema tema) {
        throw new UnsupportedOperationException("Unimplemented method 'getPorTema'");
    }

    @Override
    public Palavra[] getTodas() {
        throw new UnsupportedOperationException("Unimplemented method 'getTodas'");
    }

    @Override
    public Palavra getPalavra(String palavra) {
        throw new UnsupportedOperationException("Unimplemented method 'getPalavra'");
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        throw new UnsupportedOperationException("Unimplemented method 'inserir'");
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

}