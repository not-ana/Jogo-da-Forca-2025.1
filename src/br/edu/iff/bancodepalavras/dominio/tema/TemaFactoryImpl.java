package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {

  private static TemaFactoryImpl soleInstance;


    public static void createSoleInstance(TemaRepository repository) {

        if(soleInstance==null) {
            soleInstance = new TemaFactoryImpl(repository);
        }
    }

    public static TemaFactoryImpl getSoleInstance(){

        if(soleInstance!=null) {
            return soleInstance;
        }
        throw new IllegalStateException("A fábrica de tema não foi definida.");

    }

    private TemaFactoryImpl(TemaRepository repository) {

        super(repository);

    }

    private TemaRepository getTemaRepository(){

        return (TemaRepository) getRepository();
    }

    public Tema getTema(String nome) {

        return Tema.criar(getTemaRepository().getProximoId(), nome);
    }


}
