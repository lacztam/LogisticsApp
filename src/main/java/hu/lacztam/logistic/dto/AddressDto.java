package hu.lacztam.logistic.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@NotNull
public class AddressDto {

	private Long addressId;
	
	@NotNull
	@NotEmpty
	@Size(min=2, max=2)
	private String countryISO;
	
	@NotEmpty
	@NotNull
	private String city;
	
	@NotEmpty
	@NotNull
	private String street;
	
	@NotNull
	private Integer zipCode;
	
	@NotNull
	private Integer houseNumber;
	
	private double latitude;
	private double longitude;
	
	public AddressDto() {
	}

	public Long getAddressId() {
		return addressId;
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

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
