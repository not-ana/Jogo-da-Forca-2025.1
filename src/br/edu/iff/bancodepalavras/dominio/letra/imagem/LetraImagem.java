package br.edu.iff.bancodepalavras.dominio.letra.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementação da letra para exibição com imagem
 * Implementa padrão FLYWEIGHT - apenas 26 instâncias (A-Z)
 */
public class LetraImagem extends Letra {
    
    // FLYWEIGHT: Pool de objetos reutilizáveis
    private static final Map<Character, LetraImagem> flyweights = new HashMap<>();
    
    /**
     * Construtor público conforme diagrama UML
     */
    public LetraImagem(char codigo) {
        super(codigo);
    }
    
    /**
     * FLYWEIGHT: Método para obter instância reutilizável
     * Só existem 26 objetos LetraImagem (A-Z)
     */
    public static LetraImagem getFlyweight(char codigo) {
        char codigoNormalizado = Character.toLowerCase(codigo);
        
        LetraImagem letra = flyweights.get(codigoNormalizado);
        if (letra == null) {
            letra = new LetraImagem(codigoNormalizado);
            flyweights.put(codigoNormalizado, letra);
        }
        return letra;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void exibir(Object contexto) {
        // Para imagem, o contexto pode ser uma lista onde adicionar URLs de imagens
        if (contexto instanceof List) {
            List<String> urls = (List<String>) contexto;
            String urlImagem = "/images/letras/" + getCodigo() + ".png";
            urls.add(urlImagem);
        }
        // Simula exibição da imagem
        System.out.print("[IMG:" + getCodigo() + "]");
    }
    
    /**
     * Método para obter o número de flyweights criados
     */
    public static int getNumFlyweights() {
        return flyweights.size();
    }
}
