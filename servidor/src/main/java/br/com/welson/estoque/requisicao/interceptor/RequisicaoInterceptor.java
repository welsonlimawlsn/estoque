package br.com.welson.estoque.requisicao.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.funcionalidade.dao.FuncionalidadeDAO;
import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.anotacao.RequisicaoService;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Interceptor
@RequisicaoService
public class RequisicaoInterceptor {

    @Inject
    private HttpServletRequest httpRequest;

    @Inject
    private FuncionalidadeDAO funcionalidadeDAO;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {

        RequisicaoDTO<?> requisicao = getRequisicao(context);

        validaRequisicao(requisicao);

        Funcionalidade annotation = context.getMethod().getParameters()[0].getAnnotation(Funcionalidade.class);

        preecheRequisicao(requisicao, annotation.value());

        return context.proceed();
    }

    private void preecheRequisicao(RequisicaoDTO<?> requisicao, Long codigoFuncionalidade) throws InfraestruturaException {
        requisicao.setIpOrigem(httpRequest.getRemoteAddr());
        requisicao.setDataHora(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
        requisicao.setFuncionalidade(funcionalidadeDAO.buscaPorId(codigoFuncionalidade)
                .orElseThrow(() -> new InfraestruturaException("Funcionalidade de código " + codigoFuncionalidade + " não existe.")));
        requisicao.setCliente(buscaCliente());
    }

    private Cliente buscaCliente() {
        String authorization = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && !authorization.isEmpty() && authorization.startsWith("Bearer ")) {
            // Faz algo pra buscar o usuario
        }
        return null;
    }

    private void validaRequisicao(RequisicaoDTO<?> requisicao) throws NegocioException, InfraestruturaException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<RequisicaoDTO<?>>> violations = validator.validate(requisicao);
        if (!violations.isEmpty()) {
            throw new NegocioException(violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()),
                    Response.Status.BAD_REQUEST);
        }
        requisicao.valida();
    }

    private RequisicaoDTO<?> getRequisicao(InvocationContext context) {
        Object[] parameters = context.getParameters();

        if (parameters.length != 1 || !(parameters[0] instanceof RequisicaoDTO)) {
            throw new RuntimeException("O parametro do seu endpoint deve herdar de" + RequisicaoDTO.class.getName());
        }

        return (RequisicaoDTO<?>) parameters[0];
    }

}
