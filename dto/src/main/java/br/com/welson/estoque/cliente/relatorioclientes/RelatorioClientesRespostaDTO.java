package br.com.welson.estoque.cliente.relatorioclientes;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.relatorio.RespostaRelatorio;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
@Relatorio(vm = "relatorioUsuarios.vm")
public class RelatorioClientesRespostaDTO extends RespostaDTO implements RespostaRelatorio {

    private File relatorio;

    private List<ClienteDTO> clientes;

}
