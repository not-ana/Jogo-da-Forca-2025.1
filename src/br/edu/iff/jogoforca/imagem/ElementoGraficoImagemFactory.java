package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;

public class ElementoGraficoImagemFactory  implements ElementoGraficoFactory {

    private static ElementoGraficoImagemFactory soleInstance;

    public static ElementoGraficoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoImagemFactory();
            return soleInstance;
        } else {
            return soleInstance;
        }
    }

    @Override
    public Boneco getBoneco() {
        return BonecoImagemFactory.getSoleInstance().getBoneco();
    }

    @Override
    public Letra getLetra(char codigo) {
        return LetraImagemFactory.getSoleInstance().getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return LetraImagemFactory.getSoleInstance().getLetraEncoberta();
    }
}