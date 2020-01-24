package br.com.welson.estoque.parametro.service;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;

import br.com.welson.estoque.parametro.dao.ParametroDAO;
import br.com.welson.estoque.util.exception.InfraestruturaException;

public class ParametroService {

    @Inject
    private ParametroDAO parametroDAO;

    public <T> T buscaParametro(Long codigo, Class<T> retorno) throws InfraestruturaException {
        try {
            String valorParametro = parametroDAO.buscaPorId(codigo)
                    .orElseThrow(() -> new InfraestruturaException("Parametro invalido.")).getValor();
            return retorno.getConstructor(String.class).newInstance(valorParametro);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new InfraestruturaException(e);
        }
    }

}
