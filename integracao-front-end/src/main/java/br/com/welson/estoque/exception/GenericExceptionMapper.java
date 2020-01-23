package br.com.welson.estoque.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.welson.estoque.util.exception.NegocioException;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private Logger logger = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        logger.trace(exception);
        Throwable throwable = exception;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        if (throwable instanceof NegocioException) {
            NegocioException negocioException = (NegocioException) throwable;
            return Response.status(negocioException.getCodigo()).entity(negocioException.getErros()).build();
        }

        throw new RuntimeException(exception);
    }

}
