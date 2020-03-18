package br.com.welson.estoque.relatorio;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface RespostaRelatorio<T> {

    File getRelatorio();

    void setRelatorio(File relatorio);

    List<T> getLista();

    void setLista(List<T> lista);

    Map<String, Object> getParametros();

    void setParametros(Map<String, Object> parametros);

}
