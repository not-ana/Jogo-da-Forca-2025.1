package br.edu.iff.bancodepalavras.dominio.letra;


public abstract class LetraFactoryImpl implements LetraFactory {
    
    private Letra[] pool;
    private Letra encoberta;
    

    protected LetraFactoryImpl() {
        this.pool = new Letra[26];
        this.encoberta = null;
    }
    
    @Override
    public final Letra getLetra(char codigo) {
        // Normaliza para minúscula
        char codigoNormalizado = Character.toLowerCase(codigo);
        
        if (codigoNormalizado < 'a' || codigoNormalizado > 'z') {
            throw new IllegalArgumentException("Código inválido: " + codigo + ". Deve estar entre 'a' e 'z'.");
        }
        
        int i = codigoNormalizado - 'a';
        
        Letra result = this.pool[i];
        if (result == null) {
            result = this.criarLetra(codigoNormalizado);
            this.pool[i] = result;
        }
        
        return result;
    }
    
    @Override
    public final Letra getLetraEncoberta() {
        if (this.encoberta == null) {
            this.encoberta = this.criarLetra('_');
        }
        return this.encoberta;
    }
    
    protected abstract Letra criarLetra(char codigo);
    

}
