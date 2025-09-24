package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.dominio.ObjetoDominioImpl;


public class Jogador extends ObjetoDominioImpl {

	private String nome;
	private int pontuacao;

	public static Jogador criar(long id, String nome) {

		return new Jogador(id, nome);
	}

	public static Jogador reconstituir(long id, String nome, int pontuacao) {

		return new Jogador(id, nome, pontuacao);
	}
