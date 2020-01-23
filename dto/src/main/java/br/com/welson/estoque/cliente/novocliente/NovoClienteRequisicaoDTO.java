package br.com.welson.estoque.cliente.novocliente;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import br.com.welson.estoque.requisicao.RequisicaoDTO;

@Getter
@Setter
public class NovoClienteRequisicaoDTO extends RequisicaoDTO<NovoClienteRespostaDTO> {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String usuario;

    @NotEmpty
    private String email;

}
