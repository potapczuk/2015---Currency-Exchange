package com.potapczuk.currency.app.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.potapczuk.currency.app.model.Currency;
import com.potapczuk.currency.app.model.CurrencyExchange;

/**
 *
 * Repository class for the CurrencyExchange entity
 *
 */
@Repository
public class CurrencyExchangeRepository {

    private static final Logger LOGGER = Logger.getLogger(CurrencyExchangeRepository.class);

    @PersistenceContext
    EntityManager em;

    /**
     *
     * finds a currency exchange given the bellow criteria
     *
     * @param currencyLeft
     * @param currencyRight
     * @return -  a matching exchange
     */
    public CurrencyExchange findCurrencyExchangesByLeftRight(Long currencyLeft, Long currencyRight) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CurrencyExchange> searchQuery = cb.createQuery(CurrencyExchange.class);
        Root<CurrencyExchange> searchRoot = searchQuery.from(CurrencyExchange.class);
        searchQuery.select(searchRoot);
        searchQuery.where(getCommonWhereCondition(cb, searchRoot, currencyLeft, currencyRight));

        TypedQuery<CurrencyExchange> filterQuery = em.createQuery(searchQuery)
                .setMaxResults(1);
        
        if(filterQuery.getResultList().size() < 1) {
        	throw new RuntimeException("No result for the given parameters");
        }

        return filterQuery.getResultList().get(0);
    }

    /**
     * Delete a currencyExchange, given its identifier
     *
     * @param deletedCurrencyExchangeId - the id of the currencyExchange to be deleted
     */
    public void delete(Long deletedCurrencyExchangeId) {
        CurrencyExchange delete = em.find(CurrencyExchange.class, deletedCurrencyExchangeId);
        em.remove(delete);
    }

    /**
     *
     * finds a currencyExchange given its id
     *
     */
    public CurrencyExchange findCurrencyExchangeById(Long id) {
        return em.find(CurrencyExchange.class, id);
    }

    /**
     *
     * save changes made to a currencyExchange, or create the currencyExchange if its a new currencyExchange.
     *
     */
    public CurrencyExchange save(CurrencyExchange currencyExchange) {
        return em.merge(currencyExchange);
    }


    private Predicate[] getCommonWhereCondition(CriteriaBuilder cb, Root<CurrencyExchange> searchRoot, Long currencyLeft, Long currencyRight) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(searchRoot.<Currency>get("leftCurrency").<Long>get("id"), currencyLeft));
        predicates.add(cb.equal(searchRoot.<Currency>get("rightCurrency").<Long>get("id"), currencyRight));

        return predicates.toArray(new Predicate[]{});
    }

}
