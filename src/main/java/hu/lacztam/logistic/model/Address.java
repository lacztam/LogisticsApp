package hu.lacztam.logistic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Address {

	@Id
	@GeneratedValue
	private long addressId;
	private String ISO;
	private String city;
	private String street;
	private int zipCode;
	private int houseNumber;
	private double latitude;
	private double longitude;
	
	public Address() {
	}

	public long getAddressId() {
		return addressId;
	}

	public String getISO() {
		return ISO;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public int getZipCode() {
		return zipCode;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public void setISO(String iSO) {
		ISO = iSO;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
