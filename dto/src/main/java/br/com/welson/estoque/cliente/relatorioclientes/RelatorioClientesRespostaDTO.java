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
@Relatorio("usuarios.jrxml")
public class RelatorioClientesRespostaDTO extends RespostaDTO implements RespostaRelatorio<ClienteDTO> {

    private File relatorio;

    private List<ClienteDTO> clientes;

    @Override
    public List<ClienteDTO> getLista() {
        return clientes;
    }

}
