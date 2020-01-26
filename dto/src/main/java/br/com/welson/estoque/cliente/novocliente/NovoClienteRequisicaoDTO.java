package br.com.welson.estoque.cliente.novocliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.welson.estoque.requisicao.RequisicaoDTO;

@Getter
@Setter
public class NovoClienteRequisicaoDTO extends RequisicaoDTO<NovoClienteRespostaDTO> {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotEmpty
    private String usuario;

    @NotEmpty
    private String email;

    @NotNull
    private Long codigoGrupo;

}
