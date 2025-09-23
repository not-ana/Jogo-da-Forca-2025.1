package br.edu.iff.jogoforca.dominio.boneco;

/**
 * Factory interface para criação de bonecos
 * Implementa o padrão Abstract Factory
 */
public interface BonecoFactory {
    
    /**
     * Cria um boneco
     */
    public Boneco getBoneco();
}
