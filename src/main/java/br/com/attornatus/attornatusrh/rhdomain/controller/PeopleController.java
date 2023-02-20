package br.com.attornatus.attornatusrh.rhdomain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@RequestMapping("/api/peoples")
@RestController
public class PeopleController {
	
	@Autowired
	private PeopleRepository peopleRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping("/id/{id}")
	public People getPeople(@PathVariable("id") Long id) {
		return peopleRepository.findById(id).orElseGet(()-> null);
	}
	
	@GetMapping("/{page}")
	public List<People> getPeoples(@PathVariable("page") int page) {
		Sort sorter = Sort.by("id").descending();
		PageRequest pager = PageRequest.of(page, 20, sorter);
		List<People> peoples = peopleRepository.findAll(pager).toList();
		return peoples;
	}
	@PostMapping
	public People savePeople(@Valid PeopleDto peopleDto, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		People newPeople = peopleDto.toPeople();
		peopleRepository.save(newPeople);
		return newPeople;
	}
	@DeleteMapping("/{id}")
	public People deletePeople(@PathVariable("id") Long id) {
		Optional<People> result = peopleRepository.findById(id);
		if(result.isEmpty())
			return null;
		People deletedPeople = result.get();
		peopleRepository.delete(deletedPeople);
		return deletedPeople;
	}
	@PutMapping("/{id}")
	public People updatePeople(@PathVariable("id") Long id,@Valid PeopleDto peopleDto, BindingResult bindingresult) {
		Optional<People> result = peopleRepository.findById(id);
		if(result.isEmpty() || bindingresult.hasErrors())
			return null;
		People oldPeople = result.get();
		People newPeople = peopleDto.toPeople();
		
		oldPeople.setName(newPeople.getName());
		oldPeople.setBirthDate(newPeople.getBirthDate());
		
		peopleRepository.save(oldPeople);
		return oldPeople;
	}
}
