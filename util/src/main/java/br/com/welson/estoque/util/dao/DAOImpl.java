package br.com.welson.estoque.util.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class DAOImpl<ENTIDADE, ID> implements DAO<ENTIDADE, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<ENTIDADE> entidadeClass;

    public DAOImpl(Class<ENTIDADE> entidadeClass) {
        this.entidadeClass = entidadeClass;
    }

    @Override
    public Optional<ENTIDADE> buscaPorId(ID id) {
        return Optional.ofNullable(entityManager.find(entidadeClass, id));
    }

    @Override
    public void insere(ENTIDADE entidade) {
        entityManager.persist(entidade);
    }

    @Override
    public void atualiza(ENTIDADE entidade) {
        entityManager.merge(entidade);
    }

    @Override
    public List<ENTIDADE> listaTodos() {
        return entityManager.createQuery("SELECT e FROM " + entidadeClass.getSimpleName() + " e", entidadeClass).getResultList();
    }

    protected Optional<ENTIDADE> getSingleResultWithOptional(TypedQuery<ENTIDADE> query) {
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
