package br.com.welson.estoque.requisicao.relatorio;

import lombok.Getter;
import lombok.Setter;

import br.com.welson.estoque.relatorio.RelatorioRespostaDTO;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;

@Getter
@Setter
@Relatorio(value = "requisicoes.jrxml", nomeArquivoFinal = "Relatório de Requisições")
public class EmiteRelatorioRequisicaoRespostaDTO extends RelatorioRespostaDTO<RequisicaoRelatorioDTO> {

}
