package br.com.welson.estoque.requisicao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Getter
@Setter
public abstract class RequisicaoDTO<RESPOSTA extends RespostaDTO> {

    @JsonIgnore
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RESPOSTA resposta;

    @JsonIgnore
    private String motivoFalha;

    @JsonIgnore
    private String ipOrigem;

    @JsonIgnore
    private Cliente cliente;

    @JsonIgnore
    private Funcionalidade funcionalidade;

    @JsonIgnore
    private LocalDateTime dataHora;

    public void valida() throws NegocioException, InfraestruturaException {
    }

}
