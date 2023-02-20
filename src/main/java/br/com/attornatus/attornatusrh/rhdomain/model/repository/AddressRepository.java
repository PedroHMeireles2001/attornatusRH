package br.com.attornatus.attornatusrh.rhdomain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.attornatusrh.rhdomain.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
