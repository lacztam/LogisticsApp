package hu.lacztam.logistic.dto;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

public class AddressFilterDto {
	
	@Size(min=2, max=2)
	String countryISO;
	String city;
	String street;
	@NumberFormat
	Integer zipCode;

	public AddressFilterDto() {
	}

	public String getCountryISO() {
		return countryISO;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setCountryISO(String countryISO) {
		this.countryISO = countryISO;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
}
