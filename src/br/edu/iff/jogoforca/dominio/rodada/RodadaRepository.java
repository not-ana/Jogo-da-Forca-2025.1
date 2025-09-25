package br.edu.iff.jogoforca.dominio.rodada;

<<<<<<< HEAD
public interface RodadaRepository {
=======
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.repository.Repository;
import br.edu.iff.repository.RepositoryException;
>>>>>>> refs/remotes/origin/Rodada

public interface RodadaRepository extends Repository{
	
	public Rodada getPorId(long id);
	public Rodada[] getPorJogador(Jogador jogador);
	

	public void inserir(Rodada rodada) throws RepositoryException;
    public void atualizar(Rodada rodada) throws RepositoryException;
    public void remover(Rodada rodada) throws RepositoryException;	
}
