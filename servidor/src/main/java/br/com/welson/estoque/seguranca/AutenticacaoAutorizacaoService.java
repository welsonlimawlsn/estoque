package br.com.welson.estoque.seguranca;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.cliente.dao.ClienteDAO;
import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.parametro.CodigoParametro;
import br.com.welson.estoque.parametro.service.ParametroService;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;
import br.com.welson.estoque.util.seguranca.HashUtil;

public class AutenticacaoAutorizacaoService {

    public static final String BEARER = "Bearer ";

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ParametroService parametroService;

    @Inject
    private HttpServletRequest request;

    public Autenticacao autentica(String usuario, String senha) throws InfraestruturaException, NegocioException {
        Cliente cliente = clienteDAO.buscaPorUsuarioESenha(usuario, HashUtil.criptografa(usuario + senha))
                .orElseThrow(() -> new NegocioException(EstoqueErro.USUARIO_OU_SENHA_INVALIDOS));

        ClienteDTO clienteDTO = ClienteDTO.builder()
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .nomeUsuario(cliente.getUsuario())
                .build();

        Long tempoExpiracao = parametroService.buscaParametro(CodigoParametro.TEMPO_PARA_EXPIRACAO_TOKEN, Long.class);

        ZonedDateTime expiracao = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(tempoExpiracao);

        String token = Jwts.builder()
                .setSubject(cliente.getCpf())
                .signWith(getChave())
                .setExpiration(Date.from(expiracao.toInstant()))
                .compact();

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setCliente(clienteDTO);
        autenticacao.setExpiracao(expiracao);
        autenticacao.setToken(BEARER + token);

        return autenticacao;
    }

    public Cliente getAutorizado() throws InfraestruturaException, NegocioException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && !authorization.isEmpty() && authorization.startsWith(BEARER)) {
            try {
                String subject = Jwts.parser().setSigningKey(getChave()).parseClaimsJws(authorization.replace(BEARER, "")).getBody().getSubject();
                return clienteDAO.buscaPorId(subject).orElseThrow(() -> new InfraestruturaException("Subject invalido."));
            } catch (ExpiredJwtException e) {
                throw new NegocioException(EstoqueErro.TOKEN_EXPIRADO);
            }
        }
        return null;
    }

    private SecretKey getChave() throws InfraestruturaException {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(
                parametroService.buscaParametro(CodigoParametro.CHAVE_CRIPTOGRAFIA_TOKEN, String.class)));
    }

}
