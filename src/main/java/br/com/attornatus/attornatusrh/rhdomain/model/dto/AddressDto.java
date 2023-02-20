package br.com.attornatus.attornatusrh.rhdomain.model.dto;

import br.com.attornatus.attornatusrh.rhdomain.model.Address;
import br.com.attornatus.attornatusrh.rhdomain.model.People;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
public class AddressDto {
	
	private String number;
	@NotBlank
	private String address;
	@NotBlank //@Pattern(regexp = "")
	private String CEP;
	@NotBlank
	private String city;
	
	public Address toAddress() throws NumberFormatException {
		Address address = new Address();
		address.setAddress(this.address);
		address.setNumber(Long.parseLong(this.number));
		address.setCEP(this.CEP);
		address.setCity(this.city);
		return address;
	}
	
}
