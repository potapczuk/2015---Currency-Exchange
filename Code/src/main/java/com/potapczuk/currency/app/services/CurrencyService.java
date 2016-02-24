package com.potapczuk.currency.app.services;


import static com.potapczuk.currency.app.services.ValidationUtils.assertNotBlank;
import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potapczuk.currency.app.dao.CurrencyRepository;
import com.potapczuk.currency.app.dto.CurrencyDTO;
import com.potapczuk.currency.app.model.Currency;

/**
 *
 * Business service for Currency-related operations.
 *
 */
@Service
public class CurrencyService {

    private static final Logger LOGGER = Logger.getLogger(CurrencyService.class);

    @Autowired
    CurrencyRepository currencyRepository;

    /**
     *
     * get currencies
     * @return - the found results
     */
    @Transactional(readOnly = true)
    public List<Currency> getCurrencies() {

        List<Currency> currencies = currencyRepository.getCurrencies();

        return currencies;
    }

    /**
     *
     * deletes a list of currencies, given their Ids
     *
     * @param deletedCurrencyIds - the list of currencies to delete
     */
    @Transactional
    public void deleteCurrencys(List<Long> deletedCurrencyIds) {
        notNull(deletedCurrencyIds, "deletedCurrencysId is mandatory");
        deletedCurrencyIds.stream().forEach((deletedCurrencyId) -> currencyRepository.delete(deletedCurrencyId));
    }

    /**
     *
     * saves a currency (new or not) into the database.
     *
     * @param id 
     * @param name
     * @return - the new version of the currency
     */

    @Transactional
    public Currency saveCurrency(Long id, String name, String shortName, String symbol) {

    	notNull(name, "name is mandatory");
        assertNotBlank(name, "name cannot be blank");
        notNull(shortName, "name is mandatory");
        assertNotBlank(shortName, "name cannot be blank");

        Currency currency = null;

        if (id != null) {
            currency = currencyRepository.findCurrencyById(id);

            currency.setName(name);
            currency.setShortName(shortName);
            currency.setSymbol(symbol);
        } else {
            currency = currencyRepository.save(new Currency(name, shortName, symbol));
        }

        return currency;
    }

    /**
     *
     * saves a list of currencies (new or not) into the database
     *
     * @param currencies - the list of currencies to be saved
     * @return - the new versions of the saved currencies
     */
    @Transactional
    public List<Currency> saveCurrencys(List<CurrencyDTO> currencies) {
        return currencies.stream()
                .map((currency) -> saveCurrency(
                        currency.getId(),
                        currency.getName(),
                        currency.getShortName(),
                        currency.getSymbol()))
                .collect(Collectors.toList());
    }
}
