package br.com.welson.estoque.util.dao;

import java.util.Optional;

public interface DAO<ENTIDADE, ID> {

    Optional<ENTIDADE> buscaPorId(ID id);

    void insere(ENTIDADE entidade);

    void atualiza(ENTIDADE entidade);

}
