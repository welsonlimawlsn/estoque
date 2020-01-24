package br.com.welson.estoque.cliente.processador;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.inject.Inject;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.cliente.dao.ClienteDAO;
import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.cliente.login.LoginClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.login.LoginClienteRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.parametro.CodigoParametro;
import br.com.welson.estoque.parametro.service.ParametroService;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;
import br.com.welson.estoque.util.seguranca.HashUtil;

@Processador(CodigoFuncionalidade.LOGIN_CLIENTE)
public class LoginClienteProcessador extends AbstractProcessadorRequisicao<LoginClienteRequisicaoDTO, LoginClienteRespostaDTO> {

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ParametroService parametroService;

    @Override
    protected void executaRequisicao(LoginClienteRequisicaoDTO requisicao, LoginClienteRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        Cliente cliente = clienteDAO.buscaPorUsuarioESenha(requisicao.getUsuario(), HashUtil.criptografa(requisicao.getUsuario() + requisicao.getSenha()))
                .orElseThrow(() -> new NegocioException(EstoqueErro.USUARIO_OU_SENHA_INVALIDOS));

        ClienteDTO clienteDTO = ClienteDTO.builder()
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .nomeUsuario(cliente.getUsuario())
                .build();

        byte[] key = Base64.getDecoder().decode(
                parametroService.buscaParametro(CodigoParametro.CHAVE_CRIPTOGRAFIA_TOKEN, String.class));


        Long tempoExpiracao = parametroService.buscaParametro(CodigoParametro.TEMPO_PARA_EXPIRACAO_TOKEN, Long.class);

        ZonedDateTime expiracao = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(tempoExpiracao);

        Map<String, Object> claims = new HashMap<>();

        claims.put("cliente", clienteDTO);

        String token = Jwts.builder()
                .setSubject(cliente.getCpf())
                .addClaims(claims)
                .signWith(Keys.hmacShaKeyFor(key))
                .setExpiration(Date.from(expiracao.toInstant()))
                .compact();

        resposta.setToken("Bearer " + token);
        resposta.setExpiracao(expiracao);
    }

}
