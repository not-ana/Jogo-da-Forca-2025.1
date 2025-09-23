package br.edu.iff.jogoforca.dominio.boneco;

/**
 * Interface para representar o boneco da forca
 * Utiliza padrão Strategy para diferentes tipos de exibição
 */
public interface Boneco {
    
    /**
     * Exibe o boneco com contexto específico
     * Implementa o padrão Strategy
     * @param contexto contexto para exibição
     * @param partes número de partes do boneco a serem exibidas
     */
    void exibir(Object contexto, int partes);
}
