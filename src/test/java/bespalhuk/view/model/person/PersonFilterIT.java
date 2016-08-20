package bespalhuk.view.model.person;

import bespalhuk.AbstractIT;
import bespalhuk.infra.persistence.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonFilterIT extends AbstractIT {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void empty() {
		PersonFilter filter = PersonFilter.of();
		assertThat(personRepository.findAll(filter)).hasSize(3);
	}

	@Test
	public void withName() {
		PersonFilter filter = PersonFilter.of();
		filter.setName("b");
		assertThat(personRepository.findAll(filter)).hasSize(2);
	}

}
