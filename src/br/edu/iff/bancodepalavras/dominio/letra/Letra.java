package br.edu.iff.bancodepalavras.dominio.letra;

<<<<<<< HEAD
public abstract class Letra {

=======
/**
 * Classe abstrata para representar letras do jogo
 * Implementa padrão Flyweight conforme diagrama UML
 */
public abstract class Letra {
    private char codigo;
    
    /**
     * Construtor público conforme diagrama UML
     */
    protected Letra(char codigo) {
        this.codigo = Character.toLowerCase(codigo);
    }
    
    public char getCodigo() {
        return codigo;
    }
    
    public void setCodigo(char codigo) {
        this.codigo = Character.toLowerCase(codigo);
    }
    
    /**
     * Método abstrato para exibir a letra
     * Implementa o padrão Strategy
     * @param contexto objeto que pode conter informações específicas para exibição
     */
    public abstract void exibir(Object contexto);
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Letra)) return false;
        Letra outra = (Letra) o;
        return this.codigo == outra.codigo && this.getClass().equals(outra.getClass());
    }
    
    @Override
    public int hashCode() {
        return this.codigo + this.getClass().hashCode();
    }
    
    @Override
    public final String toString() {
        return String.valueOf(codigo);
    }
>>>>>>> refs/remotes/origin/Letra
}
