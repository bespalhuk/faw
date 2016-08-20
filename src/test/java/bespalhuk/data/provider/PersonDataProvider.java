package bespalhuk.data.provider;

import bespalhuk.domain.model.Name;
import bespalhuk.domain.model.person.Person;

public class PersonDataProvider {

	public static Person boca() {
		Person person = Person.of();
		person.setName(Name.of("boca"));
		return person;
	}

	public static Person ricardo() {
		Person person = Person.of();
		person.setName(Name.of("ricardo"));
		return person;
	}

	public static Person bespalhuk() {
		Person person = Person.of();
		person.setName(Name.of("bespalhuk"));
		return person;
	}

}
