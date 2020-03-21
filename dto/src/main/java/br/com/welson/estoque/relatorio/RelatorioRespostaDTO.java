package br.com.welson.estoque.relatorio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
public class RelatorioRespostaDTO<T> extends RespostaDTO {

    private List<T> lista;

    private Map<String, Object> parametros;

    private Object relatorio;

}
