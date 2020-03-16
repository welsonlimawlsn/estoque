package br.com.welson.estoque.relatorio;

import java.io.File;
import java.util.List;

public interface RespostaRelatorio<T> {

    File getRelatorio();

    void setRelatorio(File relatorio);

    List<T> getLista();

}
