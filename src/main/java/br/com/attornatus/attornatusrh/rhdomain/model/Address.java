package br.com.attornatus.attornatusrh.rhdomain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Address {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter(value = AccessLevel.PRIVATE)
	private Long id;
	
	@ManyToOne @JsonIgnore
	private People people;
	
	
	private Long number;
	private String address;
	private String CEP;
	private String City;
}
