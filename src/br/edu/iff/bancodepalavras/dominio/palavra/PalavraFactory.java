package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;

public interface PalavraFactory {
    public Palavra criarPalavra(String palavra, Tema tema);
}
