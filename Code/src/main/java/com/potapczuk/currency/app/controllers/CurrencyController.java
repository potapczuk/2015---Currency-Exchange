package com.potapczuk.currency.app.controllers;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.potapczuk.currency.app.model.Currency;
import com.potapczuk.currency.app.model.CurrencyExchange;
import com.potapczuk.currency.app.services.CurrencyExchangeService;
import com.potapczuk.currency.app.services.CurrencyService;

/**
 *
 *  REST service for currency for the currently logged in user.
 *
 */
@Controller
@RequestMapping("currency")
public class CurrencyController {

    Logger LOGGER = Logger.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService currencyService;
    
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<Currency> getCurrencies() {

    	List<Currency> result = currencyService.getCurrencies();

        return result;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "exchange")
    public CurrencyExchange getCurrencyExchange(@RequestParam(value = "currencyLeft") Long currencyLeft,
    		@RequestParam(value = "currencyRight") Long currencyRight) {

    	CurrencyExchange result = currencyExchangeService.getCurrencyExchange(currencyLeft, currencyRight);

        return result;
    }

    /**
     *
     * error handler for backend errors - a 400 status code will be sent back, and the body
     * of the message contains the exception text.
     *
     * @param exc - the exception caught
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
