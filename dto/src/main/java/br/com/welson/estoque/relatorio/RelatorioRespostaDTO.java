package br.com.welson.estoque.relatorio;

import br.com.welson.estoque.requisicao.RespostaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RelatorioRespostaDTO<T> extends RespostaDTO
{

    @JsonIgnore
    private List<T> lista;

    @JsonIgnore
    private Map<String, Object> parametros;

    private String nomeRelatorioGerado;

}
