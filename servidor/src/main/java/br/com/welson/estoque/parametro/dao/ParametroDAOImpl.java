package br.com.welson.estoque.parametro.dao;

import br.com.welson.estoque.parametro.entidade.Parametro;
import br.com.welson.estoque.util.dao.DAOImpl;

public class ParametroDAOImpl extends DAOImpl<Parametro, Long> implements ParametroDAO {

    public ParametroDAOImpl() {
        super(Parametro.class);
    }

}
