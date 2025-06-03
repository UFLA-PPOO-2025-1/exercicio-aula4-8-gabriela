import java.util.List;
import java.util.Random;

public class Cacador implements Ator {
    private static final Random rand = new Random();
    private static final int NUMERO_DE_TIROS = 2; // Reduzindo impacto dos caçadores
    private static final double CHANCE_DE_ACERTO = 0.7; // 70% de chance de acertar um animal

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
        // Tenta se mover para uma posição livre próxima
        List<Localizacao> locaisLivres = campo.localizacoesVizinhasLivres(localizacao);
        if (!locaisLivres.isEmpty()) {
            Localizacao novaLocalizacao = locaisLivres.get(0);
            campo.limpar(localizacao); // Remove da posição antiga
            localizacao = novaLocalizacao;
            campo.colocar(this, localizacao); // Coloca na nova posição
        }

        // Dispara em alvos aleatórios no campo
        for (int i = 0; i < NUMERO_DE_TIROS; i++) {
            Localizacao alvo = campo.localizacaoAleatoria();
            if (alvo != null) {
                Object objeto = campo.obterObjetoEm(alvo);
                if (objeto instanceof Animal && rand.nextDouble() <= CHANCE_DE_ACERTO) {
                    ((Animal) objeto).morrer(); // Mata o animal apenas se acertar
                }
            }
        }
    }

    @Override
    public boolean estaAtivo() {
        return ativo;
    }
}