package hu.lacztam.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.lacztam.logistic.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
	
	@Query("SELECT 	m.address, "
			+ "		m "
			+ "FROM Milestone m "
			+ "WHERE m.address.addressId = :addressId"
			+ " ")
	public Address getAddressWithMilestoneByAddressId(long addressId);
}
