package hu.lacztam.logistic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long addressId;
	private String countryISO;
	private String city;
	private String street;
	private Integer zipCode;
	private Integer houseNumber;
	private Double latitude;
	private Double longitude;

	@OneToOne
	Milestone milestone;
	
	public Address() {
	}

	public void modifyAddress(Address address) {
		this.countryISO = address.getCountryISO();
		this.city = address.getCity();
		this.street = address.getStreet();
		this.zipCode = address.getZipCode();
		this.houseNumber = address.getHouseNumber();
		this.latitude = address.getLatitude();
		this.longitude = address.getLongitude();
		this.milestone = address.getMilestone();
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

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
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

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

}
