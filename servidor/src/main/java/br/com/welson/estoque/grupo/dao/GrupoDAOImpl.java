package br.com.welson.estoque.grupo.dao;

import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.util.dao.DAOImpl;

public class GrupoDAOImpl extends DAOImpl<Grupo, Long> implements GrupoDAO {

    public GrupoDAOImpl() {
        super(Grupo.class);
    }

}
