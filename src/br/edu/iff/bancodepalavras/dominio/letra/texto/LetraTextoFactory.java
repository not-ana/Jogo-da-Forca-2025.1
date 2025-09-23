package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

/**
 * Factory concreta para criação de letras em modo texto
 * Implementa SINGLETON + herda FLYWEIGHT da classe abstrata
 * Estende LetraFactoryImpl com Template Method pattern
 */
public class LetraTextoFactory extends LetraFactoryImpl {
    
    // SINGLETON: única instância
    private static LetraTextoFactory soleInstance;
    
    /**
     * Construtor público conforme diagrama UML
     */
    public LetraTextoFactory() {
        super();
    }
    
    /**
     * SINGLETON: Método para obter a única instância
     */
    public static LetraTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraTextoFactory();
        }
        return soleInstance;
    }
    
    /**
     * TEMPLATE METHOD: Implementação concreta para criar letra texto
     * Chamado pela classe abstrata quando letra não está no pool
     */
    @Override
    protected Letra criarLetra(char codigo) {
        return LetraTexto.getFlyweight(codigo);
    }
}
