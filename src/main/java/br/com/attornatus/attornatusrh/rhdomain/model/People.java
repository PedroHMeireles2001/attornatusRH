package br.com.attornatus.attornatusrh.rhdomain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class People {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(value = AccessLevel.PRIVATE)
	private Long id;
	
	private String name;
	private LocalDate birthDate;
	@OneToOne
	private Address addressPrincipal;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Address> addresses = new ArrayList<Address>();
}
