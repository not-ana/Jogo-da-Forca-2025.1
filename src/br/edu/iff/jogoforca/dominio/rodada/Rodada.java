package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rodada extends ObjetoDominioImpl {
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta  = 15;

    private Jogador jogador;
    private static BonecoFactory bonecoFactory;
    private Boneco boneco;
    private Item[] itens;
    private List<Letra> erradas;


    public static int getMaxPalavras(){
        return maxPalavras;
    }

    public static void setMaxPalavras(int maxPalavras){
        Rodada.maxPalavras = maxPalavras;
    }

    public static int getMaxErros(){
        return maxErros;
    }

    public static void setMaxErros(int maxErros){
        Rodada.maxErros = maxErros;
    }

    public static int getPontosQuandoDescobreTodasAsPalavras(){
        return pontosQuandoDescobreTodasAsPalavras;
    }

    public static void setPontosQuandoDescobreTodasAsPalavras(int pontos){
        Rodada.pontosQuandoDescobreTodasAsPalavras = pontos;
    }

    public static int getPontosPorLetraEncoberta(){
        return pontosPorLetraEncoberta;
    }

    public static void setPontosPorLetraEncoberta(int pontosPorLetraEncoberta){
        Rodada.pontosPorLetraEncoberta = pontosPorLetraEncoberta;
    }

    public static void setBonecoFactory(BonecoFactory bonecoFactory){
        Rodada.bonecoFactory = bonecoFactory;
    }

    public static BonecoFactory getBonecoFactory(){
        return bonecoFactory;
    }

    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador){
        if(bonecoFactory==null){
            throw new RuntimeException("Precisa inicializar o BonecoFactory antes da rodada");
        }
        return new Rodada(id, palavras, jogador);
    }

    public static Rodada reconstruir(long id, Item[] itens, Letra[] erradas, Jogador jogador){
        if(bonecoFactory==null){
            throw new RuntimeException("Precisa inicializar o BonecoFactory antes da rodada");
        }
        return new Rodada(id, itens, erradas, jogador);
    }

    private Rodada(long id, Palavra[] palavras, Jogador jogador){
        //isso tudo é sobre criar rodada
        super(id);
        this.itens = new Item[palavras.length];
        //cria um item para cada palavra
        //cada item é uma palavra
        for(int i = 0; i < palavras.length; i++){
            this.itens[i] = Item.criar(i, palavras[i]);
        }
        //pego um tema de uma palavra qualquer, pois todas as palavras da rodada são do mesmo tema
        Tema temaTeste = this.itens[0].getPalavra().getTema();
        for(Item item : this.itens){
            if(item.getPalavra().getTema() != temaTeste){
                //rapaz, se isso aqui acontecer... é pq ta pegando temas diferentes. mas todas as palavras TÊM que ser do mesmo tema
                throw new RuntimeException("Todas as palavras da rodada devem ser do mesmo tema");
            }
        }
        this.jogador = jogador; //seta o jogador
        this.erradas = new ArrayList<Letra>(); //inicializa a lista de letras erradas
        this.boneco = bonecoFactory.getBoneco(); //pega um boneco da fábrica
    }

    private Rodada(long id, Item[] itens, Letra[] erradas, Jogador jogador){
        //isso tudo é sobre reconstruir rodada
        super(id);
        this.itens = itens;
        Tema temaTeste = this.itens[0].getPalavra().getTema();
        for(Item item : this.itens){
            if(item.getPalavra().getTema() != temaTeste){
                throw new RuntimeException("Todas as palavras da rodada devem ser do mesmo tema");
            }
        }
        this.erradas = Arrays.asList(erradas); //transforma o array de erradas em lista
        this.jogador = jogador;
        this.boneco = bonecoFactory.getBoneco();
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public int getNumPalavras() {
        return this.itens.length;
    }

    public Tema getTema() {
        if (this.getNumPalavras() == 0) {
            throw new RuntimeException("Deve ter pelo menos um item(palavra) na rodada");
        }
        return this.itens[0].getPalavra().getTema();
        //pra acessar tema, da rodada, precisa acessar o item, que acessa a palavra, que acessa o tema
    }

    public Palavra[] getPalavras() {
        Palavra[] palavras = new Palavra[this.getNumPalavras()];
        for (int i = 0; i < this.getNumPalavras(); i++) {
            palavras[i] = this.itens[i].getPalavra();
        }
        return palavras;
        //pra acessar palavras, da rodada, precisa acessar o item, que acessa a palavra
    }


    public void tentar(char codigo) {
        //só pode tentar se ainda tiver jogo. ou seja, se encerrou != true
        //o tentar de rodada chama o tentar de cada item
        //se tentar nao tiver sucesso, adiciona a letra errada
        //a palavra armazena o vetor de letras erradas

        if(this.encerrou()){
            throw new RuntimeException("Não pode tentar mais, a rodada já encerrou");
        }
        if(this.getNumPalavras()==0){
            throw new RuntimeException("Deve ter pelo menos um item(palavra) na rodada");
        }
        boolean descobriu = false;
        for(Item item : this.itens){
            if(item.tentar(codigo)&&!descobriu){  //se tentar deu certo e ainda não descobriu
                descobriu = true; //descobriu é true porque acertou
            }
        }

        if(!descobriu){
            this.erradas.add(Palavra.getLetraFactory().getLetra(codigo));
        }
        if(this.encerrou()){
            this.jogador.atualizarPontuacao(this.jogador.getPontuacao() + this.calcularPontos());
        }
    }

    public void arriscar(String[] palavras) {
        if(this.encerrou()){
            throw new RuntimeException("Não pode arriscar mais, a rodada já encerrou");
        }
        //arriscar da rodada chama o arriscar de cada item
        for(int palavraAtual = 0; palavraAtual < this.getNumPalavras(); palavraAtual++) {
            this.itens[palavraAtual].arriscar(palavras[palavraAtual]);
        }
    }

    public void exibirItens(Object contexto) {
        for(Item item : this.itens){
            item.exibir(contexto);
            System.out.println();
        }
    }

    public void exibirBoneco(Object contexto) {
        this.boneco.exibir(contexto, this.erradas.size());
    }

    public void exibirPalavras(Object contexto) {
        for(Item item : this.itens){
            item.getPalavra().exibir(contexto);
            System.out.println();
        }
    }

    public void exibirLetrasErradas(Object contexto) {
        for(Letra letra : this.erradas){
            letra.exibir(contexto);
            System.out.println(" ");
        }
    }

    public Letra[] getTentativas() {
        Letra[] tentativas = new Letra[this.getCertas().length+this.getErradas().length]; //soma do tamanho dos arrays de certas e erradas
        int letraAtual = 0;
        for(;letraAtual<this.getCertas().length; letraAtual++){
            tentativas[letraAtual] = this.getCertas()[letraAtual];
        }
        for(;letraAtual<tentativas.length; letraAtual++) {
            tentativas[letraAtual] = this.getErradas()[letraAtual - this.getCertas().length];
        }
        return tentativas;
    }

    public Letra[] getCertas() {
        ArrayList<Letra> acertos = new ArrayList<Letra>();
        for(Item item : this.itens){
            for(Letra letra : item.getLetrasDescobertas()){
                if(!acertos.contains(letra)){
                    acertos.add(letra);
                }
            }
        }
        return acertos.toArray(new Letra[acertos.size()]);
    }

    public Letra[] getErradas() {
        return this.erradas.toArray(new Letra[this.erradas.size()]);
    }

    public int calcularPontos() {
        if(this.descobriu()){
            int pontosTotaisPorLetrasEncobertas = 0;
            for (Item item : this.itens) {
                pontosTotaisPorLetrasEncobertas += item.calcularPontosLetrasEncobertas(pontosPorLetraEncoberta);
            }
            return pontosQuandoDescobreTodasAsPalavras + pontosTotaisPorLetrasEncobertas;
        }
        return 0;
    }

    public boolean encerrou() {

        return this.arriscou() || this.descobriu() || this.getQtdeTentativasRestantes() == 0;
    }

    public boolean descobriu() {
        //descobriu da rodada chama o descobriu de cada item
        for(Item item : this.itens){
            if(!item.descobriu()){
                return false;
            }
        }
        return true;
    }

    public boolean arriscou() {
        //arriscou da rodada chama o arriscou de cada item. a.k.a arriscou (uma palavra) de cada 'item'
        for(Item item : this.itens){
            if(!item.arriscou()){
                return false;
            }
        }
        return true;
    }

    public int getQtdeTentativasRestantes() {
        return maxErros - this.getQtdeErros();
    }

    public int getQtdeErros() {
        return this.getErradas().length;
    }

    public int getQtdeAcertos() {
        return this.getCertas().length;
    }

    public int getQtdeTentativas() {
        return this.getTentativas().length;
    }
}
