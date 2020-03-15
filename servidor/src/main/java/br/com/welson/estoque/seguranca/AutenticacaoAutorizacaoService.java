package br.com.welson.estoque.seguranca;

import io.jsonwebtoken.Claims;
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
import java.util.HashMap;
import java.util.Map;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.cliente.dao.ClienteDAO;
import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.parametro.CodigoParametro;
import br.com.welson.estoque.parametro.service.ParametroService;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

public class AutenticacaoAutorizacaoService {

    public static final String BEARER = "Bearer ";

    public static final String IP_ORIGEM = "ip";

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ParametroService parametroService;

    @Inject
    private HttpServletRequest request;

    @Inject
    private SenhaService senhaService;

    public Autenticacao autentica(String usuario, String senha) throws InfraestruturaException, NegocioException {
        Cliente cliente = clienteDAO.buscaPorUsuario(usuario)
                .orElseThrow(() -> new NegocioException(EstoqueErro.USUARIO_OU_SENHA_INVALIDOS));

        if (!senhaService.compara(senha, cliente.getSenha())) {
            throw new NegocioException(EstoqueErro.USUARIO_OU_SENHA_INVALIDOS);
        }

        ClienteDTO clienteDTO = ClienteDTO.builder()
                .email(cliente.getEmail())
                .nome(cliente.getNome())
                .nomeUsuario(cliente.getUsuario())
                .build();

        Long tempoExpiracao = parametroService.buscaParametro(CodigoParametro.TEMPO_PARA_EXPIRACAO_TOKEN, Long.class);

        ZonedDateTime expiracao = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(tempoExpiracao);

        Map<String, Object> claims = new HashMap<>();
        claims.put(IP_ORIGEM, request.getRemoteAddr());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(cliente.getCpf())
                .signWith(getChave())
                .setExpiration(Date.from(expiracao.toInstant()))
                .compact();

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setClienteDTO(clienteDTO);
        autenticacao.setExpiracao(expiracao);
        autenticacao.setToken(BEARER + token);
        autenticacao.setCliente(cliente);

        return autenticacao;
    }

    public Cliente getAutorizado() throws InfraestruturaException, NegocioException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && !authorization.isEmpty() && authorization.startsWith(BEARER)) {
            try {
                Claims claims = getClaims(authorization);

                validaOrigemRequisicao(claims);

                return getCliente(claims);
            } catch (ExpiredJwtException e) {
                throw new NegocioException(EstoqueErro.TOKEN_EXPIRADO);
            }
        }
        return null;
    }

    private Cliente getCliente(Claims claims) throws InfraestruturaException {
        String subject = claims.getSubject();
        return clienteDAO.buscaPorId(subject).orElseThrow(() -> new InfraestruturaException("Subject invalido."));
    }

    private Claims getClaims(String authorization) throws InfraestruturaException {
        return Jwts.parser()
                .setSigningKey(getChave())
                .parseClaimsJws(authorization.replace(BEARER, ""))
                .getBody();
    }

    private void validaOrigemRequisicao(Claims claims) throws NegocioException {
        String ipOrigem = claims.get(IP_ORIGEM, String.class);

        if (!ipOrigem.equals(request.getRemoteAddr())) {
            throw new NegocioException(EstoqueErro.TOKEN_INVALIDO);
        }
    }

    private SecretKey getChave() throws InfraestruturaException {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(
                parametroService.buscaParametro(CodigoParametro.CHAVE_CRIPTOGRAFIA_TOKEN, String.class)));
    }

}
