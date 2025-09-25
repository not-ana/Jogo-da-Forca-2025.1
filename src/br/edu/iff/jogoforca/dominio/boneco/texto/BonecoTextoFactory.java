package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

/**
 * Factory concreta para criação de bonecos em modo texto
 * Implementa SINGLETON para otimização de memória
 */
public class BonecoTextoFactory implements BonecoFactory {
    
    // SINGLETON: única instância
    private static BonecoTextoFactory soleInstance;
    
    // Construtor privado para SINGLETON
    private BonecoTextoFactory() {
        // Construtor vazio para SINGLETON
    }
    
    /**
     * SINGLETON: Método para obter a única instância
     */
    public static BonecoTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTextoFactory();
        }
        return soleInstance;
    }
    
    @Override
    public Boneco getBoneco() {
        return BonecoTexto.getSoleInstance();
    }
}
