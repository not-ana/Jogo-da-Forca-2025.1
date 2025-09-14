package br.edu.iff.dominio;
import java.util.Objects;

public abstract class ObjetoDominioImpl implements ObjetoDominio{
	
	//adicionar 'final' evita da classe ter 'id' modificado
	private final long id;
	
	public ObjetoDominioImpl(long id) {
		super();
		this.id = id;
	}
	
    //nao tem setId porque 'id' nao deve ser alterado
    @Override
    public long getId() {
        return id;
    }
    
    
    // equals() e hashCode() com final para evitar override das subclasses
    //value object!!!
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetoDominioImpl that = (ObjetoDominioImpl) o;
        return id == that.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
