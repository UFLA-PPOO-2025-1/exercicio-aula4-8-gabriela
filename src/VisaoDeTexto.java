import java.awt.Color;

/**
 * Visão textual da simulação.
 * Mostra no terminal o número de raposas e coelhos a cada passo.
 */
public class VisaoDeTexto implements VisaoSimulador {
    private EstatisticasCampo estatisticas;

    public VisaoDeTexto() {
        estatisticas = new EstatisticasCampo();
    }

    @Override
    public void definirCor(Class<?> classeAnimal, Color cor) {
        // Não aplicável na visão textual
    }

    @Override
    public boolean ehViavel(Campo campo) {
        return estatisticas.ehViavel(campo);
    }

    @Override
    public void mostrarStatus(int passo, Campo campo) {
        System.out.println("Passo " + passo + " - " + estatisticas.obterDetalhesPopulacao(campo));
    }

    @Override
    public void reiniciar() {
        estatisticas.reiniciar();
    }

    @Override
    public void reabilitarOpcoes() {
        // Nada a fazer na visão textual
    }
}
