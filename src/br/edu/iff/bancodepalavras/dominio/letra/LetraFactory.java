package br.edu.iff.bancodepalavras.dominio.letra;

<<<<<<< HEAD
public interface LetraFactory {

=======
/**
 * Factory interface para criação de letras
 * Implementa o padrão Abstract Factory conforme diagrama UML
 */
public interface LetraFactory {
    
    /**
     * Obtém uma letra com o código especificado
     * Usa FLYWEIGHT para reutilizar instâncias
     */
    public Letra getLetra(char codigo);
    
    /**
     * Obtém uma letra especial para posições encobertas
     * Retorna sempre a mesma instância (ex: '_')
     */
    public Letra getLetraEncoberta();
>>>>>>> refs/remotes/origin/Letra
}
