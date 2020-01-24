package br.com.welson.estoque.cliente.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import br.com.welson.estoque.requisicao.RequisicaoDTO;

@Getter
@Setter
public class LoginClienteRequisicaoDTO extends RequisicaoDTO<LoginClienteRespostaDTO> {

    @NotEmpty
    private String usuario;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

}
