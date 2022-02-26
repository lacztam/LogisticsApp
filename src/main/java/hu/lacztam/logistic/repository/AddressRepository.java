package hu.lacztam.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.lacztam.logistic.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
	
	@Query("SELECT 	m.address.addressId, "
			+ "		m.address.countryISO, "
			+ "		m.address.city, "
			+ "		m.address.street, "
			+ "		m.address.zipCode, "
			+ "		m.address.houseNumber, "
			+ "		m.address.latitude, "
			+ "		m.address.longitude, "
			+ "		m "
			+ "FROM Milestone m "
			+ "WHERE m.address.addressId = :addressId"
			+ " ")
	public Address getAddressWithMilestoneByAddressId(long addressId);
}
