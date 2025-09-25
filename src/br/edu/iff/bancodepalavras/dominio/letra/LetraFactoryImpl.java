package br.edu.iff.bancodepalavras.dominio.letra;

/**
 * Implementação abstrata da factory de letras
 * Implementa FLYWEIGHT com TEMPLATE METHOD conforme diagrama UML
 * Mantém pool de 26 letras (a-z) para reutilização
 */
public abstract class LetraFactoryImpl implements LetraFactory {
    
    // FLYWEIGHT: Pool de 26 letras (a-z)
    private Letra[] pool;
    
    // Letra especial para posições encobertas
    private Letra encoberta;
    
    /**
     * Construtor protegido - inicializa o pool
     */
    protected LetraFactoryImpl() {
        this.pool = new Letra[26];
        this.encoberta = null;
    }
    
    /**
     * TEMPLATE METHOD: Obtém letra do pool ou cria nova
     * Método final - algoritmo fixo, mas criação delegada para subclasses
     */
    @Override
    public final Letra getLetra(char codigo) {
        // Normaliza para minúscula
        char codigoNormalizado = Character.toLowerCase(codigo);
        
        // Valida range a-z
        if (codigoNormalizado < 'a' || codigoNormalizado > 'z') {
            throw new IllegalArgumentException("Código inválido: " + codigo + ". Deve estar entre 'a' e 'z'.");
        }
        
        // Calcula índice no array (a=0, b=1, ..., z=25)
        int i = codigoNormalizado - 'a';
        
        // FLYWEIGHT: Verifica se já existe no pool
        Letra result = this.pool[i];
        if (result == null) {
            // Se não existe, cria nova letra (TEMPLATE METHOD)
            result = this.criarLetra(codigoNormalizado);
            this.pool[i] = result;
        }
        
        return result;
    }
    
    /**
     * Obtém letra especial para posições encobertas
     * Implementação direta conforme diagrama UML
     */
    @Override
    public final Letra getLetraEncoberta() {
        if (this.encoberta == null) {
            // Cria letra encoberta usando o método criarLetra
            this.encoberta = this.criarLetra('_');
        }
        return this.encoberta;
    }
    
    /**
     * TEMPLATE METHOD: Método abstrato para criar letra específica
     * Subclasses implementam a criação concreta (Texto/Imagem)
     */
    protected abstract Letra criarLetra(char codigo);
    
    /**
     * Método auxiliar para obter estatísticas do pool
     * Útil para debug e testes
     */
    public int getLetrasNaPool() {
        int count = 0;
        for (Letra letra : pool) {
            if (letra != null) count++;
        }
        return count;
    }
}
