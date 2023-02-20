package br.com.attornatus.attornatusrh.rhdomain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.attornatus.attornatusrh.rhdomain.model.Address;
import br.com.attornatus.attornatusrh.rhdomain.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{
}
