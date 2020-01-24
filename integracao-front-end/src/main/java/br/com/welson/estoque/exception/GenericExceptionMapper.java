package br.com.welson.estoque.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

import br.com.welson.estoque.util.exception.NegocioException;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private Logger logger = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        Throwable throwable = exception;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        logger.error(throwable.getMessage(), exception);

        if (throwable instanceof NegocioException) {
            NegocioException negocioException = (NegocioException) throwable;
            return Response
                    .status(negocioException.getCodigo())
                    .entity(negocioException.getErros())
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Collections.singletonList("Erro interno, contate o administrador."))
                .build();
    }

}
