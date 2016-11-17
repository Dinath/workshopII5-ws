/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Soyer Alex <a.soyer@cubis-helios.com>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) throws Exception {
        try {
            getEntityManager().persist(entity);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void edit(T entity) throws Exception {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) throws Exception {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) throws Exception {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int count() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
