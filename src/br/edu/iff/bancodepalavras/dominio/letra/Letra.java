package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class Letra {
    private char codigo;

    protected Letra(char codigo) {
        this.setCodigo(codigo);
    }

    private void setCodigo(char codigo) {

        /**
         * Verificação para byte nulo e espaço em branco da tabela ASCII
         */
        if (codigo == 0 || codigo == 32) {
            throw new IllegalArgumentException("O caractere não pode ser nulo nem vazio.");
        }

        this.codigo = codigo;
    }

    public char getCodigo() {
        return this.codigo;
    }

    public abstract void exibir(Object contexto);

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Letra))
            return false;

        Letra outra = (Letra) o;
        return this.codigo == outra.codigo && this.getClass().equals(outra.getClass());

    }

    @Override
    public int hashCode() {
        return this.getCodigo() + this.getClass().hashCode();
    }

    @Override
    public final String toString() {
        return String.valueOf(this.getCodigo());
    }

}