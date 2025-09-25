package br.edu.iff.jogoforca.dominio.rodada;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

import java.util.ArrayList;

public class Item extends ObjetoDominioImpl {

    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;
    private Palavra palavra;

    static Item criar(long id, Palavra palavra) {
        return new Item(id, palavra);
    }

    public static Item reconstruir(long id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    private Item(long id, Palavra palavra) {
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[this.palavra.getTamanho()];
    }

    private Item(long id, Palavra palavra, int [] posicoesDescobertas, String palavraArriscada){
        super(id);
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[this.palavra.getTamanho()];
        //varre o array de posicoesDescobertas e seta como true as posições que foram descobertas
        for(Integer posicao : posicoesDescobertas){
            this.posicoesDescobertas[posicao] = true;
        }
        this.palavraArriscada = palavraArriscada;
    }

    //duvida = nao deveria fazer uma copia defensiva de palavra?
    public Palavra getPalavra() {
        return this.palavra;
    }

    public Letra[] getLetrasDescobertas() {
        ArrayList<Letra> letrasDescobertasLista = new ArrayList<Letra>();
        for(int posicaoAtual = 0; posicaoAtual < this.palavra.getTamanho(); posicaoAtual++){
            if(this.posicoesDescobertas[posicaoAtual]){
                letrasDescobertasLista.add(this.palavra.getLetra(posicaoAtual));
            }
        }
        return letrasDescobertasLista.toArray(new Letra[letrasDescobertasLista.size()]);
    }

    public Letra[] getLetrasEncobertas() {
        ArrayList<Letra> letrasDescobertasLista = new ArrayList<Letra>();
        for(int posicaoAtual = 0; posicaoAtual < this.palavra.getTamanho(); posicaoAtual++){
            if(!this.posicoesDescobertas[posicaoAtual]){
                letrasDescobertasLista.add(this.palavra.getLetra(posicaoAtual));
            }
        }
        return letrasDescobertasLista.toArray(new Letra[letrasDescobertasLista.size()]);
    }

    public int qtdeLetrasEncobertas() {
        int contador = 0;

        for (boolean posicao : posicoesDescobertas) {
            if (!posicao) {
                contador++;
            }
        }
        return contador;
    }

    public int calcularPontosLetrasEncobertas(int pontosPorLetraEncoberta){
        return this.qtdeLetrasEncobertas() * pontosPorLetraEncoberta;
    }

    public boolean descobriu(){
        return this.acertou() || this.qtdeLetrasEncobertas()==0;
    }

    public void exibir(Object contexto){
        this.palavra.exibir(contexto, this.posicoesDescobertas);
    }

    boolean tentar(char codigo) {
        int[] posicoes = this.palavra.tentar(codigo);
        for (int posicao : posicoes) {
            this.posicoesDescobertas[posicao] = true;
        }
        return posicoes.length > 0;
    }

    void arriscar(String palavra) {
        this.palavraArriscada = palavra;
    }

    public String getPalavraArriscada() {
        return palavraArriscada;
    }

    public boolean arriscou() {
        return palavraArriscada != null;
    }

    public boolean acertou() {
        //return palavraArriscada.equals(palavra.toString());
        return palavra.comparar(this.palavraArriscada);
    }
}