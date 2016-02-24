package com.potapczuk.currency.app.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * The Currency JPA entity
 *
 */
@Entity
@Table(name = "currency")
public class Currency extends AbstractEntity {

    private String name;
    private String shortName;
    private String symbol;

    public Currency() {

    }

	public Currency(String name, String shortName, String symbol) {
		super();
		this.name = name;
		this.shortName = shortName;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
