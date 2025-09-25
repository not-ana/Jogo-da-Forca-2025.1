package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementação da letra para exibição em modo texto
 * Implementa padrão FLYWEIGHT - apenas 26 instâncias (A-Z)
 */
public class LetraTexto extends Letra {
    
    // FLYWEIGHT: Pool de objetos reutilizáveis
    private static final Map<Character, LetraTexto> flyweights = new HashMap<>();
    
    /**
     * Construtor público conforme diagrama UML
     */
    public LetraTexto(char codigo) {
        super(codigo);
    }
    
    /**
     * FLYWEIGHT: Método para obter instância reutilizável
     * Só existem 26 objetos LetraTexto (A-Z)
     */
    public static LetraTexto getFlyweight(char codigo) {
        char codigoNormalizado = Character.toLowerCase(codigo);
        
        LetraTexto letra = flyweights.get(codigoNormalizado);
        if (letra == null) {
            letra = new LetraTexto(codigoNormalizado);
            flyweights.put(codigoNormalizado, letra);
        }
        return letra;
    }
    
    @Override
    public void exibir(Object contexto) {
        // Para texto simples, o contexto pode ser null
        System.out.print(getCodigo());
    }
    
    /**
     * Método para obter o número de flyweights criados
     */
    public static int getNumFlyweights() {
        return flyweights.size();
    }
}
