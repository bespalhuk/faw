package bespalhuk.view.model.person;

import bespalhuk.AbstractIT;
import bespalhuk.data.provider.PersonDataProvider;
import bespalhuk.domain.model.Name;
import bespalhuk.domain.model.person.Person;
import bespalhuk.infra.persistence.repository.PersonRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.DefaultMessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonValidatorIT extends AbstractIT {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonValidator validator;

	private ValidationContext validationContext;

	@BeforeMethod
	public void init() {
		validationContext = Mockito.mock(ValidationContext.class);
		Mockito.when(validationContext.getMessageContext()).thenReturn(new DefaultMessageContext());
	}

	@Test
	public void same() {
		validator.validate(personRepository.findOne(Persons.withName("boca")).get(), validationContext);
		assertThat(validationContext.getMessageContext().hasErrorMessages()).isFalse();
	}

	@Test
	public void exists() {
		validator.validate(PersonDataProvider.boca(), validationContext);
		assertThat(validationContext.getMessageContext().hasErrorMessages()).isTrue();
	}

	@Test
	public void editDifferent() {
		Person person = PersonDataProvider.boca();
		person.setName(Name.of("b0ca"));
		validator.validate(person, validationContext);
		assertThat(validationContext.getMessageContext().hasErrorMessages()).isFalse();
	}

	@Test
	public void editExists() {
		Person person = PersonDataProvider.ricardo();
		person.setName(Name.of("bespalhuk"));
		validator.validate(person, validationContext);
		assertThat(validationContext.getMessageContext().hasErrorMessages()).isTrue();
	}

}
