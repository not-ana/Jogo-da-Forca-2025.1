package br.edu.iff.jogoforca.dominio.rodada;

import java.util.List;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl{

	private static int maxPalavras = 3;
	private static int maxErros = 10;
	private static int pontosQuandoDescobreTodasAsPalavras = 100;
	private static int pontosPorLetraEncoberta = 15;
	
	private Jogador jogador;
	private List<Letra> erradas;
	private static BonecoFactory bonecoFactory;
	private Item[] items;
	
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
	
	public static Rodada reconstituir(long id, Item[] items, Letra[] erradas, Jogador jogador) {
		if(bonecoFactory==null) {
			throw new RuntimeException("É preciso inicializar Boneco antes da Rodada.");
		}
		return new Rodada(id, items, erradas, jogador);
	}
		
	private Rodada(long id, Palavra[] palavras, Jogador jogador) {
		
	}
	
	private Rodada(long id, Item[] items, Letra[] erradas, Jogador jogador) {
		
	}
	
	public Jogador getJogador() {
		
	}
	
	public Tema getTema() {
		
	}
	
	public Palavra[] getPalavras() {
		
	}

	public int getNumPalavras() {
		
	}
	
	public void tentar(char codigo) {
		
	}
	
	public void arriscar(String[] palavras) {
		
	}
	
	public void exibirItens(Object contexto) {
		
	}
	
	public void exibirBoneco(Object contexto) {
		
	}
	
	public void exibirPalavras(Object contexto) {
		
	}
	
	public void exibirLetrasErradas(Object contexto) {
		
	}
	
	public Letra[] getTentativas() {
		
	}
	
	public Letra[] getCertas() {
		
	}
	
	public Letra[] getErradas() {
		
	}
	
	public int calcularPontos() {
		
	}
	
	public boolean encerrou() {
		
	}
	
	public boolean descobriu() {
		
	}
	
	public boolean arriscou() {
		
	}

	public int getQtdeTentativasRestantes() {
		
	}
	
	public int getQtdeErros() {
		
	}
	
	public int getQtdeAcertos() {
		
	}
	
	public int getQtdeTentativas() {
		
	}
	
}
