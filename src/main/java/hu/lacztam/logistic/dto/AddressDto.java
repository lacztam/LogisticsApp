package hu.lacztam.logistic.dto;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@NotNull
public class AddressDto {
	@Nullable
	private Long addressId;
	
	@NotNull
	@NotEmpty
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
	
	private Double latitude;
	private Double longitude;
	
	@OneToOne
	MilestoneDto milestoneDto;
	
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

	public MilestoneDto getMilestoneDto() {
		return milestoneDto;
	}

	public void setMilestoneDto(MilestoneDto milestoneDto) {
		this.milestoneDto = milestoneDto;
	}

}
