package com.potapczuk.currency.app.model;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * The Currency JPA entity
 *
 */
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange extends AbstractEntity {

	@ManyToOne
	private Currency leftCurrency;
	
	@ManyToOne
	private Currency rightCurrency;
	
	@Column(precision=20, scale=8)
    private BigDecimal value;
	
	@Column(precision=20, scale=8)
    private BigDecimal inverseValue;

    public CurrencyExchange() {

    }

	public CurrencyExchange(Currency leftCurrency, Currency rightCurrency,
			BigDecimal value, BigDecimal inverseValue) {
		super();
		this.leftCurrency = leftCurrency;
		this.rightCurrency = rightCurrency;
		this.value = value;
		this.inverseValue = inverseValue;
	}

	public Currency getLeftCurrency() {
		return leftCurrency;
	}

	public void setLeftCurrency(Currency leftCurrency) {
		this.leftCurrency = leftCurrency;
	}

	public Currency getRightCurrency() {
		return rightCurrency;
	}

	public void setRightCurrency(Currency rightCurrency) {
		this.rightCurrency = rightCurrency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getInverseValue() {
		return inverseValue;
	}

	public void setInverseValue(BigDecimal inverseValue) {
		this.inverseValue = inverseValue;
	}
}
