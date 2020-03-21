package br.com.welson.estoque.cliente.relatorioclientes;

import lombok.Getter;
import lombok.Setter;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.relatorio.RelatorioRespostaDTO;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;

@Getter
@Setter
@Relatorio("usuarios.jrxml")
public class RelatorioClientesRespostaDTO extends RelatorioRespostaDTO<ClienteDTO> {

}
