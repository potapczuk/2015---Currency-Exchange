package com.potapczuk.currency.app.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.potapczuk.currency.app.model.Currency;

/**
 *
 * Repository class for the Currency entity
 *
 */
@Repository
public class CurrencyRepository {

    private static final Logger LOGGER = Logger.getLogger(CurrencyRepository.class);

    @PersistenceContext
    EntityManager em;

    /**
     *
     * finds a list of currencys
     * @return -  a list of matching currencys, or an empty collection if no match found
     */
    public List<Currency> getCurrencies() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Currency> searchQuery = cb.createQuery(Currency.class);
        Root<Currency> searchRoot = searchQuery.from(Currency.class);
        searchQuery.select(searchRoot);

        List<Order> orderList = new ArrayList<>();
        orderList.add(cb.asc(searchRoot.get("name")));
        searchQuery.orderBy(orderList);

        TypedQuery<Currency> filterQuery = em.createQuery(searchQuery);

        return filterQuery.getResultList();
    }

    /**
     * Delete a currency, given its identifier
     *
     * @param deletedCurrencyId - the id of the currency to be deleted
     */
    public void delete(Long deletedCurrencyId) {
        Currency delete = em.find(Currency.class, deletedCurrencyId);
        em.remove(delete);
    }

    /**
     *
     * finds a currency given its id
     *
     */
    public Currency findCurrencyById(Long id) {
        return em.find(Currency.class, id);
    }

    /**
     *
     * save changes made to a currency, or create the currency if its a new currency.
     *
     */
    public Currency save(Currency currency) {
        return em.merge(currency);
    }
}
