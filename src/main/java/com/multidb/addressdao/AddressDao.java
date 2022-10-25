package com.multidb.addressdao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multidb.model.address.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{
	@Query(value = "SELECT * FROM address a WHERE a.eid = ?1", 
			  nativeQuery = true)
	List<Address> findAddressByEid(int eid);
}
