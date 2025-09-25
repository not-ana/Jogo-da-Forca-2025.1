package br.edu.iff.jogoforca.dominio.rodada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl{

	private static int maxPalavras = 3;
	private static int maxErros = 10;
	private static int pontosQuandoDescobreTodasAsPalavras = 100;
	private static int pontosPorLetraEncoberta = 15;
	
	private Jogador jogador;
	private List<Letra> erradas;
	private static Boneco bonecoFactory;
	private Boneco boneco;
	private Item[] itens;
	
	public static int getMaxPalavras() {
		return maxPalavras;
	}
	
	public static void setMaxPalavras(int max) {
		Rodada.maxPalavras = max;
	}
	
	public static int getMaxErros() {
		return maxErros;
	}
	
	public static void setMaxErros(int max) {
		Rodada.maxErros = max;
	}
	
	public static int getPontosQuandoDescobreTodasAsPalavras() {
		return pontosQuandoDescobreTodasAsPalavras;
	}
	
	public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
		Rodada.pontosQuandoDescobreTodasAsPalavras = pontos;
	}
	
	public static int getPontosPorLetraEncoberta() {
		return pontosPorLetraEncoberta;
	}
	
	public static void setPontosPorLetraEncoberta(int pontos) {
		Rodada.pontosPorLetraEncoberta = pontos;
	}
	
	public static void setBonecoFactory(BonecoFactory bonecoFactory) {
		Rodada.bonecoFactory = bonecoFactory;
	}
	
	public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
		if(bonecoFactory==null) {
			throw new RuntimeException("É preciso inicializar Boneco antes da Rodada.");
		}
		return new Rodada(id, palavras, jogador);
	}
	
	public static Rodada reconstituir(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		if(bonecoFactory==null) {
			throw new RuntimeException("É preciso inicializar Boneco antes da Rodada.");
		}
		return new Rodada(id, itens, erradas, jogador);
	}
		
	private Rodada(long id, Palavra[] palavras, Jogador jogador) {
		//construtor sobre criar rodada
		super(id);
		
		//preciso acessar item, que acessa palavra
		this.itens = new Item[palavras.length];	
		
		//se tiver algum item/palavra, rodada chama o criar de cada item
		for (int i=0; i<palavras.length; i++) {
			this.itens[i] = Item.criar(i, palavras[i]);
		}
		
		//todas as palavras pertencem a um mesmo tema.
		Tema tema = this.itens[0].getPalavra().getTema();
		
		for (Item item : this.itens) {
			//validacao do tema da palavras vs tema selecionado
			if(item.getPalavra().getTema()!=tema) {
				throw new RuntimeException("Todas as palavras da Rodada devem ser do mesmo tema.");
			}
		}
		
		//o restante do construtor
		this.jogador = jogador;
		this.erradas = new ArrayList<Letra>(); //inicializa a lista de letras erradas
		this.boneco = bonecoFactory.getBoneco(); //pega um boneco da fábrica		
	}
	
	private Rodada(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		//construtor sobre reconstituir rodada da camada de persistencia
		super(id);
		
		this.itens = itens;
		Tema tema = this.itens[0].getPalavra().getTema();
		for (Item item : this.itens) {
			//validacao do tema da palavras vs tema selecionado
			if(item.getPalavra().getTema()!=tema) {
				throw new RuntimeException("Todas as palavras da Rodada devem ser do mesmo tema.");
			}
		}
		
	
		this.jogador = jogador;
		this.erradas = Arrays.asList(erradas); ///transforma o array de erradas em lista
		this.bonecoFactory = bonecoFactory.getBoneco(); 
		
	}
	
	public Jogador getJogador() {
		return this.getJogador();
	}
	
	public Tema getTema() {
		if(this.getNumPalavras()==0) {
			throw new RuntimeException("Deve ter pelo menos um item(palavra) na rodada");
		}
        //pra acessar tema, da rodada, precisa acessar o item, que acessa a palavra, que acessa o tema
		return this.itens[0].getPalavra().getTema();
	}
	
	public Palavra[] getPalavras() {
		Palavra[] palavras = new Palavra[this.getNumPalavras()];
		for(int i=0; i<this.getNumPalavras(); i++) {
			palavras[i] = this.itens[i].getPalavra();
		}
		return palavras;
        //pra acessar palavras, da rodada, precisa acessar o item, que acessa a palavra
	}

	public int getNumPalavras() {
		return this.itens.length;
	}
	
	public void tentar(char codigo) {
		//O jogador só pode tentar ou arriscar, se a rodada não encerrou. 
		//Ao final de tentar ou arriscar, se encerrou, atualizar os pontos do Jogador.
		
		if(this.encerrou()) {
			throw new RuntimeException("Impossível tentar. Rodada já encerrou.");
		}
		if(this.getNumPalavras()==0) {
			throw new RuntimeException("Deve ter pelo menos um item/palavra na rodada");
		}
		
		//se eu ainda estou tentando, nao descobri
		boolean descobriu = false;
		
		//o tentar da rodada chama o tentar de cada item
		for(Item item : this.itens) {
			if(item.tentar(codigo)&&!descobriu) { //se tentar deu certo e ainda não descobriu
				descobriu = true; //descobriu é true porque acertou
			}
		}
		
		if(!descobriu) {
			this.erradas.add(Palavra.getLetraFactory().getLetra(codigo));
		}
		
		if(this.encerrou()) {
			this.jogador.atualizarPontuacao(this.jogador.getPontuacao() + this.calcularPontos());
		}
		
	}
	
	public void arriscar(String[] palavras) {
		if(this.encerrou()) {
			throw new RuntimeException("Impossível arriscar. Rodada já encerrou.");
		}
		
        //arriscar da rodada chama o arriscar de cada item
		for(int posicaoPalavra=0; posicaoPalavra<this.getNumPalavras(); posicaoPalavra++) {
			this.itens[posicaoPalavra].arriscar(palavras[posicaoPalavra]);
		}
	}
	
	public void exibirItens(Object contexto) {
		//chama os itens e pede pra eles se exibirem
        for(Item item : this.itens){
            item.exibir(contexto);
            System.out.println();
        }
	}
	
	public void exibirBoneco(Object contexto) {
		//boneco exibe as partes, de acordo com a quantidade de letras erradas
        this.boneco.exibir(contexto, this.erradas.size());
	}
	
	public void exibirPalavras(Object contexto) {
        for(Item item : this.itens){
            item.getPalavra().exibir(contexto);
            System.out.println();
        }	}
	
	public void exibirLetrasErradas(Object contexto) {
        for(Letra letra : this.erradas){
            letra.exibir(contexto);
            System.out.println(" ");
        }
	}
	
	public Letra[] getTentativas() {
	    Letra[] certas = this.getCertas();
	    Letra[] erradas = this.getErradas();
	    Letra[] tentativas = new Letra[certas.length + erradas.length];
	    
	    //src: array de origem 
	    //srcPos: posição inicial no array de origem (normalmente 0 para copiar desde o início)
	    //dest: array de destino
	    //destPos: posição inicial no array de destino (normalmente 0 para colar desde o início)
	    //length
	    System.arraycopy(certas, 0, tentativas, 0, certas.length);
	    System.arraycopy(erradas, 0, tentativas, certas.length, erradas.length);
	    
	    return tentativas;
	}
	
    public Letra[] getCertas() {
        ArrayList<Letra> acertos = new ArrayList<Letra>();
        for(Item item : this.itens){
            for(Letra letra : item.getLetrasDescobertas()){
            	//garante que nao tenham letras repetidas
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
        if(!this.descobriu()){
        	return 0;
        }
        int pontosTotaisPorLetrasEncobertas = 0;
        for (Item item : this.itens) {
            pontosTotaisPorLetrasEncobertas += item.calcularPontosLetrasEncobertas(pontosPorLetraEncoberta);
        }
        return pontosQuandoDescobreTodasAsPalavras + pontosTotaisPorLetrasEncobertas;
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
        //arriscou da rodada chama o arriscou de cada item
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
