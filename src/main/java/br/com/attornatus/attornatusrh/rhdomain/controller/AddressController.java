package br.com.attornatus.attornatusrh.rhdomain.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.attornatusrh.rhdomain.model.Address;
import br.com.attornatus.attornatusrh.rhdomain.model.People;
import br.com.attornatus.attornatusrh.rhdomain.model.dto.AddressDto;
import br.com.attornatus.attornatusrh.rhdomain.model.dto.PeopleDto;
import br.com.attornatus.attornatusrh.rhdomain.model.repository.AddressRepository;
import br.com.attornatus.attornatusrh.rhdomain.model.repository.PeopleRepository;
import jakarta.validation.Valid;

@RequestMapping("/api/peoples/id/{id}/address")
@RestController
public class AddressController {
	
	@Autowired
	private PeopleRepository peopleRepository;
	@Autowired
	private AddressRepository addressRepository;
	@PostMapping("/addressPrincipal/{addressId}")
	public Address getPeople(@PathVariable("id") Long id,@PathVariable("addressId") Long addressId) {
		Optional<People> peopleResult = peopleRepository.findById(id);
		Optional<Address> addressPrincipalResult = addressRepository.findById(addressId);
		if(peopleResult.isEmpty() || addressPrincipalResult.isEmpty())
			return null;
		People people = peopleResult.get();
		Address addressPrincipal = addressPrincipalResult.get();
		people.setAddressPrincipal(addressPrincipal);
		peopleRepository.save(people);
		return addressPrincipal;
	}
	
	@PostMapping
	public Address saveAddress(@Valid AddressDto addressDto,BindingResult result,@PathVariable("id") Long peopleId) {
		try {
			Address address = addressDto.toAddress();
			Optional<People> peopleResult = peopleRepository.findById(peopleId);
			if(peopleResult.isEmpty())
				return null;
			People people = peopleResult.get();
			address.setPeople(people);
			people.getAddresses().add(address);
			peopleRepository.save(people);
			return address;
		} catch (NumberFormatException e) {
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	@DeleteMapping("/{addressId}")
	public Address deleteAddress(@PathVariable("addressId") Long addressId,@PathVariable("id") Long id) {
		Optional<Address> addressResult = addressRepository.findById(addressId);
		Optional<People> peopleResult = peopleRepository.findById(id);
		if(addressResult.isEmpty() || peopleResult.isEmpty())
			return null;
		People people = peopleResult.get();
		Address address = addressResult.get();
		people.getAddresses().removeIf((a)->a.getId()==addressId);
		peopleRepository.save(people);
		return address;
	}
	
}
