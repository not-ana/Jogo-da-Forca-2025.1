package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {

    private static final String[] TIPOS_REPOSITORY_FACTORY = {"memoria, relacional"};
    private static final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private static final String[] TIPOS_RODADA_FACTORY = {"sorteio"};
    private static Aplicacao soleInstance;

    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    public static Aplicacao getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new Aplicacao();
        }

        return soleInstance;
    }

    private Aplicacao() {}

    public void configurar() {
        RodadaSorteioFactory.createSoleInstance(this.getRepositoryFactory().getRodadaRepository(), this.getRepositoryFactory().getTemaRepository(), this.getRepositoryFactory().getPalavraRepository());
        TemaFactoryImpl.createSoleInstance(this.getRepositoryFactory().getTemaRepository());
        PalavraFactoryImpl.createSoleInstance(this.getRepositoryFactory().getPalavraRepository());
        JogadorFactoryImpl.createSoleInstance(this.getRepositoryFactory().getJogadorRepository());

        Palavra.setLetraFactory(this.getLetraFactory());
        Rodada.setBonecoFactory(this.getBonecoFactory());

        PalavraAppService.createSoleInstance(this.getRepositoryFactory().getTemaRepository(), this.getRepositoryFactory().getPalavraRepository(), this.getPalavraFactory());

        RodadaAppService.createSoleInstance(this.getRodadaFactory(), this.getRepositoryFactory().getRodadaRepository(), this.getRepositoryFactory().getJogadorRepository());
    }

    public String[] getTiposRepositoryFactory() {
        return TIPOS_REPOSITORY_FACTORY;
    }

    public void setTipoRepositoryFactory(String tipo) {
        this.tipoRepositoryFactory = tipo;
        this.configurar();
    }

    public RepositoryFactory getRepositoryFactory() {
        if(this.tipoRepositoryFactory.equals(Aplicacao.TIPOS_REPOSITORY_FACTORY[0])) {
            return MemoriaRepositoryFactory.getSoleInstance();
        } else if (this.tipoRepositoryFactory.equals(Aplicacao.TIPOS_REPOSITORY_FACTORY[1])){
            return BDRRepositoryFactory.getSoleInstance();
        } else {
            throw new RuntimeException("Tipo de repositório não previsto.");
        }
    }

    public String[] getTiposElementoGraficoFactory() {
        return TIPOS_ELEMENTO_GRAFICO_FACTORY;
    }

    public void setTipoElementoGraficoFactory(String tipo) {
        this.tipoElementoGraficoFactory = tipo;
        this.configurar();
    }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        if (this.tipoElementoGraficoFactory.equals(Aplicacao.TIPOS_ELEMENTO_GRAFICO_FACTORY[0])) {
            return ElementoGraficoTextoFactory.getSoleInstance();
        } else if (this.tipoElementoGraficoFactory.equals(Aplicacao.TIPOS_ELEMENTO_GRAFICO_FACTORY[1])){
            return ElementoGraficoImagemFactory.getSoleInstance();
        } else {
            throw new RuntimeException("Este tipo de factory não existe.");
        }
    }

    public BonecoFactory getBonecoFactory() {
        return this.getElementoGraficoFactory();
    }

    public LetraFactory getLetraFactory() {
        return this.getElementoGraficoFactory();
    }

    public String[] getTiposRodadaFactory() {
        return TIPOS_RODADA_FACTORY;
    }

    public void setTipoRodadaFactory(String tipo) {
        this.tipoRodadaFactory = tipo;
        this.configurar();
    }

    public RodadaFactory getRodadaFactory() {
        if(this.tipoRodadaFactory.compareTo(TIPOS_RODADA_FACTORY[0]) == 0) {
            return RodadaSorteioFactory.getSoleInstance();
        } else {
            throw new RuntimeException("Tipo de factory não existente.");
        }
    }

    public TemaFactory getTemaFactory() {
        return TemaFactoryImpl.getSoleInstance();
    }

    public PalavraFactory getPalavraFactory() {
        return PalavraFactoryImpl.getSoleInstance();
    }

    public JogadorFactory getJogadorFactory() {
        return JogadorFactoryImpl.getSoleInstance();
    }
}
