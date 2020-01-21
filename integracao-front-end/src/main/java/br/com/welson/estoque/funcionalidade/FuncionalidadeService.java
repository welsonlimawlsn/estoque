package br.com.welson.estoque.funcionalidade;

import br.com.welson.estoque.usuario.novousuario.NovoUsuarioRequisicaoDTO;
import br.com.welson.estoque.usuario.novousuario.NovoUsuarioRespostaDTO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/usuario")
public interface FuncionalidadeService {

    @POST
    NovoUsuarioRespostaDTO novoUsuario(NovoUsuarioRequisicaoDTO requisicao);
}
