package br.com.welson.estoque.cliente.relatorioclientes;

import lombok.Getter;
import lombok.Setter;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.relatorio.RelatorioRespostaDTO;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;

@Getter
@Setter
@Relatorio(template = "usuarios.jrxml", nomeArquivoFinal = "Relatório de Usuários")
public class RelatorioClientesRespostaDTO extends RelatorioRespostaDTO<ClienteDTO> {

}
