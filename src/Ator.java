import java.util.List;

public interface Ator {

    void agir(List<Ator> novosAtores);

    boolean estaAtivo();
}
