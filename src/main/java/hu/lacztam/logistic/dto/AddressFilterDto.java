package hu.lacztam.logistic.dto;


public class AddressFilterDto {
	
	String countryISO;
	String city;
	String street;
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

	@Override
	public String toString() {
		return "AddressFilterDto [countryISO=" + countryISO + ", city=" + city + ", street=" + street + ", zipCode="
				+ zipCode + "]";
	}
	
}
