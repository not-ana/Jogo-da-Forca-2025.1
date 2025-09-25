package br.edu.iff.bancodepalavras.dominio.letra.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

/**
 * Factory concreta para criação de letras em modo imagem
 * Implementa SINGLETON + herda FLYWEIGHT da classe abstrata
 * Estende LetraFactoryImpl com Template Method pattern
 */
public class LetraImagemFactory extends LetraFactoryImpl {
    
    // SINGLETON: única instância
    private static LetraImagemFactory soleInstance;
    
    /**
     * Construtor público conforme diagrama UML
     */
    public LetraImagemFactory() {
        super();
    }
    
    /**
     * SINGLETON: Método para obter a única instância
     */
    public static LetraImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraImagemFactory();
        }
        return soleInstance;
    }
    
    /**
     * TEMPLATE METHOD: Implementação concreta para criar letra imagem
     * Chamado pela classe abstrata quando letra não está no pool
     */
    @Override
    protected Letra criarLetra(char codigo) {
        return LetraImagem.getFlyweight(codigo);
    }
}
