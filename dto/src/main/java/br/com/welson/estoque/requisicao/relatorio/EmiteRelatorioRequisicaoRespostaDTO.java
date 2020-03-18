package br.com.welson.estoque.requisicao.relatorio;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
import java.util.Map;

import br.com.welson.estoque.relatorio.RespostaRelatorio;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
@Relatorio("requisicoes.jrxml")
public class EmiteRelatorioRequisicaoRespostaDTO extends RespostaDTO implements RespostaRelatorio<RequisicaoRelatorioDTO> {

    private File relatorio;

    private List<RequisicaoRelatorioDTO> lista;

    private Map<String, Object> parametros;

}
