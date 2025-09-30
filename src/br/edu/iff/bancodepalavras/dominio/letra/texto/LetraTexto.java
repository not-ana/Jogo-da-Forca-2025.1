package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import java.util.HashMap;
import java.util.Map;


public class LetraTexto extends Letra {
    
    private static final Map<Character, LetraTexto> flyweights = new HashMap<>();
    
    public LetraTexto(char codigo) {
        super(codigo);
    }
    
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
    
    public static int getNumFlyweights() {
        return flyweights.size();
    }
}
