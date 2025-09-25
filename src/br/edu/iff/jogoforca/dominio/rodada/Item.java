package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

import java.util.ArrayList;
import java.util.List;

public class Item extends ObjetoDominioImpl {

    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;
    private final Palavra palavra;

    // Construtores privados (usar criar/reconstituir)
    private Item(Long id, Palavra palavra) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[palavra.getTamanho()];
    }

    private Item(Long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = posicoesDescobertas.clone(); // cópia defensiva
        this.palavraArriscada = palavraArriscada;
    }

    // Factory Methods
    public static Item criar(Long id, Palavra palavra) {
        return new Item(id, palavra);
    }

    public static Item reconstituir(Long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    // Getters
    public Palavra getPalavra() {
        return palavra;
    }

    public String getPalavraArriscada() {
        return palavraArriscada;
    }

    public boolean arriscou() {
        return palavraArriscada != null;
    }

    // Tentativa de adivinhar uma letra
    public boolean tentar(Letra letra) {
        boolean acertou = false;
        for (int i = 0; i < palavra.getTamanho(); i++) {
            if (palavra.getLetra(i).equals(letra)) {
                posicoesDescobertas[i] = true;
                acertou = true;
            }
        }
        return acertou;
    }

    // Arriscar palavra completa
    public void arriscar(String palavraArriscada) {
        this.palavraArriscada = palavraArriscada;
    }

    // Verificação de acerto
    public boolean acertou() {
        if (palavraArriscada != null) {
            return palavra.comparar(palavraArriscada);
        }
        return descobriu();
    }

    public boolean errou() {
        return palavraArriscada != null && !palavra.comparar(palavraArriscada);
    }

    // Verificação de descoberta total
    public boolean descobriu() {
        for (boolean pos : posicoesDescobertas) {
            if (!pos) {
                return false;
            }
        }
        return true;
    }

    // Letras descobertas e encobertas
    public Letra[] getLetrasDescobertas() {
        List<Letra> letras = new ArrayList<>();
        for (int i = 0; i < palavra.getTamanho(); i++) {
            if (posicoesDescobertas[i]) {
                letras.add(palavra.getLetra(i));
            }
        }
        return letras.toArray(new Letra[0]);
    }

    public Letra[] getLetrasEncobertas() {
        List<Letra> letras = new ArrayList<>();
        for (int i = 0; i < palavra.getTamanho(); i++) {
            if (!posicoesDescobertas[i]) {
                letras.add(palavra.getLetra(i));
            }
        }
        return letras.toArray(new Letra[0]);
    }

    // Pontuação
    public int qtdeLetrasEncobertas() {
        int contador = 0;
        for (boolean posicao : posicoesDescobertas) {
            if (!posicao) {
                contador++;
            }
        }
        return contador;
    }

    public int calcularPontosLetrasEncobertas(int pontosPorLetraEncoberta) {
        return this.qtdeLetrasEncobertas() * pontosPorLetraEncoberta;
    }

    // Exibição
    public void exibir(Object contexto) {
        this.palavra.exibir(contexto, this.posicoesDescobertas);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palavra.getTamanho(); i++) {
            if (posicoesDescobertas[i]) {
                sb.append(palavra.getLetra(i).toString());
            } else {
                sb.append("_");
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
