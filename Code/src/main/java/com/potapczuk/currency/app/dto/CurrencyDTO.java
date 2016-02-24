package com.potapczuk.currency.app.dto;



/**
 *
 * JSON serializable DTO containing currency data
 *
 */
public class CurrencyDTO {

    private Long id;

    private String name;
    private String shortName;
    private String symbol;

    public CurrencyDTO() {
    }

	public CurrencyDTO(Long id, String name, String shortName, String symbol) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.symbol = symbol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
