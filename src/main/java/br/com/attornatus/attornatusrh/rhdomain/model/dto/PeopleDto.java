package br.com.attornatus.attornatusrh.rhdomain.model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.attornatus.attornatusrh.rhdomain.model.People;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PeopleDto {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@NotBlank
	private String name;
	@NotBlank //@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$\r\n")
	private String birthDate;
	
	public People toPeople() {
		People people = new People();
		people.setName(this.name);
		people.setBirthDate(LocalDate.parse(this.birthDate, formatter));
		return people;
	}
}
