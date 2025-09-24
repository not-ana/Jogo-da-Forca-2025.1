package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;


public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {

    private static JogadorFactoryImpl soleInstance;


    public static void createSoleInstance(JogadorRepository repository) {

        if (soleInstance == null) {

            soleInstance = new JogadorFactoryImpl(repository);

        }
    }

    public static JogadorFactoryImpl getSoleInstance(){

        if(soleInstance != null) {

            return soleInstance;
        } else {

            throw new IllegalStateException("A fábrica de jogador não foi definida.");
        }

    }

    private JogadorFactoryImpl(JogadorRepository jogadorRepository) {
        super(jogadorRepository);

    }

    private JogadorRepository getJogadorRepository() {

        return (JogadorRepository) getRepository();
    }

    public Jogador getJogador(String nome) {

        return Jogador.criar(getJogadorRepository().getProximoId(), nome);
    }


}
