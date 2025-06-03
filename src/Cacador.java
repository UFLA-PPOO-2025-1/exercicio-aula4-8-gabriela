import java.util.List;
import java.util.Random;

public class Cacador implements Ator {
    private static final Random rand = new Random();
    private static final int NUMERO_DE_TIROS = 3;

    private Campo campo;
    private Localizacao localizacao;
    private boolean ativo;

    public Cacador(Campo campo, Localizacao localizacao) {
        this.campo = campo;
        this.localizacao = localizacao;
        this.ativo = true;
        campo.colocar(this, localizacao);
    }

    @Override
    public void agir(List<Ator> novosAtores) {
        // Move-se para uma posição aleatória livre ao redor
        List<Localizacao> locaisLivres = campo.localizacoesVizinhasLivres(localizacao);
        if (!locaisLivres.isEmpty()) {
            Localizacao novaLocalizacao = locaisLivres.get(0);
            campo.limpar(localizacao);   // Remove da posição antiga
            localizacao = novaLocalizacao;
            campo.colocar(this, localizacao); // Coloca na nova posição
        }

        // Dispara em alvos aleatórios no campo
        for (int i = 0; i < NUMERO_DE_TIROS; i++) {
            Localizacao alvo = campo.localizacaoAleatoria();
            if (alvo != null) {
                Object objeto = campo.obterObjetoEm(alvo);
                if (objeto instanceof Animal) {
                    ((Animal) objeto).morrer(); // O animal é abatido
                }
            }
        }
    }

    @Override
    public boolean estaAtivo() {
        return ativo;
    }
}