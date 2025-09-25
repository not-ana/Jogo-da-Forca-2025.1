package br.edu.iff.bancodepalavras.dominio.palavra;

import java.util.Arrays;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Palavra extends ObjetoDominioImpl {
   
    private Tema tema;
    private static LetraFactory letraFactory;
    private Letra encoberta;
    private Letra[] letras;
 
 
    public static void setLetraFactory(LetraFactory factory) {
       
        letraFactory = factory;
    }
  
    public static LetraFactory getLetraFactory() {
       
        return letraFactory;
    }


    public static Palavra reconstituir(long id, String palavra, Tema tema) {

        if(getLetraFactory() == null) {
           
            throw new IllegalStateException("A fábrica de letras não foi definida.");
        }

        return new Palavra(id, palavra, tema);
    }

    public static Palavra criar(long id, String palavra, Tema tema) {

        if(getLetraFactory() == null) {
           
            throw new IllegalStateException("A fábrica de letras não foi definida.");
        }

        return new Palavra(id, palavra, tema);
    }
   
    private Palavra(long id, String palavra, Tema tema) {
       
        super(id);
        this.tema = tema;
        this.encoberta = getLetraFactory().getLetraEncoberta();
        this.letras = new Letra[palavra.length()];

        for (int i = 0; i < palavra.length(); i++) {

            this.letras[i] = getLetraFactory().getLetra(palavra.charAt(i));
        }

    }


    public Letra getLetra(int posicao) {

        if (0 <= posicao && posicao < this.letras.length) {

            return this.letras[posicao];
        }
        else{

            throw new IllegalArgumentException("Posição inválida.");
        }
    }

    public Letra[] getLetras() {

        return this.letras.clone();
    }

    public void exibir(Object contexto, boolean[] posicoes) {

        for (int i = 0; i < this.getTamanho(); i++) {

            if (posicoes[i]) {

                this.getLetra(i).exibir(contexto);

            } else {

                this.encoberta.exibir(contexto);
            }
        }
    }

    public void exibir(Object contexto) {

        boolean[] posicoes = new boolean[this.getTamanho()];
        Arrays.fill(posicoes, true);

        exibir(contexto, posicoes);
    }

    public Tema getTema() {

        return this.tema;
    }
  
    public boolean comparar(String palavra) {

        if(palavra == null) {

                return false;
        }

        if(palavra.length() == getTamanho()) {

            for (int i = 0; i < getTamanho(); i++) {

                if (getLetra(i).getCodigo() != palavra.charAt(i)) {

                    return false;
                }
            }

            return true;
        }
    
        return false;
    }

    public int[] tentar(char codigo) {
       
        int[] posicoes = new int[this.getTamanho()];
        int count = 0;
    
        for (int i = 0; i < this.getTamanho(); i++) {

            if (this.getLetra(i).getCodigo() == codigo) {

                posicoes[count] = i;
                count++;
            }
        }
    
        if (count > 0) {

            int[] resultado = new int[count];

            System.arraycopy(posicoes, 0, resultado, 0, count);

            return resultado;
        }
    
        return new int[0];
    }
    
 
    public int getTamanho() {

        return letras.length;
    }

    @Override
    public String toString() {

        StringBuilder palavra = new StringBuilder();

        for (int i = 0; i < this.getTamanho(); i++) {

            palavra.append(this.getLetra(i).getCodigo());
        }

        return palavra.toString();
    }
}
