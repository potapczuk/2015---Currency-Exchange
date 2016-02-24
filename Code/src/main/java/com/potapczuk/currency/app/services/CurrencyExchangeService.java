package com.potapczuk.currency.app.services;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potapczuk.currency.app.dao.CurrencyExchangeRepository;
import com.potapczuk.currency.app.model.CurrencyExchange;

/**
 *
 * Business service for CurrencyExchange-related operations.
 *
 */
@Service
public class CurrencyExchangeService {

    private static final Logger LOGGER = Logger.getLogger(CurrencyExchangeService.class);

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    /**
     * get the currency exchange
     * @return - the found results
     */
    @Transactional(readOnly = true)
    public CurrencyExchange getCurrencyExchange(Long currencyLeft, Long currencyRight) {

    	CurrencyExchange currency = currencyExchangeRepository.findCurrencyExchangesByLeftRight(currencyLeft, currencyRight);

        return currency;
    }
}
