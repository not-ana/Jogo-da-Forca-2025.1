package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

/**
 * Factory concreta para criação de bonecos em modo imagem
 * Implementa SINGLETON para otimização de memória
 */
public class BonecoImagemFactory implements BonecoFactory {
    
    // SINGLETON: única instância
    private static BonecoImagemFactory soleInstance;
    
    // Construtor privado para SINGLETON
    private BonecoImagemFactory() {
        // Construtor vazio para SINGLETON
    }
    
    /**
     * SINGLETON: Método para obter a única instância
     */
    public static BonecoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoImagemFactory();
        }
        return soleInstance;
    }
    
    @Override
    public Boneco getBoneco() {
        return BonecoImagem.getSoleInstance();
    }
}
