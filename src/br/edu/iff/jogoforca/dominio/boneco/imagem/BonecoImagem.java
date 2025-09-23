package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import java.util.List;

/**
 * Implementação do boneco para exibição com imagem
 * Implementa padrão Singleton conforme diagrama UML
 */
public class BonecoImagem implements Boneco {
    
    // SINGLETON: única instância
    private static BonecoImagem soleInstance;
    
    /**
     * SINGLETON: Método para obter a única instância
     */
    public static BonecoImagem getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoImagem();
        }
        return soleInstance;
    }
    
    /**
     * Construtor conforme diagrama UML
     */
    public BonecoImagem() {
        // Construtor público conforme UML
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void exibir(Object contexto, int partes) {
        // Para imagem, o contexto pode ser uma lista onde adicionar URLs de imagens
        if (contexto instanceof List) {
            List<String> urls = (List<String>) contexto;
            String urlImagem = "/images/boneco/boneco_" + partes + ".png";
            urls.add(urlImagem);
        }
        // Simula exibição da imagem
        System.out.println("[IMG:Boneco" + partes + "]");
    }
}
