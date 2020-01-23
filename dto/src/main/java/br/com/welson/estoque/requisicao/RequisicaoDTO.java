package br.com.welson.estoque.requisicao;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import java.time.LocalDateTime;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Getter
@Setter
public abstract class RequisicaoDTO<RESPOSTA extends RespostaDTO> {

    @JsonbTransient
    private Long id;

    private RESPOSTA resposta;

    @JsonbTransient
    private String motivoFalha;

    @JsonbTransient
    private String ipOrigem;

    @JsonbTransient
    private Cliente cliente;

    @JsonbTransient
    private Funcionalidade funcionalidade;

    @JsonbTransient
    private LocalDateTime dataHora;

    public void valida() throws NegocioException, InfraestruturaException {
    }

}
