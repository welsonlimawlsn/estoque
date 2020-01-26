package br.com.welson.estoque.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.welson.estoque.util.exception.NegocioException;

@Provider
public class NegocioExceptionMapper implements ExceptionMapper<NegocioException> {

    private Logger logger = Logger.getLogger(NegocioExceptionMapper.class);

    @Override
    public Response toResponse(NegocioException exception) {
        logger.error(exception.getMessage(), exception);
        return Response
                .status(exception.getCodigo())
                .entity(exception.getErros())
                .build();
    }

}
