package com.potapczuk.currency.app.init;


import java.math.BigDecimal;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.potapczuk.currency.app.model.Currency;
import com.potapczuk.currency.app.model.CurrencyExchange;
import com.potapczuk.currency.app.model.User;

/**
 *
 * This is a initializing bean that inserts some test data in the database. to see the data login with test123 / Password2 
 *
 */
@Component
public class TestDataInitializer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public void init() throws Exception {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User("test123", "$2a$10$x9vXeDsSC2109FZfIJz.pOZ4dJ056xBpbesuMJg3jZ.ThQkV119tS", "test@email.com");

        session.persist(user);
        
        Currency c1 = new Currency("US Dollar", "USD", "$");
        Currency c2 = new Currency("Euro", "EUR", "€");
        Currency c3 = new Currency("Brazilian Real", "BRL", "R$");
        
        session.persist(c1);
        session.persist(c2);
        session.persist(c3);
        
        session.persist(new CurrencyExchange(c1, c2, new BigDecimal("0.884531"), new BigDecimal("1.13054")));
        session.persist(new CurrencyExchange(c2, c1, new BigDecimal("1.13054"), new BigDecimal("0.884531")));
        
        session.persist(new CurrencyExchange(c1, c3, new BigDecimal("3.94557"), new BigDecimal("0.253449")));
        session.persist(new CurrencyExchange(c3, c1, new BigDecimal("0.253449"), new BigDecimal("3.94557")));
        
        session.persist(new CurrencyExchange(c2, c3, new BigDecimal("4.46042"), new BigDecimal("0.224194")));
        session.persist(new CurrencyExchange(c3, c2, new BigDecimal("0.224194"), new BigDecimal("4.46042")));

        transaction.commit();
    }
}
